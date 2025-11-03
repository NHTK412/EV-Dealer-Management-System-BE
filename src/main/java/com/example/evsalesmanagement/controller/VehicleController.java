package com.example.evsalesmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.evsalesmanagement.dto.VehicleDTO;
import com.example.evsalesmanagement.dto.VehicleTypeDTO;
import com.example.evsalesmanagement.dto.VehicleTypeDetailDTO;
import com.example.evsalesmanagement.model.Vehicle;
import com.example.evsalesmanagement.service.VehicleService;
import com.example.evsalesmanagement.service.VehicleTypeDetailService;
import com.example.evsalesmanagement.service.VehicleTypeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import com.example.evsalesmanagement.utils.ApiResponse;



@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleTypeService vehicleTypeService;

    @Autowired
    private VehicleTypeDetailService vehicleTypeDetailService;

    @PostMapping()
    ResponseEntity<ApiResponse<Vehicle>> createVehicle(@RequestBody VehicleDTO request) {
        // return xeService.createXe(request);

        return ResponseEntity.ok(new ApiResponse<Vehicle>(true, null, vehicleService.createVehicle(request)));

    }

    @PostMapping("/batch")
    public ResponseEntity<ApiResponse<List<Vehicle>>> createVehicleBatch(@RequestBody List<VehicleDTO> requests) {
        List<Vehicle> result = new ArrayList<>();
        for (VehicleDTO req : requests) {
            result.add(vehicleService.createVehicle(req));
        }
        return ResponseEntity.ok(new ApiResponse<List<Vehicle>>(true, null, result));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<Page<VehicleDTO>>> getAllVehicle(
        @RequestParam Integer page,
        @RequestParam Integer size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<VehicleDTO> pageVehicle = vehicleService.getAllVehicle(pageable);
    return ResponseEntity.ok(new ApiResponse<>(true, null, pageVehicle));
    }

    @GetMapping("/{vehicleId}")
    ResponseEntity<ApiResponse<Vehicle>> getVehicleById(@PathVariable Integer maXe) {
        // return xeService.getXeById(maXe);
        return ResponseEntity.ok(new ApiResponse<Vehicle>(true, null, vehicleService.getVehicleById(maXe)));

    }

    @PutMapping("/{vehicleId}")
    ResponseEntity<ApiResponse<Vehicle>> updateVehicle(@PathVariable Integer vehicleId, @RequestBody VehicleDTO request) {
        // return xeService.updateXe(maXe, request);
        return ResponseEntity.ok(new ApiResponse<Vehicle>(true, null, vehicleService.updateVehicle(vehicleId, request)));

    }

    @DeleteMapping("/{vehicleId}")
    String deleteVehicle(@PathVariable Integer vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return "Deleted Successfully";
    }


    @GetMapping()
    public ResponseEntity<ApiResponse<Page<VehicleTypeDTO>>> getAllVehicleType(
        @RequestParam Integer page,
        @RequestParam Integer size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<VehicleTypeDTO> pageVehicleType = vehicleTypeService.getAllVehicleType(pageable);
    return ResponseEntity.ok(new ApiResponse<>(true, null, pageVehicleType));
    }
   
     @GetMapping()
    public ResponseEntity<ApiResponse<Page<VehicleTypeDetailDTO>>> getAllVehicleTypeDetail(
        @RequestParam Integer page,
        @RequestParam Integer size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<VehicleTypeDetailDTO> pageVehicleTypeDetail = vehicleTypeDetailService.getAllVehicleTypeDetail(pageable);
    return ResponseEntity.ok(new ApiResponse<>(true, null, pageVehicleTypeDetail));
    }
    
    @GetMapping("/{vehicleTypeId}")
    public ResponseEntity<ApiResponse<VehicleTypeDTO>> getVehicleTypeById(@PathVariable Integer vehicleTypeId) {

        VehicleTypeDTO vehicleTypeDTO = vehicleTypeService.getVehicleTypeById(vehicleTypeId);

        return ResponseEntity.ok(new ApiResponse<VehicleTypeDTO>(true, null, vehicleTypeDTO));
    }
    
    @GetMapping("/{vehicleTypeDetailId}")
    public ResponseEntity<ApiResponse<VehicleTypeDetailDTO>> getVehicleTypeDetailById(@PathVariable Integer vehicleTypeDetailId) {
        
        VehicleTypeDetailDTO vehicleTypeDetailDTO = vehicleTypeDetailService.getVehicleTypeDetailById(vehicleTypeDetailId);
       
        return ResponseEntity.ok(new ApiResponse<VehicleTypeDetailDTO>(true, null, vehicleTypeDetailDTO));
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<VehicleTypeDTO>> createVehicleType(
                    @RequestBody VehicleTypeDTO vehicleTypeDTO) {

        VehicleTypeDTO createdVehicleTypeDTO = vehicleTypeService.createVehicleType(vehicleTypeDTO);
        return ResponseEntity.ok(new ApiResponse<VehicleTypeDTO>(true, null, createdVehicleTypeDTO));
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<VehicleTypeDetailDTO>> createVehicleTypeDetail(
                    @RequestBody VehicleTypeDetailDTO vehicleTypeDetailDTO) {
        VehicleTypeDetailDTO createdVehicleTypeDetailDTO = vehicleTypeDetailService.createVehicleTypeDetail(vehicleTypeDetailDTO);
        return ResponseEntity.ok(new ApiResponse<VehicleTypeDetailDTO>(true, null, createdVehicleTypeDetailDTO));
    }

    @PutMapping("/{vehicleTypeId}")
    public ResponseEntity<ApiResponse<VehicleTypeDTO>> updateVehicleType(@PathVariable Integer vehicleTypeId,
                    @RequestBody VehicleTypeDTO vehicleTypeDTO) {   
        VehicleTypeDTO updatedVehicleTypeDTO = vehicleTypeService.updateVehicleType(vehicleTypeId, vehicleTypeDTO);
        return ResponseEntity.ok(new ApiResponse<VehicleTypeDTO>(true, null, updatedVehicleTypeDTO));
    }   

    @PutMapping("/{vehicleTypeDetailId}")
    public ResponseEntity<ApiResponse<VehicleTypeDetailDTO>> updateVehicleTypeDetail(@PathVariable Integer vehicleTypeDetailId,
                    @RequestBody VehicleTypeDetailDTO vehicleTypeDetailDTO) {   
        VehicleTypeDetailDTO updatedVehicleTypeDetailDTO = vehicleTypeDetailService.updateVehicleTypeDetail(vehicleTypeDetailId, vehicleTypeDetailDTO);
        return ResponseEntity.ok(new ApiResponse<VehicleTypeDetailDTO>(true, null, updatedVehicleTypeDetailDTO));
    }

    @DeleteMapping("/{vehicleTypeId}")
    public ResponseEntity<ApiResponse<VehicleTypeDTO>> deleteVehicleType(@PathVariable Integer vehicleTypeId) {
        VehicleTypeDTO deletedVehicleTypeDTO = vehicleTypeService.deleteVehicleType(vehicleTypeId);
        return ResponseEntity.ok(new ApiResponse<VehicleTypeDTO>(true, null, deletedVehicleTypeDTO));
    }   

    @DeleteMapping("/{vehicleTypeDetailId}")
    public ResponseEntity<ApiResponse<VehicleTypeDetailDTO>> deleteVehicleTypeDetail(@PathVariable Integer vehicleTypeDetailId) {
        VehicleTypeDetailDTO deletedVehicleTypeDetailDTO = vehicleTypeDetailService.deleteVehicleTypeDetail(vehicleTypeDetailId);
        return ResponseEntity.ok(new ApiResponse<VehicleTypeDetailDTO>(true, null, deletedVehicleTypeDetailDTO));       
    }
    

}
