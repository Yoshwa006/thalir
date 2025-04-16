package com.example.thalir.repository;

import com.example.thalir.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
    public boolean existsByFileUrl(String fileUrl);
}
