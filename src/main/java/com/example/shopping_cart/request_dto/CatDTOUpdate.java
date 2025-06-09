package com.example.shopping_cart.request_dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CatDTOUpdate extends CatDTO {

    private Integer catId;

    public CatDTOUpdate(Integer catId, String catName, String status) {
        super(catName, status);
        this.catId = catId;
    }
}