package com.example.shopping_cart.request_dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class CustDTO {

    @NotBlank(message = "Customer name is required")
    private String custName;

    @NotBlank(message = "Aadhaar number is required")
    private String aadhaarNo;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String pswd;
}
