package com.example.thalir.dto;

import com.example.thalir.dto.request.ModelRequestDTO;
import com.example.thalir.dto.request.RegisterRequest;
import com.example.thalir.dto.responce.ModelResponseDTO;
import com.example.thalir.entity.Model;
import com.example.thalir.entity.User;

public class DTOMapper {


    public static Model toModel(ModelRequestDTO dto) {


        Model model = new Model();    // to real datatype


        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setPrice(dto.getPrice());
        model.setCategory(dto.getCategory());
        model.setFileUrl(dto.getFileUrl());
        model.setThumbnailUrl(dto.getThumbnailUrl());
        model.setPublished(dto.isPublished());
        model.setFree(dto.isFree());
        return model;
    }

    public static ModelResponseDTO toResponse(Model model) {


        ModelResponseDTO responseDTO = new ModelResponseDTO();    //all string


        responseDTO.setName(model.getName());
        responseDTO.setDescription(model.getDescription());
        responseDTO.setPrice(model.getPrice());
        responseDTO.setCategory(model.getCategory());
        responseDTO.setFileUrl(model.getFileUrl());
        responseDTO.setThumbnailUrl(model.getThumbnailUrl());
        responseDTO.setPublished(model.isPublished());
        responseDTO.setFree(model.isFree());
        return responseDTO;
    }

    public static User toUser(RegisterRequest request){
        User user  = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());
        return user;
    }

}
