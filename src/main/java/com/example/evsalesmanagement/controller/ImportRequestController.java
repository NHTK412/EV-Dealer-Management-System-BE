package com.example.evsalesmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.importrequest.ImportRequestRequestDTO;
import com.example.evsalesmanagement.dto.importrequest.ImportRequestResponseDTO;
import com.example.evsalesmanagement.dto.importrequest.ImportRequestSummaryDTO;
import com.example.evsalesmanagement.security.CustomerUserDetails;
import com.example.evsalesmanagement.service.ImportRequestService;
import com.example.evsalesmanagement.utils.ApiResponse;

import jakarta.validation.constraints.Positive;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/import-request")
public class ImportRequestController {

        @Autowired
        ImportRequestService importRequestService;

        // Chưa tạo logic gửi thông báo đến đại lý
        // @PreAuthorize("hasAnyRole('DEALER_MANAGER')")
        @PostMapping()
        public ResponseEntity<ApiResponse<ImportRequestResponseDTO>> createImportRequest(
                        @RequestBody ImportRequestRequestDTO importRequestRequestDTO) {

                ImportRequestResponseDTO importRequestResponseDTO = importRequestService
                                .createImportRequest(importRequestRequestDTO);

                return ResponseEntity
                                .ok(new ApiResponse<ImportRequestResponseDTO>(true, null, importRequestResponseDTO));
        }

        // @PreAuthorize("hasAnyRole('DEALER_MANAGER')")
        @DeleteMapping("/{importRequestId}")
        public ResponseEntity<ApiResponse<ImportRequestResponseDTO>> deleteImportRequest(
                        @PathVariable Integer importRequestId) {

                ImportRequestResponseDTO importRequestResponseDTO = importRequestService
                                .deleteImportRequest(importRequestId);

                return ResponseEntity
                                .ok(new ApiResponse<ImportRequestResponseDTO>(true, null, importRequestResponseDTO));
                // return ResponseEntity.ok());
        }

        // @PreAuthorize("hasAnyRole('DEALER_MANAGER')")
        @PutMapping("/{importRequestId}")
        public ResponseEntity<ApiResponse<ImportRequestResponseDTO>> updateImportRequest(
                        @PathVariable Integer importRequestId,
                        @RequestBody ImportRequestRequestDTO importRequestRequestDTO) {
                // return
                // ResponseEntity.ok(importRequestService.updateImportRequest(importRequestId,
                // importRequestRequestDTO));

                ImportRequestResponseDTO importRequestResponseDTO = importRequestService.updateImportRequest(
                                importRequestId,
                                importRequestRequestDTO);

                return ResponseEntity
                                .ok(new ApiResponse<ImportRequestResponseDTO>(true, null, importRequestResponseDTO));
        }

        // @PreAuthorize("hasAnyRole('DEALER_MANAGER')")
        @GetMapping
        public ResponseEntity<ApiResponse<List<ImportRequestSummaryDTO>>> getAllImportRequests(
                        @RequestParam Integer page,
                        @RequestParam @Positive Integer size,
                        @RequestParam(required = false) Integer employeeId) {
                Pageable pageable = PageRequest.of(page - 1, size);

                List<ImportRequestSummaryDTO> importRequestSummaryDTOs = importRequestService.getAllImportRequests(
                                pageable,
                                employeeId);

                return ResponseEntity.ok(
                                new ApiResponse<List<ImportRequestSummaryDTO>>(true, null, importRequestSummaryDTOs));

                // return ResponseEntity.ok(importRequestService.getAllImportRequests(pageable,
                // employeeId));
        }

        // @PreAuthorize("hasAnyRole('DEALER_MANAGER')")
        @GetMapping("/{importRequestId}")
        public ResponseEntity<ApiResponse<ImportRequestResponseDTO>> getImportRequestDetail(
                        @PathVariable Integer importRequestId) {

                ImportRequestResponseDTO importRequestResponseDTO = importRequestService
                                .getImportRequestDetail(importRequestId);

                return ResponseEntity
                                .ok(new ApiResponse<ImportRequestResponseDTO>(true, null, importRequestResponseDTO));

                // return
                // ResponseEntity.ok(importRequestService.getImportRequestDetail(importRequestId));
        }

        // @PreAuthorize("hasAnyRole('DEALER_MANAGER')")
        @GetMapping("/me")
        public ResponseEntity<ApiResponse<List<ImportRequestSummaryDTO>>> getMyImportRequest(
                        @AuthenticationPrincipal CustomerUserDetails customerUserDetails, @RequestParam Integer page,
                        @RequestParam @Positive Integer size) {

                Pageable pageable = PageRequest.of(page - 1, size);
                Integer employeeId = customerUserDetails.getEmployeeId();

                List<ImportRequestSummaryDTO> importRequestSummaryDTOs = importRequestService
                                .getAllImportRequests(pageable, employeeId);

                return ResponseEntity.ok(
                                new ApiResponse<List<ImportRequestSummaryDTO>>(true, null, importRequestSummaryDTOs));

                // return ResponseEntity.ok(importRequestService.getAllImportRequests(pageable,
                // employeeId));
        }

        // @GetMapping("/nhanVien/{maNhanVien}")
        // public ResponseEntity<List<>> layTatCaYeuCauNhapHangTheoNhanVien(
        // @PathVariable Integer maYeuCauNhapHang) {
        // return
        // ResponseEntity.ok(yeuCauNhapHangService.layChiTietKhuyenMai(maYeuCauNhapHang));
        // }

}
