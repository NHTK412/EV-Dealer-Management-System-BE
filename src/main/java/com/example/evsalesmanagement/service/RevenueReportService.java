package com.example.evsalesmanagement.service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.revenuareport.RevenueReportRequestDTO;
import com.example.evsalesmanagement.dto.revenuareport.RevenueReportResponseDTO;
import com.example.evsalesmanagement.dto.revenuareport.RevenueReportSummaryDTO;
import com.example.evsalesmanagement.enums.OrderStatusEnum;
import com.example.evsalesmanagement.exception.InternalServerException;
import com.example.evsalesmanagement.model.Order;
import com.example.evsalesmanagement.model.OrderDetail;
import com.example.evsalesmanagement.repository.RevenueReportRepository;

@Service
public class RevenueReportService {

    @Autowired
    private RevenueReportRepository revenueReportRepository;

    // Lọc đơn hàng theo request
    private List<Order> getFilteredOrders(RevenueReportRequestDTO request) {
        Integer agencyId = request.getAgencyId();
        OrderStatusEnum status = request.getStatus();
        var fromDate = request.getFromDate();
        var toDate = request.getToDate();

        if (agencyId != null && status != null && fromDate != null && toDate != null) {
            return revenueReportRepository.findByAgency_AgencyIdAndStatusAndCreateAtBetween(
                    agencyId, status, fromDate, toDate);
        } else if (agencyId != null && status != null) {
            return revenueReportRepository.findByAgency_AgencyIdAndStatus(agencyId, status);
        } else if (agencyId != null && fromDate != null && toDate != null) {
            return revenueReportRepository.findByAgency_AgencyIdAndCreateAtBetween(agencyId, fromDate, toDate);
        } else if (status != null && fromDate != null && toDate != null) {
            return revenueReportRepository.findByStatusAndCreateAtBetween(status, fromDate, toDate);
        } else if (agencyId != null) {
            return revenueReportRepository.findByAgency_AgencyId(agencyId);
        } else if (status != null) {
            return revenueReportRepository.findByStatus(status);
        } else if (fromDate != null && toDate != null) {
            return revenueReportRepository.findByCreateAtBetween(fromDate, toDate);
        } else {
            return revenueReportRepository.findAll();
        }
    }

    // Lấy báo cáo doanh thu chi tiết
    public List<RevenueReportResponseDTO> getRevenueReport(RevenueReportRequestDTO request) {
        List<Order> orders = getFilteredOrders(request);
        Map<String, RevenueData> map = new HashMap<>();

        for (Order order : orders) {
            if (order.getOrderDetails() == null) continue;

            for (OrderDetail detail : order.getOrderDetails()) {
                if (request.getVehicleTypeId() != null &&
                        !request.getVehicleTypeId().equals(Optional.ofNullable(detail.getVehicleTypeDetail())
                                .map(v -> v.getVehicleType().getVehicleTypeId())
                                .orElse(null))) continue;

                String key = createGroupKey(detail, order);
                RevenueData data = map.computeIfAbsent(key, k -> new RevenueData(detail, order));

                data.orderIds.add(order.getOrderId());
                data.totalQuantity += Optional.ofNullable(detail.getQuantity()).orElse(0);
                data.totalRevenue = data.totalRevenue.add(Optional.ofNullable(detail.getTotalAmount()).orElse(BigDecimal.ZERO));
                data.totalDiscount = data.totalDiscount.add(Optional.ofNullable(detail.getDiscount()).orElse(BigDecimal.ZERO));
            }
        }

        return map.values().stream()
                .map(RevenueData::toResponseDTO)
                .sorted(Comparator.comparing(RevenueReportResponseDTO::getVehicleTypeName, Comparator.nullsLast(String::compareToIgnoreCase)))
                .collect(Collectors.toList());
    }

    // Tính tổng doanh thu
    public RevenueReportSummaryDTO getTotalRevenueAll(RevenueReportRequestDTO request) {
        List<Order> orders = getFilteredOrders(request);

        BigDecimal totalRevenue = BigDecimal.ZERO;
        BigDecimal totalDiscount = BigDecimal.ZERO;
        int totalQuantity = 0;
        Set<Integer> uniqueOrderIds = new HashSet<>();

        for (Order order : orders) {
            uniqueOrderIds.add(order.getOrderId());

            if (order.getOrderDetails() != null) {
                for (OrderDetail d : order.getOrderDetails()) {
                    if (request.getVehicleTypeId() != null &&
                            !request.getVehicleTypeId().equals(Optional.ofNullable(d.getVehicleTypeDetail())
                                    .map(v -> v.getVehicleType().getVehicleTypeId())
                                    .orElse(null))) continue;

                    totalQuantity += Optional.ofNullable(d.getQuantity()).orElse(0);
                    totalRevenue = totalRevenue.add(Optional.ofNullable(d.getTotalAmount()).orElse(BigDecimal.ZERO));
                    totalDiscount = totalDiscount.add(Optional.ofNullable(d.getDiscount()).orElse(BigDecimal.ZERO));
                }
            }
        }

        return new RevenueReportSummaryDTO(totalRevenue, totalDiscount, uniqueOrderIds.size(), totalQuantity);
    }

    public RevenueReportSummaryDTO getTotalRevenueByAgency(Integer agencyId) {
        RevenueReportRequestDTO request = new RevenueReportRequestDTO();
        request.setAgencyId(agencyId);
        return getTotalRevenueAll(request);
    }

    public RevenueReportSummaryDTO getTotalRevenueByStatus(OrderStatusEnum status) {
        RevenueReportRequestDTO request = new RevenueReportRequestDTO();
        request.setStatus(status);
        return getTotalRevenueAll(request);
    }

