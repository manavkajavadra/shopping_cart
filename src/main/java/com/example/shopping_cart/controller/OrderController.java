package com.example.shopping_cart.controller;

import com.example.shopping_cart.repository.CustRepository;
import com.example.shopping_cart.request_dto.OrderItemDTO;
import com.example.shopping_cart.comman_response_dto.CommonResponse;
import com.example.shopping_cart.comman_response_dto.ResGenerator;
import com.example.shopping_cart.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j

@RestController
@RequestMapping("/customer/order")
@Tag(name = "Order API", description = "For CRUD operation of orders.")


public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CustRepository custRepository;


    @PostMapping("/insert")
    public ResponseEntity<CommonResponse> insert(@RequestBody List<OrderItemDTO> itemsList,
                                                 @AuthenticationPrincipal User user) {

        String custName = user.getUsername();
        Integer custId = custRepository.findByCustName(custName).get().getCustId();

        return ResGenerator.orderPlaced("Order placed successfully", orderService.insert(itemsList, custId));
    }

    @GetMapping("/get-order")
    public ResponseEntity<CommonResponse> getOrder(@RequestParam(required = false) Integer orderId) {

        return ResGenerator.success("Your order detail", orderService.getOrder(orderId));
    }

    @PatchMapping("/change-status/{orderId}/{status}")
    public ResponseEntity<CommonResponse> changeStatus(@PathVariable Integer orderId,
                                                       @PathVariable String status) {

        return ResGenerator.success("Status updated successfully", orderService.changeStatus(orderId, status));
    }
}
