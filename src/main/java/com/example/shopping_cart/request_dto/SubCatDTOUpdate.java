package com.example.shopping_cart.request_dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SubCatDTOUpdate extends SubCatDTO {

    private Integer subCatId;


    public SubCatDTOUpdate(Integer subCatId, Integer catId, String subCatName, String status) {

        super(catId, subCatName, status);
        this.subCatId = subCatId;
    }
}