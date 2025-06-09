package com.example.shopping_cart.controller_noException.controller;

import com.example.shopping_cart.request_dto.ProductAddDTO;
import com.example.shopping_cart.request_dto.ProductEditDTO;
import com.example.shopping_cart.response_dto.CommonResponse;
import com.example.shopping_cart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")

public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/insert")
    public ResponseEntity<CommonResponse> insert(@RequestBody ProductAddDTO productAddDTO) {
        ProductEditDTO productEditDTO = productService.insert(productAddDTO);

        if (productEditDTO == null) {
            return new ResponseEntity<>(new CommonResponse(
                    true,
                    "This name is already exist",
                    null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new CommonResponse(
                true,
                "Record inserted successfully",
                productEditDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get-list-by-status")
    public ResponseEntity<CommonResponse> getListById(@RequestParam String status) {
        List<ProductEditDTO> productEditDTOList = productService.getListByStatus(status);

        if (productEditDTOList == null) {
            return new ResponseEntity<>(new CommonResponse(
                    true,
                    "This status is not found",
                    null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new CommonResponse(
                true,
                "Record of your status",
                productEditDTOList), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CommonResponse> update(@RequestBody ProductEditDTO productEditDTO) {
        productEditDTO = productService.update(productEditDTO);

        if (productEditDTO == null) {
            return new ResponseEntity<>(new CommonResponse(
                    true,
                    "This id is not found",
                    null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new CommonResponse(
                true,
                "Record updated successfully",
                productEditDTO), HttpStatus.OK);
    }

    @PatchMapping("/change-status/{pro_id}/{status}")
    public ResponseEntity<CommonResponse> chnageStatus(@PathVariable int pro_id,
                                                       @PathVariable String status) {

        ProductEditDTO productEditDTO = productService.changeStatus(pro_id, status);
        if (productEditDTO == null) {
            return new ResponseEntity<>(new CommonResponse(
                    true,
                    "This id is not found",
                    null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new CommonResponse(
                true,
                "Record updated successfully",
                productEditDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CommonResponse> delete(@RequestParam int pro_id) {
        boolean result = productService.delete(pro_id);

        if (result == false) {
            return new ResponseEntity<>(new CommonResponse(
                    true,
                    "This id is not found",
                    null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new CommonResponse(
                true,
                "Record delete successfully",
                null), HttpStatus.OK);
    }
}