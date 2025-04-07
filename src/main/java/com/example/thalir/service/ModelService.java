package com.example.thalir.service;


import com.example.thalir.model.Model;
import com.example.thalir.repo.ModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ModelService {


    private final ModelRepo repo;

    @Autowired
    ModelService(ModelRepo repo){
        this.repo = repo;
    }

    //get all model
    public List<Model> getAllDetails(){
        return repo.findAll();
    }

    //save a model
    public Model saveModel(Model model){
        System.out.println(model);
        repo.save(model);
        return model;
    }

    //get by UUID
    public Model getById(Long id){
        return repo.getReferenceById(id);
    }

    //delete model
    public void deleteModel(Long id){
        repo.deleteById(id);
    }
}
