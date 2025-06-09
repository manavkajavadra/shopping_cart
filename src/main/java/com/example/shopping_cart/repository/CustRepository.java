package com.example.shopping_cart.repository;

import com.example.shopping_cart.entity.CustEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface CustRepository extends JpaRepository<CustEntity, Integer> {

    Optional<CustEntity> findByAadhaarNo(String aadhaarNo);

    Optional<CustEntity> findByCustName(String custName);

}