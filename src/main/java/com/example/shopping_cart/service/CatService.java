package com.example.shopping_cart.service;

import com.example.shopping_cart.request_dto.CatDTO;
import com.example.shopping_cart.request_dto.CatDTOResponse;
import com.example.shopping_cart.request_dto.CatDTOUpdate;
import org.springframework.security.core.userdetails.User;

import java.util.List;


public interface CatService {

    CatDTOResponse insert(CatDTO catDTO, User user);

    List<CatDTOResponse> getList(String status);

    CatDTOResponse update(CatDTOUpdate catDTOUpdate);

    CatDTOResponse changeStatus(int catId, String status);

    boolean delete(int catId);

    void exportCategoryPdf();

}
