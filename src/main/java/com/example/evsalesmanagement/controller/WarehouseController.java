package com.example.evsalesmanagement.controller;

import com.example.evsalesmanagement.dto.warehouseimportreceipt.*;
import com.example.evsalesmanagement.dto.warehousereleasenote.WarehouseReleaseNoteRequestDTO;
import com.example.evsalesmanagement.dto.warehousereleasenote.WarehouseReleaseNoteResponseDTO;
import com.example.evsalesmanagement.dto.warehousereleasenote.WarehouseReleaseNoteStatusUpdateDTO;
import com.example.evsalesmanagement.dto.warehousereleasenote.WarehouseReleaseNoteSummaryDTO;
import com.example.evsalesmanagement.service.WarehouseReceiptService;
import com.example.evsalesmanagement.service.WarehouseReleaseNoteService;
import com.example.evsalesmanagement.utils.ApiResponse;

import jakarta.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseReceiptService warehouseReceiptService;
    @Autowired
    private WarehouseReleaseNoteService warehouseExportService;

    // --- Phiếu nhập kho ---
    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
    @GetMapping("/import")
    public ResponseEntity<ApiResponse<Page<WarehouseReceiptSummaryDTO>>> getAllImport(
            @RequestParam int page,
            @RequestParam @Positive int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<WarehouseReceiptSummaryDTO> data = warehouseReceiptService.getAllWarehouseReceipts(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, null, data));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
    @GetMapping("/import/{warehouseReceiptId}")
    public ResponseEntity<ApiResponse<WarehouseReceiptResponseDTO>> getImportById(
            @PathVariable Integer warehouseReceiptId) {
        WarehouseReceiptResponseDTO dto = warehouseReceiptService.getWarehouseReceiptById(warehouseReceiptId);
        return ResponseEntity.ok(new ApiResponse<>(true, null, dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
    @PostMapping("/import")
    public ResponseEntity<ApiResponse<WarehouseReceiptResponseDTO>> createImport(
            @RequestBody WarehouseReceiptRequestDTO request) {
        WarehouseReceiptResponseDTO dto = warehouseReceiptService.importReceipt(request).getData();
        return ResponseEntity.ok(new ApiResponse<>(true, null, dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
    @PatchMapping("/import/{warehouseReceiptId}")
    public ResponseEntity<ApiResponse<WarehouseReceiptResponseDTO>> updateWarehouseReceiptStatus(
        @PathVariable Integer warehouseReceiptId,
        @RequestBody WarehouseReceiptStatusUpdateDTO request) {
        WarehouseReceiptResponseDTO dto = warehouseReceiptService
                .updateWarehouseReceiptStatus(warehouseReceiptId, request).getData();
        return ResponseEntity.ok(new ApiResponse<>(true, null, dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
    @DeleteMapping("/import/{warehouseReceiptId}")
    public ResponseEntity<ApiResponse<WarehouseReceiptResponseDTO>> deleteImport(
            @PathVariable Integer warehouseReceiptId) {
        WarehouseReceiptResponseDTO dto = warehouseReceiptService.getWarehouseReceiptById(warehouseReceiptId);
        warehouseReceiptService.deleteWarehouseReceipt(warehouseReceiptId);
        return ResponseEntity.ok(new ApiResponse<>(true, "xoá thành công", dto));
    }

    // --- Phiếu xuất kho ---
    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
    @GetMapping("/export")
    public ResponseEntity<ApiResponse<Page<WarehouseReleaseNoteSummaryDTO>>> getAllExport(
            @RequestParam int page,
            @RequestParam @Positive int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<WarehouseReleaseNoteSummaryDTO> data = warehouseExportService.getAllWarehouseExports(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, null, data));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
    @GetMapping("/export/{warehouseReleaseNoteId}")
    public ResponseEntity<ApiResponse<WarehouseReleaseNoteResponseDTO>> getExportById(
            @PathVariable Integer warehouseReleaseNoteId) {
        WarehouseReleaseNoteResponseDTO dto = warehouseExportService.getByIdWarehouseExport(warehouseReleaseNoteId);
        return ResponseEntity.ok(new ApiResponse<>(true, null, dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
    @PostMapping("/export")
    public ResponseEntity<ApiResponse<WarehouseReleaseNoteResponseDTO>> createExport(
            @RequestBody WarehouseReleaseNoteRequestDTO request) {
        WarehouseReleaseNoteResponseDTO dto = warehouseExportService.exportReceipt(request).getData();
        return ResponseEntity.ok(new ApiResponse<>(true, null, dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
    @PatchMapping("/export/{warehouseReleaseNoteId}")
    public ResponseEntity<ApiResponse<WarehouseReleaseNoteResponseDTO>> updateWarehouseExportStatus(
        @PathVariable Integer warehouseReleaseNoteId,
        @RequestBody WarehouseReleaseNoteStatusUpdateDTO request) {
        WarehouseReleaseNoteResponseDTO dto = warehouseExportService
                .updateWarehouseExport(warehouseReleaseNoteId, request).getData();
        return ResponseEntity.ok(new ApiResponse<>(true, null, dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EVM_STAFF','DEADLER_STAFF','DEADLER_MANAGER')")
    @DeleteMapping("/export/{warehouseReleaseNoteId}")
    public ResponseEntity<ApiResponse<WarehouseReleaseNoteResponseDTO>> deleteExport(
            @PathVariable Integer warehouseReleaseNoteId) {
        WarehouseReleaseNoteResponseDTO dto = warehouseExportService.getByIdWarehouseExport(warehouseReleaseNoteId);
        warehouseExportService.deleteWarehouseExport(warehouseReleaseNoteId);
        return ResponseEntity.ok(new ApiResponse<>(true, "xoá thành công ", dto));
    }
}
