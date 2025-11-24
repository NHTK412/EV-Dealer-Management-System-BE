package com.example.evsalesmanagement.controller;

import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryRequestDTO;
import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryResponseDTO;
import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliverySummaryDTO;
import com.example.evsalesmanagement.enums.VehicleDeliveryStatusEnum;
import com.example.evsalesmanagement.service.VehicleDeliveryService;
import com.example.evsalesmanagement.utils.ApiResponse;

import jakarta.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle-deliveries")
public class VehicleDeliveryController {

    @Autowired
    private VehicleDeliveryService vehicleDeliveryService;

    @PostMapping
    public ResponseEntity<ApiResponse<VehicleDeliveryResponseDTO>> createVehicleDelivery(
            @RequestBody VehicleDeliveryRequestDTO vehicleDeliveryRequestDTO) {

        VehicleDeliveryResponseDTO vehicleDeliveryResponseDTO = vehicleDeliveryService
                .createVehicleDelivery(vehicleDeliveryRequestDTO);

        return ResponseEntity.ok(new ApiResponse<>(true, null, vehicleDeliveryResponseDTO));

    }

    @PatchMapping("/{vehicleDeliveryId}/status")
    public ResponseEntity<ApiResponse<VehicleDeliveryResponseDTO>> updateStatus(
            @PathVariable Integer vehicleDeliveryId,
            @RequestParam VehicleDeliveryStatusEnum status) {

        VehicleDeliveryResponseDTO vehicleDeliveryResponseDTO = vehicleDeliveryService
                .updateStatus(vehicleDeliveryId, status);

        return ResponseEntity.ok(new ApiResponse<>(true, null, vehicleDeliveryResponseDTO));
    }

    // Lấy danh sách đơn giao hàng của đại lý
    @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'DEALER_STAFF')")
    @GetMapping("/agency/{agencyId}")
    public ResponseEntity<ApiResponse<List<VehicleDeliverySummaryDTO>>> getAllByAgency(
            @PathVariable Integer agencyId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") @Positive Integer size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        List<VehicleDeliverySummaryDTO> deliveries = vehicleDeliveryService.getAllByAgencyId(agencyId, pageable);

        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy danh sách đơn giao hàng thành công", deliveries));
    }

    // Lấy chi tiết đơn giao hàng của đại lý
    @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'DEALER_STAFF')")
    @GetMapping("/agency/{agencyId}/{vehicleDeliveryId}")
    public ResponseEntity<ApiResponse<VehicleDeliveryResponseDTO>> getByIdAndAgency(
            @PathVariable Integer agencyId,
            @PathVariable Integer vehicleDeliveryId) {

        VehicleDeliveryResponseDTO delivery = vehicleDeliveryService
                .getByIdAndAgencyId(vehicleDeliveryId, agencyId);

        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy chi tiết đơn giao hàng thành công", delivery));
    }

    // Lấy chi tiết đơn giao hàng (admin/staff)
    @PreAuthorize("hasAnyRole('ADMIN', 'EVM_STAFF')")
    @GetMapping("/{vehicleDeliveryId}")
    public ResponseEntity<ApiResponse<VehicleDeliveryResponseDTO>> getById(
            @PathVariable Integer vehicleDeliveryId) {

        VehicleDeliveryResponseDTO delivery = vehicleDeliveryService.getById(vehicleDeliveryId);

        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy chi tiết đơn giao hàng thành công", delivery));
    }

}