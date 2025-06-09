package com.example.shopping_cart.service;

import com.example.shopping_cart.request_dto.ItemDTOResponse;
import com.example.shopping_cart.request_dto.ItemDTOUpdate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ItemService {

    Object insert(Integer subCatId, String itemName, Integer stockQty, Integer price, String expDate, String status, MultipartFile photo) throws IOException;

    List<Map<String, Object>> getList(String status);

    ItemDTOResponse update(ItemDTOUpdate itemDTOUpdate);

    ItemDTOResponse changeStatus(Integer itemId, String status);

    boolean delete(Integer itemId);

}