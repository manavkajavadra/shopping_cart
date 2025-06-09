package com.example.shopping_cart.controller_noException.controller;

import com.example.shopping_cart.request_dto.SubCatAddDTO;
import com.example.shopping_cart.request_dto.SubCatEditDTO;
import com.example.shopping_cart.response_dto.CommonResponse;
import com.example.shopping_cart.service.SubCatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/sub-cat")

public class SubCateController {

    @Autowired
    SubCatService subCatService;

    @PostMapping("/insert")
    public ResponseEntity<CommonResponse> insert(@RequestBody SubCatAddDTO subCatAddDTO) {
        log.info("subCatAddDTO {}", subCatAddDTO);
        SubCatEditDTO subCatEditDTO = subCatService.insert(subCatAddDTO);

        if (subCatEditDTO == null) {
            return new ResponseEntity<>(new CommonResponse(
                    true,
                    "This sub category name is already exist",
                    null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new CommonResponse(
                true,
                "Record insert successfully",
                subCatEditDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get-list-status")
    public ResponseEntity<CommonResponse> getListSubCatByStatus(@RequestParam String status) {

        return new ResponseEntity<>(new CommonResponse(
                true,
                "List of your status",
                subCatService.getListOfSubCatByStatus(status)), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CommonResponse> update(@RequestBody SubCatEditDTO subCatEditDTO) {
        subCatEditDTO = subCatService.update(subCatEditDTO);
        if (subCatEditDTO == null) {
            return new ResponseEntity<>(new CommonResponse(
                    true,
                    "This id not found",
                    null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new CommonResponse(
                true,
                "Record update successfully",
                subCatEditDTO), HttpStatus.OK);
    }

    @PatchMapping("/update-status/{sub_cat_id}/{status}")
    public ResponseEntity<CommonResponse> changeStatus(@PathVariable int sub_cat_id,
                                                       @PathVariable String status) {

        SubCatEditDTO subCatEditDTO = subCatService.changeStatus(sub_cat_id, status);
        if (subCatEditDTO == null) {
            return new ResponseEntity<>(new CommonResponse(
                    true,
                    "This id not found",
                    null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new CommonResponse(
                true,
                "Record update successfully",
                subCatEditDTO), HttpStatus.OK);
    }

    @GetMapping("/get-list-sub-cat-id")
    public ResponseEntity<CommonResponse> getListSubCatId(@RequestParam int sub_cat_id) {

        SubCatEditDTO subCatEditDTO = subCatService.getListSubCatId(sub_cat_id);
        if (subCatEditDTO == null) {
            return new ResponseEntity<>(new CommonResponse(
                    true,
                    "This id is not found",
                    null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new CommonResponse(
                true,
                "Record of your id",
                subCatEditDTO), HttpStatus.OK);
    }
}