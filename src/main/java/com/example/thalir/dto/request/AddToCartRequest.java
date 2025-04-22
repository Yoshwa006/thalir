package com.example.thalir.dto.request;

import lombok.Data;

@Data
public class AddToCartRequest {

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    private Long modelId;

}
