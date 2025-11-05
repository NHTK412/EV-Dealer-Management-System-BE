package com.example.evsalesmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.order.OrderResponseDTO;
import com.example.evsalesmanagement.service.OrderService;
import com.example.evsalesmanagement.utils.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> getOrderById(@PathVariable Integer orderId) {

        OrderResponseDTO orderResponseDTO = orderService.getOrderById(orderId);

        return ResponseEntity.ok(new ApiResponse<OrderResponseDTO>(true, null, orderResponseDTO));
    }

}
