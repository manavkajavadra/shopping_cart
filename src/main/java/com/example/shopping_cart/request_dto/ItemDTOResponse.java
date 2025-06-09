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
@JsonPropertyOrder({"categoryName", "subCategoryName", "itemId", "subCatId", "itemName", "stockQty", "price",
                    "expDate", "status", "photo", "createdAt", "createdBy"})

public class ItemDTOResponse extends ItemDTO {
    private Integer itemId;

    private String catName;
    private String subCatName;
    private String createdAt;
    private Integer createdBy;


    public ItemDTOResponse(Integer itemId, Integer subCatId, String itemName, Integer stockQty, Integer price,
                           String expDate, String status, String photo, String createdAt, Integer createdBy) {

        super(subCatId, itemName, stockQty, price, expDate, status, photo);
        this.itemId = itemId;
        this.createdAt = createdAt;
        this.createdBy = createdBy;

        this.catName = catName;
        this.subCatName = subCatName;
    }
}