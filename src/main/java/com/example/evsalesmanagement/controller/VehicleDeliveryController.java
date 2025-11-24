package com.example.evsalesmanagement.controller;

import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryRequestDTO;
import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryResponseDTO;
import com.example.evsalesmanagement.enums.VehicleDeliveryStatusEnum;
import com.example.evsalesmanagement.service.VehicleDeliveryService;
import com.example.evsalesmanagement.utils.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}