package com.example.evsalesmanagement.controller;

import com.example.evsalesmanagement.dto.testdriveappointment.TestDriveAppointmentRequestDTO;
import com.example.evsalesmanagement.dto.testdriveappointment.TestDriveAppointmentResponseDTO;
import com.example.evsalesmanagement.dto.testdriveappointment.TestDriveAppointmentSummaryDTO;
import com.example.evsalesmanagement.enums.TestDriveAppointmentStatusEnum;
import com.example.evsalesmanagement.service.TestDriveAppointmentService;
import com.example.evsalesmanagement.utils.ApiResponse;

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

        // CREATE - Tạo lịch hẹn lái thử mới
        @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'DEALER_STAFF')")
        @PostMapping
        public ResponseEntity<ApiResponse<TestDriveAppointmentSummaryDTO>> createAppointment(
                        @Valid @RequestBody TestDriveAppointmentRequestDTO request) {
                TestDriveAppointmentSummaryDTO createdAppointment = service.createAppointment(request);
                ApiResponse<TestDriveAppointmentSummaryDTO> response = new ApiResponse<>(true,
                                "Test-drive appointment created successfully", createdAppointment);
                return ResponseEntity.ok(response);
        }

        // UPDATE - Cập nhật thông tin lịch hẹn
        @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'DEALER_STAFF')")
        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<TestDriveAppointmentSummaryDTO>> updateAppointment(
                        @PathVariable("id") Integer id,
                        @Valid @RequestBody TestDriveAppointmentRequestDTO request) {
                TestDriveAppointmentSummaryDTO updatedAppointment = service.updateAppointment(id, request);
                ApiResponse<TestDriveAppointmentSummaryDTO> response = new ApiResponse<>(true,
                                "Test-drive appointment updated successfully", updatedAppointment);
                return ResponseEntity.ok(response);
        }

        // PATCH - Cập nhật trạng thái lịch hẹn
        @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'DEALER_STAFF')")
        @PatchMapping("/{id}")
        public ResponseEntity<ApiResponse<TestDriveAppointmentSummaryDTO>> updateAppointmentStatus(
                        @PathVariable("id") Integer id,
                        @RequestParam TestDriveAppointmentStatusEnum status) {
                TestDriveAppointmentSummaryDTO updatedAppointment = service.updateAppointmentStatus(id, status);

                ApiResponse<TestDriveAppointmentSummaryDTO> response = new ApiResponse<>(true, null,
                                updatedAppointment);

                return ResponseEntity.ok(response);
        }

        // DELETE - Xóa lịch hẹn
        @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'DEALER_STAFF')")
        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Void>> deleteAppointment(@PathVariable("id") Integer id) {
                service.deleteAppointment(id);
                ApiResponse<Void> response = new ApiResponse<>(true, "Test-drive appointment deleted successfully",
                                null);
                return ResponseEntity.ok(response);
        }

        // READ - Lấy tất cả lịch hẹn
        @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'DEALER_STAFF')")
        @GetMapping
        public ResponseEntity<ApiResponse<List<TestDriveAppointmentSummaryDTO>>> getAll(
                        @RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") Integer size,
                        @RequestParam(required = false) String status) {

                Pageable pageable = PageRequest.of(page - 1, size);

                List<TestDriveAppointmentSummaryDTO> testDriveAppointmentSummaryDTOs = service.getAllAppointments(
                                pageable,
                                status);

                ApiResponse<List<TestDriveAppointmentSummaryDTO>> response = new ApiResponse<>(
                                true, "Test-drive appointment list retrieved successfully",
                                testDriveAppointmentSummaryDTOs);

                return ResponseEntity.ok(response);
        }

        @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'DEALER_STAFF')")
        @GetMapping("/{testDriveAppointmentId}")
        public ResponseEntity<ApiResponse<TestDriveAppointmentResponseDTO>> getById(
                        @PathVariable Integer testDriveAppointmentId) {

                TestDriveAppointmentResponseDTO dto = service.getAppointmentById(testDriveAppointmentId);

                ApiResponse<TestDriveAppointmentResponseDTO> response = new ApiResponse<>(
                                true,
                                "Test-drive appointment details retrieved successfully", dto);

                return ResponseEntity.ok(response);
        }
}