package com.example.evsalesmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.importrequest.ImportRequestRequestDTO;
import com.example.evsalesmanagement.dto.importrequest.ImportRequestResponseDTO;
import com.example.evsalesmanagement.dto.importrequest.ImportRequestSummaryDTO;
import com.example.evsalesmanagement.security.CustomerUserDetails;
import com.example.evsalesmanagement.service.ImportRequestService;

import jakarta.validation.constraints.Positive;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    @PostMapping()
    public ResponseEntity<ImportRequestResponseDTO> createImportRequest(
            @RequestBody ImportRequestRequestDTO importRequestRequestDTO) {
        return ResponseEntity.ok(importRequestService.createImportRequest(importRequestRequestDTO));
    }

    @DeleteMapping("/{importRequestId}")
    public ResponseEntity<ImportRequestResponseDTO> deleteImportRequest(@PathVariable Integer importRequestId) {

        return ResponseEntity.ok(importRequestService.deleteImportRequest(importRequestId));
    }

    @PutMapping("/{importRequestId}")
    public ResponseEntity<ImportRequestResponseDTO> updateImportRequest(
            @PathVariable Integer importRequestId,
            @RequestBody ImportRequestRequestDTO importRequestRequestDTO) {
        return ResponseEntity.ok(importRequestService.updateImportRequest(importRequestId, importRequestRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<ImportRequestSummaryDTO>> getAllImportRequests(@RequestParam Integer page,
            @RequestParam @Positive Integer size,
            @RequestParam(required = false) Integer employeeId) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(importRequestService.getAllImportRequests(pageable, employeeId));
    }

    @GetMapping("/{importRequestId}")
    public ResponseEntity<ImportRequestResponseDTO> getImportRequestDetail(@PathVariable Integer importRequestId) {
        return ResponseEntity.ok(importRequestService.getImportRequestDetail(importRequestId));
    }

    // @GetMapping("/me")
    // public ResponseEntity<ImportRequestResponseDTO> getMyImportRequest(
    //         @AuthenticationPrincipal CustomerUserDetails customerUserDetails, @RequestParam Integer page,
    //         @RequestParam @Positive Integer size,
    //         @RequestParam(required = false) Integer employeeId) {
                
    //     Pageable pageable = PageRequest.of(page - 1, size);
        // Integer employeeId = customerUserDetails.get

        // return ResponseEntity.ok(importRequestService.getAllImportRequests(pageable, employeeId));
    // }

    // @GetMapping("/nhanVien/{maNhanVien}")
    // public ResponseEntity<List<>> layTatCaYeuCauNhapHangTheoNhanVien(
    // @PathVariable Integer maYeuCauNhapHang) {
    // return
    // ResponseEntity.ok(yeuCauNhapHangService.layChiTietKhuyenMai(maYeuCauNhapHang));
    // }

}
