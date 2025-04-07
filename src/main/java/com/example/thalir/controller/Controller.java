package com.example.thalir.controller;


import com.example.thalir.model.Model;
import com.example.thalir.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    private final ModelService service;

    @Autowired
    Controller(ModelService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllData(){
        List<Model> model = service.getAllDetails();

        return ResponseEntity.ok().body(model);
    }

    @PostMapping
    public ResponseEntity<?> getModelData(@RequestBody Model model){
        service.saveModel(model);

        return ResponseEntity.ok().body(model);
    }
}
