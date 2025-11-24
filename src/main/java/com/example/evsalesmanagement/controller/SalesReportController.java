package com.example.evsalesmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.salesreport.AgencySalesDTO;
import com.example.evsalesmanagement.dto.salesreport.EmployeeSalesDTO;
import com.example.evsalesmanagement.service.SalesReportService;
import com.example.evsalesmanagement.utils.ApiResponse;

@RestController
@RequestMapping("/sales-report")
public class SalesReportController {

    @Autowired
    private SalesReportService salesReportService;

    // ============ DOANH SỐ THEO NHÂN VIÊN BÁN HÀNG ============

    /**
     * Lấy doanh số tất cả nhân viên bán hàng theo tháng
     * GET /sales-report/employee/month?year=2024&month=11
     */
    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @GetMapping("/employee/month")
    public ResponseEntity<ApiResponse<List<EmployeeSalesDTO>>> getEmployeeSalesByMonth(
            @RequestParam Integer year,
            @RequestParam Integer month) {
        List<EmployeeSalesDTO> response = salesReportService.getEmployeeSalesByMonth(year, month);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy doanh số nhân viên theo tháng thành công", response));
    }

    /**
     * Lấy doanh số tất cả nhân viên bán hàng theo năm
     * GET /sales-report/employee/year?year=2024
     */
    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @GetMapping("/employee/year")
    public ResponseEntity<ApiResponse<List<EmployeeSalesDTO>>> getEmployeeSalesByYear(
            @RequestParam Integer year) {
        List<EmployeeSalesDTO> response = salesReportService.getEmployeeSalesByYear(year);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy doanh số nhân viên theo năm thành công", response));
    }

    /**
     * Lấy doanh số của một nhân viên cụ thể theo tháng
     * GET /sales-report/employee/{employeeId}/month?year=2024&month=11
     */
    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @GetMapping("/employee/{employeeId}/month")
    public ResponseEntity<ApiResponse<EmployeeSalesDTO>> getEmployeeSalesByEmployeeAndMonth(
            @PathVariable Integer employeeId,
            @RequestParam Integer year,
            @RequestParam Integer month) {
        EmployeeSalesDTO response = salesReportService.getEmployeeSalesByEmployeeAndMonth(employeeId, year, month);
        return ResponseEntity
                .ok(new ApiResponse<>(true, "Lấy doanh số nhân viên theo tháng thành công", response));
    }

    /**
     * Lấy doanh số của một nhân viên cụ thể theo năm
     * GET /sales-report/employee/{employeeId}/year?year=2024
     */
    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @GetMapping("/employee/{employeeId}/year")
    public ResponseEntity<ApiResponse<EmployeeSalesDTO>> getEmployeeSalesByEmployeeAndYear(
            @PathVariable Integer employeeId,
            @RequestParam Integer year) {
        EmployeeSalesDTO response = salesReportService.getEmployeeSalesByEmployeeAndYear(employeeId, year);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy doanh số nhân viên theo năm thành công", response));
    }

    // ============ DOANH SỐ THEO ĐẠI LÝ ============

    /**
     * Lấy doanh số tất cả đại lý theo tháng
     * GET /sales-report/agency/month?year=2024&month=11
     */
    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @GetMapping("/agency/month")
    public ResponseEntity<ApiResponse<List<AgencySalesDTO>>> getAgencySalesByMonth(
            @RequestParam Integer year,
            @RequestParam Integer month) {
        List<AgencySalesDTO> response = salesReportService.getAgencySalesByMonth(year, month);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy doanh số đại lý theo tháng thành công", response));
    }

    /**
     * Lấy doanh số tất cả đại lý theo năm
     * GET /sales-report/agency/year?year=2024
     */
    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @GetMapping("/agency/year")
    public ResponseEntity<ApiResponse<List<AgencySalesDTO>>> getAgencySalesByYear(
            @RequestParam Integer year) {
        List<AgencySalesDTO> response = salesReportService.getAgencySalesByYear(year);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy doanh số đại lý theo năm thành công", response));
    }

    /**
     * Lấy doanh số của một đại lý cụ thể theo tháng
     * GET /sales-report/agency/{agencyId}/month?year=2024&month=11
     */
    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN', 'AGENCY_STAFF')")
    @GetMapping("/agency/{agencyId}/month")
    public ResponseEntity<ApiResponse<AgencySalesDTO>> getAgencySalesByAgencyAndMonth(
            @PathVariable Integer agencyId,
            @RequestParam Integer year,
            @RequestParam Integer month) {
        AgencySalesDTO response = salesReportService.getAgencySalesByAgencyAndMonth(agencyId, year, month);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy doanh số đại lý theo tháng thành công", response));
    }

    /**
     * Lấy doanh số của một đại lý cụ thể theo năm (tất cả các tháng trong năm)
     * GET /sales-report/agency/{agencyId}/year?year=2024
     */
    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN', 'AGENCY_STAFF')")
    @GetMapping("/agency/{agencyId}/year")
    public ResponseEntity<ApiResponse<List<AgencySalesDTO>>> getAgencySalesByAgencyAndYear(
            @PathVariable Integer agencyId,
            @RequestParam Integer year) {
        List<AgencySalesDTO> response = salesReportService.getAgencySalesByAgencyAndYear(agencyId, year);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy doanh số đại lý theo năm thành công", response));
    }

}
