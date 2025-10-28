package com.example.evsalesmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.VehicleDTO;
import com.example.evsalesmanagement.model.Vehicle;
import com.example.evsalesmanagement.service.VehicleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping
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

    @GetMapping
    ResponseEntity<ApiResponse<List<Vehicle>>> getAllVehicle() {
        // return xeService.getAllXe();
        return ResponseEntity.ok(new ApiResponse<List<Vehicle>>(true, null, vehicleService.getAllVehicle()));

    }

    @GetMapping("/{maXe}")
    ResponseEntity<ApiResponse<Vehicle>> getVehicleById(@PathVariable Integer maXe) {
        // return xeService.getXeById(maXe);
        return ResponseEntity.ok(new ApiResponse<Vehicle>(true, null, vehicleService.getVehicleById(maXe)));

    }

    @PutMapping("/{maXe}")
    ResponseEntity<ApiResponse<Vehicle>> updateVehicle(@PathVariable Integer vehicleId, @RequestBody VehicleDTO request) {
        // return xeService.updateXe(maXe, request);
        return ResponseEntity.ok(new ApiResponse<Vehicle>(true, null, vehicleService.updateVehicle(vehicleId, request)));

    }

    @DeleteMapping("/{maXe}")
    String deleteVehicle(@PathVariable Integer vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return "Deleted Successfully";
    }

}
