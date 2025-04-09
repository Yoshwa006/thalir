package com.example.thalir.service;


import com.example.thalir.exception.FileUrlAlreadyExistsException;
import com.example.thalir.dto.MapperDTO;
import com.example.thalir.dto.ModelRequestDTO;
import com.example.thalir.dto.ModelResponseDTO;
import com.example.thalir.model.Model;
import com.example.thalir.repo.ModelRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ModelResponseDTO saveModel(ModelRequestDTO dto){
        if(repo.existsByFileUrl(dto.getFileUrl())){
            throw new FileUrlAlreadyExistsException("This file already exists  -->  " + dto.getFileUrl());}
        Model model = MapperDTO.toModel(dto);
        repo.save(model);
        return MapperDTO.toResponse(model);
    }

    public ModelResponseDTO getById(Long id) {
        Model model = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Model not found with id: " + id));
        return MapperDTO.toResponse(model);
    }

    //delete model
    public void deleteModel(Long id){
        repo.deleteById(id);
    }

    //edit a model
    public ModelResponseDTO updateModel(Long id, ModelRequestDTO dto) {


        Model existingModel = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found with id: " + id));


        existingModel.setName(dto.getName());
        existingModel.setDescription(dto.getDescription());
        existingModel.setPrice(dto.getPrice());
        existingModel.setCategory(dto.getCategory());
        existingModel.setFileUrl(dto.getFileUrl());
        existingModel.setThumbnailUrl(dto.getThumbnailUrl());
        existingModel.setPublished(dto.isPublished());
        existingModel.setFree(dto.isFree());


        Model updatedModel = repo.save(existingModel);

       return MapperDTO.toResponse(updatedModel);
    }

}
