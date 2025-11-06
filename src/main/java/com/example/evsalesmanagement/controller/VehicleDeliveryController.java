package com.example.evsalesmanagement.controller;

import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryResponseDTO;
import com.example.evsalesmanagement.enums.VehicleDeliveryEnum;
import com.example.evsalesmanagement.service.VehicleDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle-deliveries")
public class VehicleDeliveryController {

    @Autowired
    private VehicleDeliveryService service;

    // Lấy tất cả giao xe
    @GetMapping
    public ResponseEntity<List<VehicleDeliveryResponseDTO>> getAll() {
        List<VehicleDeliveryResponseDTO> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    // Lấy giao xe theo trạng thái Pending
    @GetMapping("/pending")
    public ResponseEntity<List<VehicleDeliveryResponseDTO>> getPending() {
        List<VehicleDeliveryResponseDTO> list = service.getPendingDeliveries();
        return ResponseEntity.ok(list);
    }

    // Lấy giao xe theo trạng thái In Progress
    @GetMapping("/in-progress")
    public ResponseEntity<List<VehicleDeliveryResponseDTO>> getInProgress() {
        List<VehicleDeliveryResponseDTO> list = service.getInProgressDeliveries();
        return ResponseEntity.ok(list);
    }

    // Lấy giao xe theo trạng thái Completed
    @GetMapping("/completed")
    public ResponseEntity<List<VehicleDeliveryResponseDTO>> getCompleted() {
        List<VehicleDeliveryResponseDTO> list = service.getCompletedDeliveries();
        return ResponseEntity.ok(list);
    }

    // Lấy giao xe theo trạng thái bất kỳ (truyền enum)
    @GetMapping("/by-status")
    public ResponseEntity<List<VehicleDeliveryResponseDTO>> getByStatus(@RequestParam VehicleDeliveryEnum status) {
        List<VehicleDeliveryResponseDTO> list = service.getByStatus(status);
        return ResponseEntity.ok(list);
    }
}
