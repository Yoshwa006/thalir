package com.example.thalir.controller;

import com.example.thalir.dto.request.AddToCartRequest;
import com.example.thalir.dto.request.ModelRequestDTO;
import com.example.thalir.dto.responce.ModelResponseDTO;
import com.example.thalir.entity.Model;
import com.example.thalir.service.CartService;
import com.example.thalir.service.JwtService;
import com.example.thalir.service.ModelManagementService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class ModelController {

    private final ModelManagementService service;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final CartService cartService;

    @Autowired
    public ModelController(ModelManagementService service, JwtService jwtService, CartService cartService) {
        this.service = service;
        this.jwtService = jwtService;
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<Model>> getAllData() {
        return ResponseEntity.ok(service.getAllDetails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            ModelResponseDTO dto = service.getById(id);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("Model not found with ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<ModelResponseDTO> saveModel(@RequestBody ModelRequestDTO dto) {
        ModelResponseDTO saved = service.saveModel(dto);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateModel(@PathVariable Long id, @RequestBody ModelRequestDTO dto) {
        try {
            ModelResponseDTO updated = service.updateModel(id, dto);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("Model not found with ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteModel(@PathVariable Long id) {
        try {
            service.deleteModel(id);
            return ResponseEntity.ok("Deleted model with ID: " + id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("Model not found with ID: " + id);
        }
    }

}
