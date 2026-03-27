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
import com.example.evsalesmanagement.dto.order.OrderRequestDTO;
import com.example.evsalesmanagement.dto.order.OrderResponseDTO;
import com.example.evsalesmanagement.dto.order.OrderSummaryDTO;
import com.example.evsalesmanagement.dto.payment.PaymentRequestDTO;
import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryRequestDTO;
import com.example.evsalesmanagement.enums.OrderStatusEnum;
import com.example.evsalesmanagement.enums.VehicleDeliveryStatusEnum;
import com.example.evsalesmanagement.repository.WarehouseReleaseNoteRepository;
import com.example.evsalesmanagement.security.CustomerUserDetails;
import com.example.evsalesmanagement.service.OrderService;
import com.example.evsalesmanagement.utils.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'ADMIN', 'DEALER_STAFF')")
    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> getOrderById(@PathVariable Integer orderId) {

        OrderResponseDTO orderResponseDTO = orderService.getOrderById(orderId);

        return ResponseEntity.ok(new ApiResponse<OrderResponseDTO>(true, null, orderResponseDTO));
    }

    @PreAuthorize("hasAnyRole('DEALER_MANAGER','DEALER_STAFF')")
    @PostMapping("/from-quotation")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> createOrderFromQuotation(
            @AuthenticationPrincipal CustomerUserDetails customerUserDetails,
            @RequestBody OrderFromQuoteRequestDTO orderFromQuoteRequestDTO) {
        Integer employeeId = customerUserDetails.getEmployeeId();

        OrderResponseDTO orderResponseDTO = orderService.createOrderFromQuotation(employeeId, orderFromQuoteRequestDTO);

        return ResponseEntity.ok(new ApiResponse<OrderResponseDTO>(true, null, orderResponseDTO));
    }

    @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'ADMIN', 'DEALER_STAFF')")
    @PatchMapping("{orderId}/cancel")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> cancelOrderById(@PathVariable Integer orderId) {

        OrderResponseDTO orderResponseDTO = orderService.cancelOrderById(orderId);
        return ResponseEntity.ok(new ApiResponse<OrderResponseDTO>(true, null, orderResponseDTO));
    }

    @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'ADMIN', 'DEALER_STAFF')")
    @GetMapping("/agency")
    public ResponseEntity<ApiResponse<List<OrderSummaryDTO>>> getOrdersByAgencyId(
            @AuthenticationPrincipal CustomerUserDetails customerUserDetails,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String customerPhone) {

        Integer agencyId = customerUserDetails.getAgencyId();

        Pageable pageable = PageRequest.of(page - 1, size);

        List<OrderSummaryDTO> orderResponseDTOs = orderService.getOrdersByAgencyId(agencyId, pageable);

        return ResponseEntity.ok(new ApiResponse<List<OrderSummaryDTO>>(true, null, orderResponseDTOs));
    }

    @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'ADMIN', 'DEALER_STAFF')")
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

    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponseDTO>> createOrder(
            @AuthenticationPrincipal CustomerUserDetails customerUserDetails,
            @RequestBody OrderRequestDTO orderRequestDTO) {

        Integer employeeId = customerUserDetails.getEmployeeId();

        OrderResponseDTO orderResponseDTO = orderService.createOrder(employeeId, orderRequestDTO);

        return ResponseEntity.ok(new ApiResponse<>(true, null, orderResponseDTO));

    }

    @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'ADMIN', 'DEALER_STAFF')")
    @GetMapping("/customer")
    public ResponseEntity<ApiResponse<List<OrderSummaryDTO>>> getOrderByCustomer(
            @AuthenticationPrincipal CustomerUserDetails customerUserDetails, @RequestParam Integer customerId,
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        Integer agencyId = customerUserDetails.getAgencyId();

        List<OrderSummaryDTO> orderResponseDTOs = orderService.getOrdersByCustomerId(agencyId, customerId, pageable);

        return ResponseEntity.ok(new ApiResponse<List<OrderSummaryDTO>>(true, null, orderResponseDTOs));
    }

}
