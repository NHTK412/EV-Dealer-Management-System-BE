package com.example.evsalesmanagement.controller;
import com.example.evsalesmanagement.dto.testdriveappointment.TestDriveAppointmentSummaryDTO;
import com.example.evsalesmanagement.service.TestDriveAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/test-drive-appointments")
public class TestDriveAppointmentController {

    @Autowired
    private TestDriveAppointmentService service;

    @PatchMapping("/{id}")
    public ResponseEntity<TestDriveAppointmentSummaryDTO> modifyAppointment(
            @PathVariable("id") Integer id,
            @Valid @RequestBody TestDriveAppointmentSummaryDTO request) {

        TestDriveAppointmentSummaryDTO updatedAppointment = service.modifyAppointment(id, request);
        
        return ResponseEntity.ok(updatedAppointment);
    }

    
    @PatchMapping("/{id}/confirm")
    public ResponseEntity<TestDriveAppointmentSummaryDTO> confirmAppointment(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.updateAppointmentStatus(id, "SCHEDULED"));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<TestDriveAppointmentSummaryDTO> cancelAppointment(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.updateAppointmentStatus(id, "CANCELLED"));
    }

    @PatchMapping("/{id}/arrive")
    public ResponseEntity<TestDriveAppointmentSummaryDTO> arriveAppointment(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.updateAppointmentStatus(id, "ARRIVED"));
    }
    
    // Lấy tất cả
    @GetMapping
    public ResponseEntity<List<TestDriveAppointmentSummaryDTO>> getAll() {
        List<TestDriveAppointmentSummaryDTO> list = service.getAllAppointments();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<TestDriveAppointmentSummaryDTO>> getByStatus(@RequestParam String status) {
        List<TestDriveAppointmentSummaryDTO> list = service.getAppointmentsByStatus(status);
        return ResponseEntity.ok(list);
    }
}