package com.example.evsalesmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.evsalesmanagement.service.VehicleService;
import com.example.evsalesmanagement.service.VehicleTypeService;
import com.example.evsalesmanagement.utils.ApiResponse;
import com.example.evsalesmanagement.dto.vehicle.VehicleRequestDTO;
import com.example.evsalesmanagement.dto.vehicle.VehicleResponseDTO;
import com.example.evsalesmanagement.dto.vehicle.VehicleSummaryDTO;
import com.example.evsalesmanagement.dto.vehicletype.CreateVehicleTypeDTO;
import com.example.evsalesmanagement.dto.vehicletype.UpdateVehicleTypeDTO;
import com.example.evsalesmanagement.dto.vehicletype.VehicleTypeRequestDTO;
import com.example.evsalesmanagement.dto.vehicletype.VehicleTypeResponseDTO;
import com.example.evsalesmanagement.dto.vehicletype.VehicleTypeResponseDTO_v2;
import com.example.evsalesmanagement.dto.vehicletype.VehicleTypeSummaryDTO;
import com.example.evsalesmanagement.dto.vehicletypedetail.VehicleTypeDetailRequestDTO;
import com.example.evsalesmanagement.dto.vehicletypedetail.VehicleTypeDetailResponseDTO;
import com.example.evsalesmanagement.dto.vehicletypedetail.VehicleTypeDetailSummaryDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleTypeService vehicleTypeService;

    // =============== VEHICLE ===============
    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF')")
    @PostMapping
    public ResponseEntity<ApiResponse<VehicleResponseDTO>> createVehicle(@RequestBody VehicleRequestDTO request) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.createVehicle(request);
        return ResponseEntity.ok(new ApiResponse<VehicleResponseDTO>(true, null, vehicleResponseDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEALER_MANAGER')")
    @GetMapping
    public ResponseEntity<ApiResponse<Page<VehicleSummaryDTO>>> getAllVehicle(
            @RequestParam Integer page,
            @RequestParam Integer size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<VehicleSummaryDTO> vehiclePage = vehicleService.getAllVehicle(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, null, vehiclePage));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEALER_MANAGER')")
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

    // // =============== VEHICLE TYPE ===============
    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEALER_MANAGER')")
    @GetMapping("/type_v2")
    public ResponseEntity<ApiResponse<Page<VehicleTypeSummaryDTO>>> getAllVehicleTypeV2(@RequestParam Integer page,
            @RequestParam Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(vehicleTypeService.getAllVehicleType_v2(pageable));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEALER_MANAGER')")
    @GetMapping("/type_v2/{vehicleTypeId}")
    public ResponseEntity<ApiResponse<VehicleTypeResponseDTO_v2>> getVehicleTypeByIdV2(
            @PathVariable Integer vehicleTypeId) {
        return ResponseEntity.ok(vehicleTypeService.getVehicleTypeById_v2(vehicleTypeId));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF')")
    @PostMapping("/type_v2")
    public ResponseEntity<ApiResponse<VehicleTypeResponseDTO_v2>> createVehicleTypeV2(
            @RequestBody CreateVehicleTypeDTO createVehicleTypeDTO) {
        return ResponseEntity.ok(vehicleTypeService.createVehicleType_v2(createVehicleTypeDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF')")
    @PutMapping("/type_v2/{vehicleTypeId}")
    public ResponseEntity<ApiResponse<VehicleTypeResponseDTO_v2>> updateVehicleTypeV2(
            @PathVariable Integer vehicleTypeId,
            @RequestBody UpdateVehicleTypeDTO updateVehicleTypeDTO) {
        return ResponseEntity.ok(vehicleTypeService.updateVehicleType_v2(vehicleTypeId, updateVehicleTypeDTO));
    }

}
