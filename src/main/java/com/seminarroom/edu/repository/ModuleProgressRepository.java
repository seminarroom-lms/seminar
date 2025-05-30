package com.seminarroom.edu.repository;

import com.seminarroom.edu.model.ModuleProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModuleProgressRepository extends JpaRepository<ModuleProgress, Long> {
    Optional<ModuleProgress> findByUserIdAndModuleId(Long userId, Long moduleId);
}

