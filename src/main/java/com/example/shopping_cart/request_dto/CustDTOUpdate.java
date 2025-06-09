package com.example.shopping_cart.request_dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustDTOUpdate extends CustDTO {

    private Integer custId;

    CustDTOUpdate(Integer custId, String createdAt, String custName,
                  String aadhaarNo, String address, String pswd) {

        super(custName, aadhaarNo, address, null);
        this.custId = custId;
    }
}
