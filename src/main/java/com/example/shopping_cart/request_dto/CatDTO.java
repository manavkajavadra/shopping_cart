package com.example.shopping_cart.request_dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j

public class CatDTO {

    @NotBlank(message = "Category name is required")
    private String catName;

    //    think witch way is best one line or double line
//    @NotBlank(message = "Status must be either 'Active' or 'Inactive'")
    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^(Active|Inactive)$", message = "Status must be either Active or Inactive")
    private String status;
}