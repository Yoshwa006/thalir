package com.example.thalir.dto.request;

import lombok.Data;

@Data
public class AddToCartRequest {

    public Long getModelId() {
        return modelId;
    }

    private Long modelId;

}
