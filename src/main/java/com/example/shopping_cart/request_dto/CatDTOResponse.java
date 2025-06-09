package com.example.shopping_cart.request_dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data   
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"catId", "catName", "status", "createdAt", "createdBy"})

public class CatDTOResponse extends CatDTO {
    private Integer catId;
    private String createdAt;
    private Integer createdBy;

    public CatDTOResponse(Integer catId, String catName, String status, String createdAt, Integer createdBy) {
        super(catName, status);
        this.catId = catId;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
}
