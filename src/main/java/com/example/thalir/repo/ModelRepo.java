package com.example.thalir.repo;

import com.example.thalir.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModelRepo extends JpaRepository<Model, Long> {

}
