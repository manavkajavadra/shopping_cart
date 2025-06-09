package com.example.shopping_cart.request_dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ItemDTOUpdate extends ItemDTO {
    private Integer itemId;



    public ItemDTOUpdate(Integer itemId, Integer subCatId, String itemName, Integer stockQty, Integer price,
                         String expDate, String status, String photo) {

        super(subCatId, itemName, stockQty, price, expDate, status, photo);
        this.itemId = itemId;
    }
}