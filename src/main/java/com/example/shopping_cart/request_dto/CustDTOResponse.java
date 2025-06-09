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
@JsonPropertyOrder({"custId", "custName", "aadhaarNo", "address", "createdAt"})

public class CustDTOResponse extends CustDTO {

    private Integer custId;
    private String createdAt;

    CustDTOResponse(Integer custId, String createdAt, String custName,
                    String aadhaarNo, String address, String pswd) {

        super(custName, aadhaarNo, address, null);
        this.custId = custId;
        this.createdAt = createdAt;
    }
}
