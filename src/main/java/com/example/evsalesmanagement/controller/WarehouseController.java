package com.example.evsalesmanagement.controller;

import com.example.evsalesmanagement.dto.Warehouse.*;
import com.example.evsalesmanagement.service.WarehouseReceiptService;
import com.example.evsalesmanagement.service.WarehouseExportService;
import com.example.evsalesmanagement.utils.ApiResponse;

import jakarta.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseReceiptService warehouseReceiptService;
    @Autowired
    private WarehouseExportService warehouseExportService;

    // --- Phiếu nhập kho ---
    @GetMapping("/import")
    public ResponseEntity<ApiResponse<List<WarehouseImportReceiptSummaryDTO>>> getAllImport(
        @RequestParam int page,
        @RequestParam @Positive int size) {
        Pageable pageable = PageRequest.of(page -1 , size);
        List<WarehouseImportReceiptSummaryDTO> data = warehouseReceiptService.getAllWarehouseReceipts(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, null, data));
    }

    @GetMapping("/import/{warehouseReceiptId}")
    public ResponseEntity<ApiResponse<WarehouseImportReceiptResponseDTO>> getImportById(@PathVariable Integer warehouseReceiptId) {
        WarehouseImportReceiptResponseDTO dto = warehouseReceiptService.getWarehouseReceiptById(warehouseReceiptId);
        return ResponseEntity.ok(new ApiResponse<>(true, null, dto));
    }

    @PostMapping("/import")
    public ResponseEntity<ApiResponse<WarehouseImportReceiptResponseDTO>> createImport(@RequestBody WarehouseImportReceiptRequestDTO request) {
        WarehouseImportReceiptResponseDTO dto = warehouseReceiptService.importReceipt(request).getData();
        return ResponseEntity.ok(new ApiResponse<>(true, null, dto));
    }

    @PutMapping("/import/{warehouseReceiptId}")
    public ResponseEntity<ApiResponse<WarehouseImportReceiptResponseDTO>> updateImport(@PathVariable Integer warehouseReceiptId,
                                                                                      @RequestBody WarehouseImportReceiptRequestDTO request) {
        WarehouseImportReceiptResponseDTO dto = warehouseReceiptService.updateWarehouseReceipt(warehouseReceiptId, request).getData();
        return ResponseEntity.ok(new ApiResponse<>(true, null, dto));
    }

    @DeleteMapping("/import/{warehouseReceiptId}")
    public ResponseEntity<ApiResponse<WarehouseImportReceiptResponseDTO>> deleteImport(@PathVariable Integer warehouseReceiptId) {
        WarehouseImportReceiptResponseDTO dto = warehouseReceiptService.getWarehouseReceiptById(warehouseReceiptId);
        warehouseReceiptService.deleteWarehouseReceipt(warehouseReceiptId);
        return ResponseEntity.ok(new ApiResponse<>(true, "xoá thành công", dto));
    }

    // --- Phiếu xuất kho ---
    @GetMapping("/export")
    public ResponseEntity<ApiResponse<List<WarehouseExportReceiptSummaryDTO>>> getAllExport(
        @RequestParam int page,
        @RequestParam @Positive int size) {
        Pageable pageable = PageRequest.of(page -1, size);
        List<WarehouseExportReceiptSummaryDTO> data = warehouseExportService.getAllWarehouseExports(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, null, data));
    }

    @GetMapping("/export/{warehouseReceiptId}")
    public ResponseEntity<ApiResponse<WarehouseExportReceiptResponseDTO>> getExportById(@PathVariable Integer warehouseReceiptId) {
        WarehouseExportReceiptResponseDTO dto = warehouseExportService.getByIdWarehouseExport(warehouseReceiptId);
        return ResponseEntity.ok(new ApiResponse<>(true, null, dto));
    }

    @PostMapping("/export")
    public ResponseEntity<ApiResponse<WarehouseExportReceiptResponseDTO>> createExport(@RequestBody WarehouseExportReceiptRequestDTO request) {
        WarehouseExportReceiptResponseDTO dto = warehouseExportService.exportReceipt(request).getData();
        return ResponseEntity.ok(new ApiResponse<>(true, null, dto));
    }

    @PutMapping("/export/{warehouseReceiptId}")
    public ResponseEntity<ApiResponse<WarehouseExportReceiptResponseDTO>> updateExport(@PathVariable Integer warehouseReceiptId,
                                                                                      @RequestBody WarehouseExportReceiptRequestDTO request) {
        WarehouseExportReceiptResponseDTO dto = warehouseExportService.updateWarehouseExport(warehouseReceiptId, request).getData();
        return ResponseEntity.ok(new ApiResponse<>(true, null, dto));
    }

    @DeleteMapping("/export/{warehouseReceiptId}")
    public ResponseEntity<ApiResponse<WarehouseExportReceiptResponseDTO>> deleteExport(@PathVariable Integer warehouseReceiptId) {
        WarehouseExportReceiptResponseDTO dto = warehouseExportService.getByIdWarehouseExport(warehouseReceiptId);
        warehouseExportService.deleteWarehouseExport(warehouseReceiptId);
        return ResponseEntity.ok(new ApiResponse<>(true, "xoá thành công ", dto));
    }
}
