package com.example.shopping_cart.service_impl;

import com.example.shopping_cart.advice.MyCustomException;
import com.example.shopping_cart.entity.CatEntity;
import com.example.shopping_cart.repository.AdminRepository;
import com.example.shopping_cart.repository.CatRepository;
import com.example.shopping_cart.request_dto.CatDTO;
import com.example.shopping_cart.request_dto.CatDTOResponse;
import com.example.shopping_cart.request_dto.CatDTOUpdate;
import com.example.shopping_cart.service.CatService;
import com.example.shopping_cart.util.PdfGeneratorHelper;
import com.itextpdf.text.BaseColor;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.shopping_cart.util.CustomizeDateFormat.formatTimestamp;

@Slf4j
@Service
public class CatServiceImpl implements CatService {

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PdfGeneratorHelper pdfGeneratorHelper;

    //    @CacheEvict(value = "category", key = "#catDTO.catName")
    public CatDTOResponse insert(CatDTO catDTO, User user) {

        Optional<CatEntity> optionalCatEntity = catRepository.findByCatNameIgnoreCase(catDTO.getCatName());

        if (!optionalCatEntity.isEmpty()) {
            throw new MyCustomException("The Category Name'" + catDTO.getCatName() + "' Already Exists");
        }

        String adminName = user.getUsername();
        Integer createdBy = adminRepository.findByAdminName(adminName).get().getAdminId();

        CatEntity catEntity = modelMapper.map(catDTO, CatEntity.class);
        catEntity.setCreatedBy(createdBy);
        catEntity = catRepository.save(catEntity);
        CatDTOResponse catDTOResponse = modelMapper.map(catEntity, CatDTOResponse.class);
        String formattedDate = formatTimestamp(catEntity.getCreatedAt());
        catDTOResponse.setCreatedAt(formattedDate);

        return catDTOResponse;
    }

    //    @Cacheable(value = "categoryList", key = "#status")
    public List<CatDTOResponse> getList(String status) {

        List<CatDTOResponse> catDTOResponseList = new ArrayList<>();
        List<CatEntity> catEntityList;

        boolean isAllStatus = status == null || status.isEmpty() || status.equalsIgnoreCase("All");
        catEntityList = isAllStatus ? catRepository.findAll() : catRepository.findByStatus(status);

        for (CatEntity catEntity : catEntityList) {
            catDTOResponseList.add(modelMapper.map(catEntity, CatDTOResponse.class));
        }

        return catDTOResponseList;
    }

    //    @CachePut(value = "category", key = "#catDTOUpdate.catId")
    public CatDTOResponse update(CatDTOUpdate catDTOUpdate) {

        Optional<CatEntity> optionalCatEntity = catRepository.findById(catDTOUpdate.getCatId());
        if (optionalCatEntity.isEmpty()) {
            throw new MyCustomException("The Category Name'" + catDTOUpdate.getCatId() + "' Already Exists");
        }

        boolean isDuplicate = catRepository.existsByCatNameIgnoreCaseAndCatIdNot(
                catDTOUpdate.getCatName(),
                catDTOUpdate.getCatId());
        if (isDuplicate) {
        }

        CatEntity catEntity = modelMapper.map(catDTOUpdate, CatEntity.class);
        catEntity = catRepository.save(catEntity);

        return modelMapper.map(catEntity, CatDTOResponse.class);
    }

    //    @CachePut(value = "category", key = "#catId")
    public CatDTOResponse changeStatus(int catId, String status) {

        Optional<CatEntity> optionalCatEntity = catRepository.findById(catId);

        if (optionalCatEntity.isEmpty()) {

        }

        CatEntity catEntity = optionalCatEntity.get();
        catEntity.setStatus(status);
        catEntity = catRepository.save(catEntity);

        return modelMapper.map(catEntity, CatDTOResponse.class);
    }

    //    @CacheEvict(value = "category", key = "#catId")
    public boolean delete(int catId) {
        Optional<CatEntity> catEntity = catRepository.findById(catId);

        if (catEntity.isEmpty()) {
        }

        catRepository.deleteById(catId);

        return true;
    }

    @Override
    @Transactional
    @CachePut(value = "catCache", key = "#result != null && !#result.isEmpty() ? #result[0].catId : 0")
    public void exportCategoryPdf () {

        // Step 1: Fetch data from DB
        List<CatEntity> catEntityList = catRepository.findAll();

        if (catEntityList.isEmpty()) {
            throw new RuntimeException("No categories found in database.");
        }

        // Step 2: Convert Entity to DTO
        List<CatDTO> dtoList = catEntityList.stream()
                .map(entity -> modelMapper.map(entity, CatDTO.class))
                .collect(Collectors.toList());

        // Step 3: Setup styling and export to PDF
        String fileDestinationPath = "D:\\shopping_cart_jwt_demo\\shopping_cart\\pdf\\category_list.pdf";
        String bgImagePath = "D:\\shopping_cart_jwt_demo\\shopping_cart\\pdf-background-image\\bg-1.jpg";

        try {
            pdfGeneratorHelper
                    .setBackgroundImagePath(bgImagePath,0.3f)
                    .setBackgroundColor(BaseColor.LIGHT_GRAY)
                    .setWatermarkText("WATERMARK", 0.15f, 40, 45)
                    .setWatermarkTextColor(BaseColor.LIGHT_GRAY)
                    .exportToPdfWithStyling(dtoList, fileDestinationPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}