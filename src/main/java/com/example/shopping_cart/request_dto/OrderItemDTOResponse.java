package com.example.shopping_cart.request_dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"orderId", "orderItemId", "itemId", "orderItemName", "qty", "unitPrice", "totalAmt"})


public class OrderItemDTOResponse extends OrderItemDTO {

    private String orderItemName;
    private Integer totalAmt;

    OrderItemDTOResponse(Integer orderItemId, Integer orderId, Integer itemId,
                         Integer qty, Integer unitPrice, String orderItemName) {
        super(orderItemId, orderId, itemId, qty, unitPrice);

        this.orderItemName = orderItemName;
        this.totalAmt = getTotalAmt();
    }

}