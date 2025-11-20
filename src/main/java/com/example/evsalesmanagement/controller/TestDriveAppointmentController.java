package com.example.evsalesmanagement.controller;

import com.example.evsalesmanagement.dto.testdriveappointment.TestDriveAppointmentResponseDTO;
import com.example.evsalesmanagement.dto.testdriveappointment.TestDriveAppointmentSummaryDTO;
import com.example.evsalesmanagement.service.TestDriveAppointmentService;
import com.example.evsalesmanagement.utils.ApiResponse;

// import org.springdoc.core.converters.models.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/test-drive-appointments")
public class TestDriveAppointmentController {

    @Autowired
    private TestDriveAppointmentService service;

    // @PutMapping("/{id}")
    // public ResponseEntity<ApiResponse<TestDriveAppointmentSummaryDTO>>
    // createTestDriveAppointment(
    // @Valid @RequestBody TestDriveAppointmentSummaryDTO request) {

    // // TestDriveAppointmentSummaryDTO updatedAppointment =
    // service.modifyAppointment(id, request);

    // TestDriveAppointmentSummaryDTO updatedAppointment =
    // service.modifyAppointment(request.getTestDriveAppointmentId(), request);

    // return ResponseEntity.ok(updatedAppointment);
    // }

    // @PatchMapping("/{id}/confirm")
    // public ResponseEntity<TestDriveAppointmentSummaryDTO>
    // confirmAppointment(@PathVariable("id") Integer id) {
    // return ResponseEntity.ok(service.updateAppointmentStatus(id, "SCHEDULED"));
    // }

    // @PatchMapping("/{id}/cancel")
    // public ResponseEntity<TestDriveAppointmentSummaryDTO>
    // cancelAppointment(@PathVariable("id") Integer id) {
    // return ResponseEntity.ok(service.updateAppointmentStatus(id, "CANCELLED"));
    // }

    // @PatchMapping("/{id}/arrive")
    // public ResponseEntity<TestDriveAppointmentSummaryDTO>
    // arriveAppointment(@PathVariable("id") Integer id) {
    // return ResponseEntity.ok(service.updateAppointmentStatus(id, "ARRIVED"));
    // }

    @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'DEALER_STAFF')")
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<TestDriveAppointmentSummaryDTO>> arriveAppointment(@PathVariable("id") Integer id,
            @RequestParam String status) {
        TestDriveAppointmentSummaryDTO updatedAppointment = service.updateAppointmentStatus(id, status);

        ApiResponse<TestDriveAppointmentSummaryDTO> response = new ApiResponse<>(true, null, updatedAppointment);

        return ResponseEntity.ok(response);
    }

    // Lấy tất cả
    @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'DEALER_STAFF')")
    @GetMapping
    public ResponseEntity<ApiResponse<List<TestDriveAppointmentSummaryDTO>>> getAll(
            @RequestParam(value = "1") Integer page,
            @RequestParam(value = "10") Integer size, @RequestParam(required = false) String status) {

        Pageable pageable = PageRequest.of(page - 1, size);

        List<TestDriveAppointmentSummaryDTO> testDriveAppointmentSummaryDTOs = service.getAllAppointments(pageable,
                status);

        ApiResponse<List<TestDriveAppointmentSummaryDTO>> response = new ApiResponse<List<TestDriveAppointmentSummaryDTO>>(
                true, null, testDriveAppointmentSummaryDTOs);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'DEALER_STAFF')")
    @Cacheable(value = "test-drive-appointment", key = "#testDriveAppointmentId")
    @GetMapping("/{testDriveAppointmentId}")
    public ResponseEntity<ApiResponse<TestDriveAppointmentResponseDTO>> getById(
            @PathVariable Integer testDriveAppointmentId) {

        TestDriveAppointmentResponseDTO dto = service.getAppointmentById(testDriveAppointmentId);

        ApiResponse<TestDriveAppointmentResponseDTO> response = new ApiResponse<TestDriveAppointmentResponseDTO>(true,
                null, dto);

        return ResponseEntity.ok(response);
    }

    // @GetMapping("/by-status")
    // public ResponseEntity<List<TestDriveAppointmentSummaryDTO>>
    // getByStatus(@RequestParam String status) {
    // List<TestDriveAppointmentSummaryDTO> list =
    // service.getAppointmentsByStatus(status);
    // return ResponseEntity.ok(list);
    // }
}