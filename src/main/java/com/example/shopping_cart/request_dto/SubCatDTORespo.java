package com.example.shopping_cart.request_dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"subCatId", "catId", "subCatName", "status", "createdAt", "createdBy"})

public class SubCatDTORespo extends SubCatDTO {
    private Integer subCatId;
    private String createdAt;
    private Integer createdBy;


    public SubCatDTORespo(Integer subCatId, Integer catId, String subCatName, String status, String createdAt,
                          Integer createdBy) {

        super(catId, subCatName, status);
        this.subCatId = subCatId;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
}