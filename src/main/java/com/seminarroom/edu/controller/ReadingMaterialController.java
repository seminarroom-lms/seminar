package com.seminarroom.edu.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.seminarroom.edu.model.ReadingMaterial;
import com.seminarroom.edu.repository.ReadingMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses/{courseId}/modules/{moduleId}/reading-materials")
public class ReadingMaterialController {

    @Autowired
    private ReadingMaterialRepository repository;

    @Autowired
    private AmazonS3 amazonS3;

    private static final String BUCKET_NAME = "your-bucket-name"; // Replace with your S3 bucket

    @GetMapping
    public List<ReadingMaterial> getReadingMaterialsByModuleId(@PathVariable Long moduleId) {
        return repository.findByModuleId(moduleId);
    }

    @PostMapping
    public ReadingMaterial create(@RequestBody ReadingMaterial material) {
        return repository.save(material);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadPdfFile(
            @PathVariable Long courseId,
            @PathVariable Long moduleId,
            @PathVariable Long id) {

        Optional<ReadingMaterial> materialOptional = repository.findById(id);

        if (materialOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        ReadingMaterial material = materialOptional.get();
        String s3Url = material.getS3Url();

        if (s3Url == null || s3Url.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        try {
            // Extract key from URL (assuming it's in the form: https://bucket-name.s3.amazonaws.com/folder/filename.pdf)
            String key = s3Url.substring(s3Url.indexOf(".com/") + 5);

            S3Object s3Object = amazonS3.getObject(BUCKET_NAME, key);
            InputStream inputStream = s3Object.getObjectContent();
            byte[] fileBytes = inputStream.readAllBytes();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename("material_" + id + ".pdf")
                    .build());

            return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
