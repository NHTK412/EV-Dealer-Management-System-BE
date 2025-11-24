package com.example.evsalesmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.revenuareport.RevenueReportRequestDTO;
import com.example.evsalesmanagement.dto.revenuareport.RevenueReportResponseDTO;
import com.example.evsalesmanagement.dto.revenuareport.RevenueReportSummaryDTO;
import com.example.evsalesmanagement.enums.OrderStatusEnum;
import com.example.evsalesmanagement.service.RevenueReportService;
import com.example.evsalesmanagement.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reports")
public class RevenueReportController {

    @Autowired
    private RevenueReportService revenueReportService;


    // Lấy báo cáo doanh thu JSON
    @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'ADMIN')")
    @PostMapping("/revenue")
    public ResponseEntity<ApiResponse<List<RevenueReportResponseDTO>>> getRevenueReport(
            @Valid @RequestBody RevenueReportRequestDTO request) {

        List<RevenueReportResponseDTO> report = revenueReportService.getRevenueReport(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Get revenue report successfully", report));
    }


    // Xuất báo cáo doanh thu Excel
    @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'ADMIN')")
    @PostMapping("/revenue/export")
    public ResponseEntity<byte[]> exportRevenueReport(@Valid @RequestBody RevenueReportRequestDTO request) {

        byte[] excelData = revenueReportService.exportRevenueReportToExcel(request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment",
                "BaoCaoDoanhThu_" + System.currentTimeMillis() + ".xlsx");

        return ResponseEntity.ok().headers(headers).body(excelData);
    }


    // Lấy tổng doanh thu toàn bộ
    @PostMapping("/revenue/summary/all")
    public ResponseEntity<ApiResponse<RevenueReportSummaryDTO>> getTotalRevenueAll(
            @Valid @RequestBody RevenueReportRequestDTO request) {

        RevenueReportSummaryDTO summary = revenueReportService.getTotalRevenueAll(request);
        return ResponseEntity.ok(new ApiResponse<>(true,
                "Get total revenue all successfully", summary));
    }


    // Lấy tổng doanh thu theo đại lý
    @GetMapping("/revenue/summary/agency/{agencyId}")
    public ResponseEntity<ApiResponse<RevenueReportSummaryDTO>> getTotalRevenueByAgency(
            @PathVariable Integer agencyId) {

        RevenueReportSummaryDTO summary = revenueReportService.getTotalRevenueByAgency(agencyId);
        return ResponseEntity.ok(new ApiResponse<>(true,
                "Get total revenue by agency successfully", summary));
    }


    // Lấy tổng doanh thu theo trạng thái đơn hàng
    @GetMapping("/revenue/summary/status/{status}")
    public ResponseEntity<ApiResponse<RevenueReportSummaryDTO>> getTotalRevenueByStatus(
            @PathVariable OrderStatusEnum status) {

        RevenueReportSummaryDTO summary = revenueReportService.getTotalRevenueByStatus(status);
        return ResponseEntity.ok(new ApiResponse<>(true,
                "Get total revenue by status successfully", summary));
    }

}
