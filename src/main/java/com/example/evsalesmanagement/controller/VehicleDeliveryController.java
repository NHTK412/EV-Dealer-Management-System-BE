package com.example.evsalesmanagement.controller;

import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryDTO;
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

    //Lấy tất cả giao xe
    @GetMapping
    public ResponseEntity<List<VehicleDeliveryDTO>> getAll() {
        List<VehicleDeliveryDTO> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    //Lấy giao xe theo trạng thái Pending
    @GetMapping("/pending")
    public ResponseEntity<List<VehicleDeliveryDTO>> getPending() {
        List<VehicleDeliveryDTO> list = service.getPendingDeliveries();
        return ResponseEntity.ok(list);
    }

    //Lấy giao xe theo trạng thái In Progress
    @GetMapping("/in-progress")
    public ResponseEntity<List<VehicleDeliveryDTO>> getInProgress() {
        List<VehicleDeliveryDTO> list = service.getInProgressDeliveries();
        return ResponseEntity.ok(list);
    }

    //Lấy giao xe theo trạng thái Completed
    @GetMapping("/completed")
    public ResponseEntity<List<VehicleDeliveryDTO>> getCompleted() {
        List<VehicleDeliveryDTO> list = service.getCompletedDeliveries();
        return ResponseEntity.ok(list);
    }

    //Lấy giao xe theo trạng thái bất kỳ (truyền enum)
    @GetMapping("/by-status")
    public ResponseEntity<List<VehicleDeliveryDTO>> getByStatus(@RequestParam VehicleDeliveryEnum status) {
        List<VehicleDeliveryDTO> list = service.getByStatus(status);
        return ResponseEntity.ok(list);
    }
}
