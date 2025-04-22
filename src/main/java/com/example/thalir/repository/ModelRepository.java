package com.example.thalir.repository;

import com.example.thalir.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {
    public boolean existsByFileUrl(String fileUrl);
    boolean existsByThumbnailUrl(String thumbnailUrl);
    Optional<Model> findById(Long id);
}
