package com.example.evsalesmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.order.OrderFromQuoteRequestDTO;
import com.example.evsalesmanagement.dto.order.OrderResponseDTO;
import com.example.evsalesmanagement.enums.OrderStatusEnum;
import com.example.evsalesmanagement.service.OrderService;
import com.example.evsalesmanagement.utils.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @PostMapping("/from-quotation")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> createOrderFromQuotation(
            @RequestBody OrderFromQuoteRequestDTO orderFromQuoteRequestDTO) {
        OrderResponseDTO orderResponseDTO = orderService.createOrderFromQuotation(orderFromQuoteRequestDTO);

        return ResponseEntity.ok(new ApiResponse<OrderResponseDTO>(true, null, orderResponseDTO));
    }

    @PatchMapping("{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> updateOrderById(@PathVariable Integer orderId,
            @RequestParam(required = false) OrderStatusEnum status,
            @RequestParam(required = false) String contractNumber) {

        OrderResponseDTO orderResponseDTO = orderService.updateOrderById(orderId, status, contractNumber);

        return ResponseEntity.ok(new ApiResponse<OrderResponseDTO>(true, null, orderResponseDTO));

    }
}
