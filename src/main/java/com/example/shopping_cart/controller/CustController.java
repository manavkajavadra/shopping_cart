package com.example.shopping_cart.controller;

import com.example.shopping_cart.request_dto.CustDTO;
import com.example.shopping_cart.request_dto.CustLoginDTO;
import com.example.shopping_cart.comman_response_dto.CommonResponse;
import com.example.shopping_cart.comman_response_dto.ResGenerator;
import com.example.shopping_cart.security.JwtHelper;
import com.example.shopping_cart.service_impl.CustServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@Tag(name = "Customer API", description = "For CRUD operation of customers.")


public class CustController {

    @Autowired
    CustServiceImpl customerServiceImpl;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse> signup(@Valid @RequestBody CustDTO custDTO,
                                                 BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldError().getDefaultMessage();
        }

        customerServiceImpl.signup(custDTO);

        return ResGenerator.success("Signup successfully", null);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse> login(@Valid @RequestBody CustLoginDTO custLoginDTO,
                                                BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldError().getDefaultMessage();
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(custLoginDTO.getCustName(),custLoginDTO.getPswd()));
        String token = JwtHelper.generateToken(custLoginDTO.getCustName());

        return ResGenerator.success("Login successful", token);
    }
}