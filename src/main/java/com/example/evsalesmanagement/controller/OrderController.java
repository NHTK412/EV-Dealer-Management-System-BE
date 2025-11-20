package com.example.evsalesmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.order.OrderFromQuoteRequestDTO;
import com.example.evsalesmanagement.dto.order.OrderResponseDTO;
import com.example.evsalesmanagement.dto.order.OrderSummaryDTO;
import com.example.evsalesmanagement.enums.OrderStatusEnum;
import com.example.evsalesmanagement.security.CustomerUserDetails;
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

    @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'ADMIN', 'DEADLER_STAFF')")
    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> getOrderById(@PathVariable Integer orderId) {

        OrderResponseDTO orderResponseDTO = orderService.getOrderById(orderId);

        return ResponseEntity.ok(new ApiResponse<OrderResponseDTO>(true, null, orderResponseDTO));
    }

    @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'ADMIN', 'DEADLER_STAFF')")
    @PostMapping("/from-quotation/{quoteId}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> createOrderFromQuotation(
            @AuthenticationPrincipal CustomerUserDetails customerUserDetails,
            @PathVariable Integer quoteId, @RequestParam(value = "", required = false) String note) {
        // public ResponseEntity<ApiResponse<OrderResponseDTO>>
        // createOrderFromQuotation(

        // @AuthenticationPrincipal CustomerUserDetails customerUserDetails,
        // @RequestBody OrderFromQuoteRequestDTO orderFromQuoteRequestDTO) {
        // OrderResponseDTO orderResponseDTO =
        // orderService.createOrderFromQuotation(orderFromQuoteRequestDTO);

        Integer employeeId = customerUserDetails.getEmployeeId();

        OrderResponseDTO orderResponseDTO = orderService.createOrderFromQuotation(employeeId, quoteId, note);

        return ResponseEntity.ok(new ApiResponse<OrderResponseDTO>(true, null, orderResponseDTO));
    }

    @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'ADMIN', 'DEADLER_STAFF')")
    @PatchMapping("{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> updateOrderById(@PathVariable Integer orderId,
            @RequestParam(required = false) OrderStatusEnum status,
            @RequestParam(required = false) String contractNumber) {

        OrderResponseDTO orderResponseDTO = orderService.updateOrderById(orderId, status, contractNumber);

        return ResponseEntity.ok(new ApiResponse<OrderResponseDTO>(true, null, orderResponseDTO));

    }

    @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'ADMIN', 'DEADLER_STAFF')")
    @GetMapping("/agency")
    public ResponseEntity<ApiResponse<List<OrderSummaryDTO>>> getOrdersByAgencyId(
            @AuthenticationPrincipal CustomerUserDetails customerUserDetails,
            @RequestParam int page,
            @RequestParam int size) {

        Integer agencyId = customerUserDetails.getAgencyId();

        Pageable pageable = PageRequest.of(page - 1, size);

        List<OrderSummaryDTO> orderResponseDTOs = orderService.getOrdersByAgencyId(agencyId, pageable);

        return ResponseEntity.ok(new ApiResponse<List<OrderSummaryDTO>>(true, null, orderResponseDTOs));
    }

    @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'ADMIN', 'DEADLER_STAFF')")
    @GetMapping("/employee")
    public ResponseEntity<ApiResponse<List<OrderSummaryDTO>>> getOrdersByEmployeeId(
            @AuthenticationPrincipal CustomerUserDetails customerUserDetails,
            @RequestParam int page,
            @RequestParam int size) {

        Integer employeeId = customerUserDetails.getEmployeeId();

        Pageable pageable = PageRequest.of(page - 1, size);

        List<OrderSummaryDTO> orderResponseDTOs = orderService.getOrdersByAgencyId(employeeId, pageable);

        return ResponseEntity.ok(new ApiResponse<List<OrderSummaryDTO>>(true, null, orderResponseDTOs));
    }

}
