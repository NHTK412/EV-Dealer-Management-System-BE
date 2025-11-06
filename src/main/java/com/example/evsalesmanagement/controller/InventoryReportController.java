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
@RequestMapping("/reports")
public class InventoryReportController {

    @Autowired
    private InventoryReportService inventoryReportService;

    // GET - Lấy báo cáo tồn kho JSON
    @GetMapping("/inventory")
    public ResponseEntity<ApiResponse<List<InventoryReportResponseDTO>>> getInventoryReportDTO(
            @Valid @ModelAttribute InventoryReportRequestDTO request) {

        List<InventoryReportResponseDTO> report = inventoryReportService.getInventoryReport(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Get inventory report successfully", report));
    }


    // GET - Xuất báo cáo tồn kho Excel
    @GetMapping("/inventory/export")
    public ResponseEntity<byte[]> exportInventoryReport(
            @Valid @ModelAttribute InventoryReportRequestDTO request) {

        byte[] excelData = inventoryReportService.exportInventoryReportToExcel(request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment",
                "BaoCaoTonKho_" + System.currentTimeMillis() + ".xlsx");

        return ResponseEntity.ok().headers(headers).body(excelData);
    }


    // POST - Lấy báo cáo tồn kho JSON
    @PostMapping("/inventory")
    public ResponseEntity<ApiResponse<List<InventoryReportResponseDTO>>> getInventoryReportPost(
            @Valid @RequestBody InventoryReportRequestDTO request) {

        List<InventoryReportResponseDTO> report = inventoryReportService.getInventoryReport(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Get inventory report successfully", report));
    }

    
    // POST - Xuất báo cáo tồn kho Excel
    @PostMapping("/inventory/export")
    public ResponseEntity<byte[]> exportInventoryReportPost(
            @Valid @RequestBody InventoryReportRequestDTO request) {

        byte[] excelData = inventoryReportService.exportInventoryReportToExcel(request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment",
                "BaoCaoTonKho_" + System.currentTimeMillis() + ".xlsx");

        return ResponseEntity.ok().headers(headers).body(excelData);
    }
}
