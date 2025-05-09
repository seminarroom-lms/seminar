package com.seminarroom.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.seminarroom.edu.config.WebConfig;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(WebConfig.class)
public class EduJourneyBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduJourneyBackendApplication.class, args);
    }
}
