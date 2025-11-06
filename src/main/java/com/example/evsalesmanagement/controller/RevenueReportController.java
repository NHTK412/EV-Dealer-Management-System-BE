package com.example.evsalesmanagement.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.RevenueReportRequestDTO;
import com.example.evsalesmanagement.dto.RevenueReportResponseDTO;
import com.example.evsalesmanagement.service.RevenueReportService;
import com.example.evsalesmanagement.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reports")
public class RevenueReportController {

    @Autowired
    private RevenueReportService revenueReportService;

    // Lấy báo cáo doanh thu JSON
    @PostMapping("/revenue")
    public ResponseEntity<ApiResponse<List<RevenueReportResponseDTO>>> getRevenueReport(
            @Valid @RequestBody RevenueReportRequestDTO request) {

        List<RevenueReportResponseDTO> report = revenueReportService.getRevenueReport(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Get revenue report successfully", report));
    }

    // Xuất báo cáo doanh thu Excel
    @PostMapping("/revenue/export")
    public ResponseEntity<byte[]> exportRevenueReport(@Valid @RequestBody RevenueReportRequestDTO request) {

        byte[] excelData = revenueReportService.exportRevenueReportToExcel(request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment",
                "BaoCaoDoanhThu_" + System.currentTimeMillis() + ".xlsx");

        return ResponseEntity.ok().headers(headers).body(excelData);
    }
}
