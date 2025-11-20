package com.example.evsalesmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.evsalesmanagement.service.VehicleService;
import com.example.evsalesmanagement.service.VehicleTypeDetailService;
import com.example.evsalesmanagement.service.VehicleTypeService;
import com.example.evsalesmanagement.utils.ApiResponse;
import com.example.evsalesmanagement.dto.vehicle.VehicleRequestDTO;
import com.example.evsalesmanagement.dto.vehicle.VehicleResponseDTO;
import com.example.evsalesmanagement.dto.vehicle.VehicleSummaryDTO;
import com.example.evsalesmanagement.dto.vehicletype.VehicleTypeRequestDTO;
import com.example.evsalesmanagement.dto.vehicletype.VehicleTypeResponseDTO;
import com.example.evsalesmanagement.dto.vehicletype.VehicleTypeSummaryDTO;
import com.example.evsalesmanagement.dto.vehicletypedetail.VehicleTypeDetailRequestDTO;
import com.example.evsalesmanagement.dto.vehicletypedetail.VehicleTypeDetailResponseDTO;
import com.example.evsalesmanagement.dto.vehicletypedetail.VehicleTypeDetailSummaryDTO;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleTypeService vehicleTypeService;

    @Autowired
    private VehicleTypeDetailService vehicleTypeDetailService;

    // =============== VEHICLE ===============
    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF')")
    @PostMapping
    public ResponseEntity<ApiResponse<VehicleResponseDTO>> createVehicle(@RequestBody VehicleRequestDTO request) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.createVehicle(request);
        return ResponseEntity.ok(new ApiResponse<VehicleResponseDTO>(true, null, vehicleResponseDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
    @GetMapping
    public ResponseEntity<ApiResponse<List<VehicleSummaryDTO>>> getAllVehicle(@RequestParam Integer page,
            @RequestParam Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        List<VehicleSummaryDTO> vehicleSummaryDTOs = vehicleService.getAllVehicle(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, null, vehicleSummaryDTOs));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
    @GetMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse<VehicleResponseDTO>> getByIdVehicle(@PathVariable Integer vehicleId) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.getByIdVehicle(vehicleId);
        return ResponseEntity.ok(new ApiResponse<VehicleResponseDTO>(true, null, vehicleResponseDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF')")
    @PutMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse<VehicleResponseDTO>> updateVehicle(@PathVariable Integer vehicleId,
            @RequestBody VehicleRequestDTO request) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.updateVehicle(vehicleId, request);
        return ResponseEntity.ok(new ApiResponse<VehicleResponseDTO>(true, null, vehicleResponseDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF')")
    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse<VehicleResponseDTO>> deleteVehicle(@PathVariable Integer vehicleId) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.ok(new ApiResponse<VehicleResponseDTO>(true, null, vehicleResponseDTO));
    }

    // =============== VEHICLE TYPE ===============

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
    @GetMapping("/type")
    public ResponseEntity<ApiResponse<Page<VehicleTypeSummaryDTO>>> getAllVehicleType(@RequestParam Integer page,
            @RequestParam Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<VehicleTypeSummaryDTO> pageVehicleType = vehicleTypeService.getAllVehicleType(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, null, pageVehicleType));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
    @GetMapping("/type/{vehicleTypeId}")
    public ResponseEntity<ApiResponse<VehicleTypeResponseDTO>> getVehicleTypeById(@PathVariable Integer vehicleTypeId) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, vehicleTypeService.getVehicleTypeById(vehicleTypeId)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF')")
    @PostMapping("/type")
    public ResponseEntity<ApiResponse<VehicleTypeResponseDTO>> createVehicleType(
            @RequestBody VehicleTypeRequestDTO request) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, vehicleTypeService.createVehicleType(request)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF')")
    @PutMapping("/type/{vehicleTypeId}")
    public ResponseEntity<ApiResponse<VehicleTypeResponseDTO>> updateVehicleType(@PathVariable Integer vehicleTypeId,
            @RequestBody VehicleTypeRequestDTO request) {
        return ResponseEntity
                .ok(new ApiResponse<>(true, null, vehicleTypeService.updateVehicleType(vehicleTypeId, request)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF')")
    @DeleteMapping("/type/{vehicleTypeId}")
    public ResponseEntity<ApiResponse<VehicleTypeResponseDTO>> deleteVehicleType(@PathVariable Integer vehicleTypeId) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, vehicleTypeService.deleteVehicleType(vehicleTypeId)));
    }

    // =============== VEHICLE TYPE DETAIL ===============
    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
    @GetMapping("/type/detail")
    public ResponseEntity<ApiResponse<Page<VehicleTypeDetailSummaryDTO>>> getAllVehicleTypeDetail(
            @RequestParam Integer page, @RequestParam Integer size,
            @RequestParam(required = false) Integer vehicleTypeId) {
        Pageable pageable = PageRequest.of(page - 1, size);
        ApiResponse<Page<VehicleTypeDetailSummaryDTO>> result = vehicleTypeDetailService
                .getAllVehicleTypeDetails(pageable, vehicleTypeId);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
    @GetMapping("/type/detail/{vehicleTypeDetailId}")
    public ResponseEntity<ApiResponse<VehicleTypeDetailResponseDTO>> getVehicleTypeDetailById(
            @PathVariable Integer vehicleTypeDetailId) {
        return ResponseEntity.ok(vehicleTypeDetailService.getById(vehicleTypeDetailId));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF')")
    @PostMapping("/type/detail")
    public ResponseEntity<ApiResponse<VehicleTypeDetailResponseDTO>> createVehicleTypeDetail(
            @RequestBody VehicleTypeDetailRequestDTO request) {
        return ResponseEntity.ok(vehicleTypeDetailService.create(request));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF')")
    @PutMapping("/type/detail/{vehicleTypeDetailId}")
    public ResponseEntity<ApiResponse<VehicleTypeDetailResponseDTO>> updateVehicleTypeDetail(
            @PathVariable Integer vehicleTypeDetailId, @RequestBody VehicleTypeDetailRequestDTO request) {
        return ResponseEntity.ok(vehicleTypeDetailService.update(vehicleTypeDetailId, request));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF')")
    @DeleteMapping("/type/detail/{vehicleTypeDetailId}")
    public ResponseEntity<ApiResponse<VehicleTypeDetailResponseDTO>> deleteVehicleTypeDetail(
            @PathVariable Integer vehicleTypeDetailId) {
        return ResponseEntity.ok(vehicleTypeDetailService.delete(vehicleTypeDetailId));
    }
}
