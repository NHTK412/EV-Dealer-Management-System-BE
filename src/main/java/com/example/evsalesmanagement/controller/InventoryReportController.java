package com.example.evsalesmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.InventoryReportRequestDTO;
import com.example.evsalesmanagement.dto.InventoryReportResponseDTO;
import com.example.evsalesmanagement.service.InventoryReportService;
import com.example.evsalesmanagement.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
// @CrossOrigin("*")
@RequestMapping("/reports")
public class InventoryReportController {

    @Autowired
    private InventoryReportService inventoryReportService;


    // Lấy báo cáo tồn kho JSON
    @GetMapping("/inventory")
    public ResponseEntity<ApiResponse<List<InventoryReportResponseDTO>>> getInventoryReportDTO(
           @Valid @ModelAttribute InventoryReportRequestDTO request) {
        try {
            List<InventoryReportResponseDTO> report = inventoryReportService.getInventoryReport(request);
            return ResponseEntity.ok(new ApiResponse<>(
                    true,
                    "Get inventory successfully",
                    report
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(
                    false,
                    "Error when get inventory: " + e.getMessage(),
                    null
            ));
        }
    }


    // Xuất báo cáo tồn kho ra Excel
    @GetMapping("/inventory/export")
    public ResponseEntity<byte[]> exportInventoryReport(
            @Valid @ModelAttribute InventoryReportRequestDTO request) {
        try {
            byte[] excelData = inventoryReportService.exportInventoryReportToExcel(request);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", 
                "BaoCaoTonKho_" + System.currentTimeMillis() + ".xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(excelData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    // POST lấy báo cáo 
    @PostMapping("/inventory")
    public ResponseEntity<ApiResponse<List<InventoryReportResponseDTO>>> getInventoryReportPost(
            @Valid @RequestBody InventoryReportRequestDTO request) {
        try {
            List<InventoryReportResponseDTO> report = inventoryReportService.getInventoryReport(request);
            return ResponseEntity.ok(new ApiResponse<>(
                    true,
                    "Get inventory successfully",
                    report
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(
                    false,
                    "Error when get inventory: " + e.getMessage(),
                    null
            ));
        }
    }

    // POST xuất Excel 
    @PostMapping("/inventory/export")
    public ResponseEntity<byte[]> exportInventoryReportPost(
            @Valid @RequestBody InventoryReportRequestDTO request) {
        try {
            byte[] excelData = inventoryReportService.exportInventoryReportToExcel(request);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", 
                "BaoCaoTonKho_" + System.currentTimeMillis() + ".xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(excelData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}