    // Xuất Excel
    public byte[] exportRevenueReportToExcel(RevenueReportRequestDTO request) {
        List<RevenueReportResponseDTO> report = getRevenueReport(request);
        RevenueReportSummaryDTO summary = getTotalRevenueAll(request);

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Báo cáo doanh thu");
            int rowNum = 0;

            // Tiêu đề
            Row titleRow = sheet.createRow(rowNum++);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("BÁO CÁO DOANH THU");
            CellStyle titleStyle = workbook.createCellStyle();
            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 16);
            titleStyle.setFont(titleFont);
            titleCell.setCellStyle(titleStyle);

            // Bộ lọc
            if (request.getFromDate() != null && request.getToDate() != null) {
                Row dateRow = sheet.createRow(rowNum++);
                dateRow.createCell(0).setCellValue("Từ ngày: " +
                        request.getFromDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) +
                        " - Đến ngày: " +
                        request.getToDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            }
            if (request.getAgencyId() != null) {
                Row agencyRow = sheet.createRow(rowNum++);
                agencyRow.createCell(0).setCellValue("Đại lý: " + request.getAgencyId());
            }
            if (request.getStatus() != null) {
                Row statusRow = sheet.createRow(rowNum++);
                statusRow.createCell(0).setCellValue("Trạng thái: " + request.getStatus().getDisplayName());
            }
            rowNum++;

            // Header
            Row headerRow = sheet.createRow(rowNum++);
            String[] headers = {"Loại xe", "Đại lý", "Số đơn hàng", "Số lượng xe", "Tổng doanh thu", "Tổng giảm giá", "Doanh thu ròng"};
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Dữ liệu
            for (RevenueReportResponseDTO dto : report) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(dto.getVehicleTypeName() != null ? dto.getVehicleTypeName() : "");
                row.createCell(1).setCellValue(dto.getAgencyName() != null ? dto.getAgencyName() : "");
                row.createCell(2).setCellValue(dto.getTotalOrders());
                row.createCell(3).setCellValue(dto.getTotalQuantity());
                row.createCell(4).setCellValue(dto.getTotalRevenue() != null ? dto.getTotalRevenue().doubleValue() : 0);
                row.createCell(5).setCellValue(dto.getTotalDiscount() != null ? dto.getTotalDiscount().doubleValue() : 0);
                row.createCell(6).setCellValue(dto.getNetRevenue() != null ? dto.getNetRevenue().doubleValue() : 0);
            }

            // Tổng cộng
            rowNum++;
            Row summaryRow = sheet.createRow(rowNum);
            Cell summaryLabelCell = summaryRow.createCell(0);
            summaryLabelCell.setCellValue("TỔNG CỘNG");
            summaryLabelCell.setCellStyle(headerStyle);

            summaryRow.createCell(2).setCellValue(summary.getTotalOrders());
            summaryRow.createCell(3).setCellValue(summary.getTotalQuantity());
            summaryRow.createCell(4).setCellValue(summary.getTotalRevenue().doubleValue());
            summaryRow.createCell(5).setCellValue(summary.getTotalDiscount().doubleValue());
            summaryRow.createCell(6).setCellValue(summary.getNetRevenue().doubleValue());

            // Auto-size
            for (int i = 0; i < headers.length; i++) sheet.autoSizeColumn(i);

            workbook.write(out);
            return out.toByteArray();
        } catch (Exception e) {
            throw new InternalServerException("Failed to export Excel: " + e.getMessage());
        }
    }

    // Tạo key nhóm dữ liệu
    private String createGroupKey(OrderDetail detail, Order order) {
        String vehicleType = Optional.ofNullable(detail.getVehicleTypeDetail())
                .map(v -> v.getVehicleType().getVehicleTypeName())
                .orElse("Unknown");
        String agency = Optional.ofNullable(order.getAgency())
                .map(a -> a.getAgencyName())
                .orElse("Unknown");
        return vehicleType + "_" + agency;
    }

    // Class lưu dữ liệu trung gian
    private static class RevenueData {
        String vehicleTypeName;
        String agencyName;
        Set<Integer> orderIds = new HashSet<>();
        int totalQuantity = 0;
        BigDecimal totalRevenue = BigDecimal.ZERO;
        BigDecimal totalDiscount = BigDecimal.ZERO;

        RevenueData(OrderDetail detail, Order order) {
            this.vehicleTypeName = Optional.ofNullable(detail.getVehicleTypeDetail())
                    .map(v -> v.getVehicleType().getVehicleTypeName())
                    .orElse("Unknown");
            this.agencyName = Optional.ofNullable(order.getAgency())
                    .map(a -> a.getAgencyName())
                    .orElse("Unknown");
        }

        RevenueReportResponseDTO toResponseDTO() {
            RevenueReportResponseDTO dto = new RevenueReportResponseDTO();
            dto.setVehicleTypeName(vehicleTypeName);
            dto.setAgencyName(agencyName);
            dto.setTotalOrders(orderIds.size());
            dto.setTotalQuantity(totalQuantity);

            BigDecimal safeRevenue = totalRevenue != null ? totalRevenue : BigDecimal.ZERO;
            BigDecimal safeDiscount = totalDiscount != null ? totalDiscount : BigDecimal.ZERO;
            dto.setTotalRevenue(safeRevenue);
            dto.setTotalDiscount(safeDiscount);
            dto.setNetRevenue(safeRevenue.subtract(safeDiscount));

            return dto;
        }
    }
}
