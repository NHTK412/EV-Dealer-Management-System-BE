// package com.example.evsalesmanagement.controller;

// import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryRequestDTO;
// import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryResponseDTO;
// import com.example.evsalesmanagement.enums.VehicleDeliveryStatusEnum;
// import com.example.evsalesmanagement.service.VehicleDeliveryService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import jakarta.validation.Valid;
// import java.util.List;

// @RestController
// @RequestMapping("/vehicle-deliveries")
// public class VehicleDeliveryController {

//     @Autowired
//     private VehicleDeliveryService service;

//     @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
//     @PostMapping
//     public ResponseEntity<VehicleDeliveryResponseDTO> createDelivery(
//             @Valid @RequestBody VehicleDeliveryRequestDTO request) {

//         VehicleDeliveryResponseDTO createdDelivery = service.createVehicleDelivery(request);

//         return new ResponseEntity<>(createdDelivery, HttpStatus.CREATED);
//     }

//     @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
//     @PutMapping("/{id}")
//     public ResponseEntity<VehicleDeliveryResponseDTO> updateDelivery(
//             @PathVariable("id") Integer id,
//             @Valid @RequestBody VehicleDeliveryRequestDTO request) {

//         VehicleDeliveryResponseDTO updatedDelivery = service.updateVehicleDelivery(id, request);

//         return ResponseEntity.ok(updatedDelivery);
//     }

//     // Lấy tất cả giao xe
//     @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
//     @GetMapping
//     public ResponseEntity<List<VehicleDeliveryResponseDTO>> getAll() {
//         List<VehicleDeliveryResponseDTO> list = service.getAll();
//         return ResponseEntity.ok(list);
//     }

//     // Lấy giao xe theo trạng thái bất kỳ
//     @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
//     @GetMapping("/by-status")
//     public ResponseEntity<List<VehicleDeliveryResponseDTO>> getByStatus(
//             @RequestParam VehicleDeliveryStatusEnum status) {
//         List<VehicleDeliveryResponseDTO> list = service.getDeliveriesByStatus(status);
//         return ResponseEntity.ok(list);
//     }
// }