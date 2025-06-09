package com.example.shopping_cart.service_impl;

import com.example.shopping_cart.entity.CustEntity;
import com.example.shopping_cart.repository.CustRepository;
import com.example.shopping_cart.request_dto.CustDTO;
import com.example.shopping_cart.request_dto.CustDTOResponse;
import com.example.shopping_cart.request_dto.CustDTOUpdate;
import com.example.shopping_cart.service.CustService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service

public class CustServiceImpl implements CustService {

    @Autowired
    CustRepository custRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    public CustDTOResponse signup(CustDTO custDTO) {

        Optional<CustEntity> optionalCustEntity = custRepository.findByAadhaarNo(custDTO.getAadhaarNo());
        if (optionalCustEntity.isPresent()) {
        }

        CustEntity custEntity = modelMapper.map(custDTO, CustEntity.class);
        custEntity.setPswd(passwordEncoder.encode(custDTO.getPswd()));

        return modelMapper.map(custRepository.save(custEntity), CustDTOResponse.class);

    }

    @Cacheable(value = "customer")
    public List<CustDTOResponse> getList() {

        List<CustEntity> custEntityList = custRepository.findAll();

        List<CustDTOResponse> customerDTOResponseList = new ArrayList<>();

        for (CustEntity custEntity : custEntityList) {
            customerDTOResponseList.add(modelMapper.map(custEntity, CustDTOResponse.class));
        }

        return customerDTOResponseList;

    }

    public CustDTOResponse update(CustDTOUpdate custDTOUpdate) {

        Optional<CustEntity> optionalCustEntity = custRepository.findById(custDTOUpdate.getCustId());
        if (optionalCustEntity.isEmpty()) {
        }

        Optional<CustEntity> duplicateAadhaarNo = custRepository.findByAadhaarNo(custDTOUpdate.getAadhaarNo());
        if (duplicateAadhaarNo.isPresent() && !duplicateAadhaarNo.get().getCustId().equals(custDTOUpdate.getCustId())) {
        }

        CustEntity custEntity = optionalCustEntity.get();
        custEntity.setCustName(custDTOUpdate.getCustName());
        custEntity.setAadhaarNo(custDTOUpdate.getAadhaarNo());
        custEntity.setAddress(custDTOUpdate.getAddress());

        custEntity = custRepository.save(custEntity);

        return modelMapper.map(custEntity, CustDTOResponse.class);
    }

    public boolean delete(Integer custId) {

        Optional<CustEntity> optionalCustEntity = custRepository.findById(custId);
        if (optionalCustEntity.isEmpty()) {
        }
        custRepository.deleteById(custId);
        return true;
    }
}