package com.example.thalir.dto.responce;

import com.example.thalir.entity.Model;

public class CartModelDTO {


    private Long id;
    private String name;
    private Double price;

    public CartModelDTO(Model model) {
        this.id = model.getId();
        this.name = model.getName();
        this.price = model.getPrice();

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}