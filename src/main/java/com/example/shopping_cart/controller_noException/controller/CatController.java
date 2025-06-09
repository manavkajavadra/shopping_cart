package com.example.shopping_cart.controller_noException.controller;

import com.example.shopping_cart.request_dto.CatAddDTO;
import com.example.shopping_cart.request_dto.CatEditDTO;
import com.example.shopping_cart.response_dto.CommonResponse;
import com.example.shopping_cart.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cat")

public class CatController {
    @Autowired
    CatService catService;


    @GetMapping("/get-list")
    public ResponseEntity<CommonResponse> getList() {

        return new ResponseEntity<>(new CommonResponse(
                true,
                "List of category record",
                catService.getList()), HttpStatus.OK);
    }

    @GetMapping("/get-list-status")
    public ResponseEntity<CommonResponse> getListByStatus(@RequestParam String status) {

        return new ResponseEntity<>(new CommonResponse(
                true,
                "List of records based on status",
                catService.getListCatByStatus(status)), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<CommonResponse> insert(@RequestBody CatAddDTO catAddDTO) {
        CatEditDTO catEditDTO = catService.insert(catAddDTO);

        if (catEditDTO == null) {
            return new ResponseEntity<>(new CommonResponse(
                    true,
                    "This category name is already exist",
                    null), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(new CommonResponse(
                true,
                "Record insert successfully",
                catEditDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CommonResponse> update(@RequestBody CatEditDTO catEditDTO) {
        catEditDTO = catService.update(catEditDTO);

        if (catEditDTO == null) {
            //        throw new Exception("category id not found",);

            return new ResponseEntity<>(new CommonResponse(
                    true,
                    "This id not found",
                    null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new CommonResponse(
                true,
                "Record updated successfully",
                catEditDTO), HttpStatus.OK);
    }

    @PatchMapping("/update-status/{cat_id}/{status}")
    public ResponseEntity<CommonResponse> changeStatus(@PathVariable int cat_id,
                                                       @PathVariable String status) {

        CatEditDTO catEditDTO = catService.changeStatus(cat_id, status);

        if (catEditDTO == null) {
            return new ResponseEntity<>(new CommonResponse(
                    true,
                    "This id not found",
                    null), HttpStatus.BAD_REQUEST);
        }

        return CommonResponse.success("Record update successfully", catEditDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CommonResponse> delete(@RequestParam int cat_id) {
        boolean result = catService.delete(cat_id);

        if (result == false) {
            return new ResponseEntity<>(new CommonResponse(
                    true,
                    "This id not found",
                    null), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CommonResponse(
                true,
                "This id has been deleted",
                null), HttpStatus.OK);
    }
}