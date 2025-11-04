package com.example.evsalesmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.evsalesmanagement.dto.Vehicle.VehicleRequestDTO;
import com.example.evsalesmanagement.dto.Vehicle.VehicleResponseDTO;
import com.example.evsalesmanagement.dto.Vehicle.VehicleSummaryDTO;
import com.example.evsalesmanagement.dto.Vehicle.VehicleTypeDTO;
import com.example.evsalesmanagement.dto.Vehicle.VehicleTypeDetailDTO;
import com.example.evsalesmanagement.service.VehicleService;
import com.example.evsalesmanagement.service.VehicleTypeDetailService;
import com.example.evsalesmanagement.service.VehicleTypeService;
import com.example.evsalesmanagement.utils.ApiResponse;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired private VehicleService vehicleService;

    @Autowired private VehicleTypeService vehicleTypeService;
    
    @Autowired private VehicleTypeDetailService vehicleTypeDetailService;

    // =============== VEHICLE ===============

    @PostMapping
    public ResponseEntity<ApiResponse<VehicleResponseDTO>> createVehicle(@RequestBody VehicleRequestDTO request) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.createVehicle(request);
        return ResponseEntity.ok(new ApiResponse<VehicleResponseDTO>(true, null, vehicleResponseDTO));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<VehicleSummaryDTO>>> getAllVehicle(@RequestParam Integer page, @RequestParam Integer size) {
        Pageable pageable = PageRequest.of(page - 1 , size);
        List<VehicleSummaryDTO> vehicleSummaryDTOs = vehicleService.getAllVehicle(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, null, vehicleSummaryDTOs));
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse<VehicleResponseDTO>> getByIdVehicle(@PathVariable Integer vehicleId) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.getByIdVehicle(vehicleId);
        return ResponseEntity.ok(new ApiResponse<VehicleResponseDTO>(true, null, vehicleResponseDTO));
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse<VehicleResponseDTO>> updateVehicle(@PathVariable Integer vehicleId, @RequestBody VehicleRequestDTO request) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.updateVehicle(vehicleId, request);
        return ResponseEntity.ok(new ApiResponse<VehicleResponseDTO>(true, null, vehicleResponseDTO));
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse<VehicleResponseDTO>> deleteVehicle(@PathVariable Integer vehicleId) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.ok(new ApiResponse<VehicleResponseDTO>(true, null, vehicleResponseDTO));
    }

    // =============== VEHICLE TYPE ===============

    @GetMapping("/type")
    public ResponseEntity<ApiResponse<Page<VehicleTypeDTO>>> getAllVehicleType(@RequestParam Integer page, @RequestParam Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<VehicleTypeDTO> pageVehicleType = vehicleTypeService.getAllVehicleType(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, null, pageVehicleType));
    }

    @GetMapping("/type/{vehicleTypeId}")
    public ResponseEntity<ApiResponse<VehicleTypeDTO>> getVehicleTypeById(@PathVariable Integer vehicleTypeId) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, vehicleTypeService.getVehicleTypeById(vehicleTypeId)));
    }

    @PostMapping("/type")
    public ResponseEntity<ApiResponse<VehicleTypeDTO>> createVehicleType(@RequestBody VehicleTypeDTO vehicleTypeDTO) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, vehicleTypeService.createVehicleType(vehicleTypeDTO)));
    }

    @PutMapping("/type/{vehicleTypeId}")
    public ResponseEntity<ApiResponse<VehicleTypeDTO>> updateVehicleType(@PathVariable Integer vehicleTypeId, @RequestBody VehicleTypeDTO vehicleTypeDTO) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, vehicleTypeService.updateVehicleType(vehicleTypeId, vehicleTypeDTO)));
    }

    @DeleteMapping("/type/{vehicleTypeId}")
    public ResponseEntity<ApiResponse<VehicleTypeDTO>> deleteVehicleType(@PathVariable Integer vehicleTypeId) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, vehicleTypeService.deleteVehicleType(vehicleTypeId)));
    }

    // =============== VEHICLE TYPE DETAIL ===============

    @GetMapping("/type/detail")
    public ResponseEntity<ApiResponse<Page<VehicleTypeDetailDTO>>> getAllVehicleTypeDetail(@RequestParam Integer page, @RequestParam Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<VehicleTypeDetailDTO> pageVehicleTypeDetail = vehicleTypeDetailService.getAllVehicleTypeDetail(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, null, pageVehicleTypeDetail));
    }

    @GetMapping("/type/detail/{vehicleTypeDetailId}")
    public ResponseEntity<ApiResponse<VehicleTypeDetailDTO>> getVehicleTypeDetailById(@PathVariable Integer vehicleTypeDetailId) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, vehicleTypeDetailService.getVehicleTypeDetailById(vehicleTypeDetailId)));
    }

    @PostMapping("/type/detail")
    public ResponseEntity<ApiResponse<VehicleTypeDetailDTO>> createVehicleTypeDetail(@RequestBody VehicleTypeDetailDTO dto) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, vehicleTypeDetailService.createVehicleTypeDetail(dto)));
    }

    @PutMapping("/type/detail/{vehicleTypeDetailId}")
    public ResponseEntity<ApiResponse<VehicleTypeDetailDTO>> updateVehicleTypeDetail(@PathVariable Integer vehicleTypeDetailId, @RequestBody VehicleTypeDetailDTO dto) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, vehicleTypeDetailService.updateVehicleTypeDetail(vehicleTypeDetailId, dto)));
    }

    @DeleteMapping("/type/detail/{vehicleTypeDetailId}")
    public ResponseEntity<ApiResponse<VehicleTypeDetailDTO>> deleteVehicleTypeDetail(@PathVariable Integer vehicleTypeDetailId) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, vehicleTypeDetailService.deleteVehicleTypeDetail(vehicleTypeDetailId)));
    }
}
