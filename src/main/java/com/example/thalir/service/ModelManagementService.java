package com.example.thalir.service;


import com.example.thalir.exceptions.FileUrlAlreadyExistsException;
import com.example.thalir.dto.DTOMapper;
import com.example.thalir.dto.request.ModelRequestDTO;
import com.example.thalir.dto.responce.ModelResponseDTO;
import com.example.thalir.entity.Model;
import com.example.thalir.repository.ModelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelManagementService {


    private final ModelRepository repo;

    @Autowired
    ModelManagementService(ModelRepository repo){
        this.repo = repo;
    }


    public List<Model> getAllDetails(){
        return repo.findAll();
    }


    public ModelResponseDTO saveModel(ModelRequestDTO dto){
        if(repo.existsByFileUrl(dto.getFileUrl())){
            throw new FileUrlAlreadyExistsException("This file already exists  -->  " + dto.getFileUrl());}
        Model model = DTOMapper.toModel(dto);
        repo.save(model);
        return DTOMapper.toResponse(model);
    }


    public ModelResponseDTO getById(Long id) {
        Model model = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Model not found with id: " + id));
        return DTOMapper.toResponse(model);
    }


    public void deleteModel(Long id){
        repo.deleteById(id);
    }



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

       return DTOMapper.toResponse(updatedModel);
    }

}
