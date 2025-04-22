package com.example.thalir.repository;

import com.example.thalir.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ModelRepository extends JpaRepository<Model, Long> {
    boolean existsByFileUrl(String fileUrl);
    boolean existsByThumbnailUrl(String thumbnailUrl);
}
