package com.example.shopping_cart.service;

import com.example.shopping_cart.request_dto.SubCatDTO;
import com.example.shopping_cart.request_dto.SubCatDTORespo;
import com.example.shopping_cart.request_dto.SubCatDTOUpdate;

import java.util.List;

public interface SubCatService {

    SubCatDTORespo insert(SubCatDTO subCatDTO, Integer createdBy);

    List<SubCatDTORespo> getList(String status);

    List<SubCatDTORespo> getListCatId(int CatId);

    SubCatDTORespo update(SubCatDTOUpdate subCatDTOUpdate);

    SubCatDTORespo changeStatus(int subCatId, String status);

}