package com.example.evsalesmanagement.service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    private List<Order> getFilteredOrders(RevenueReportRequestDTO request) {
        Integer agencyId = request.getAgencyId();
        OrderStatusEnum status = request.getStatus();
        LocalDateTime fromDate = request.getFromDate();
        LocalDateTime toDate = request.getToDate();

        if (agencyId != null && status != null && fromDate != null && toDate != null) {
            return revenueReportRepository.findByAgency_AgencyIdAndStatusAndCreateAtBetween(
                    agencyId, status, fromDate, toDate);
        } else if (agencyId != null && status != null) {
            return revenueReportRepository.findByAgency_AgencyIdAndStatus(agencyId, status);
        } else if (agencyId != null && fromDate != null && toDate != null) {
            return revenueReportRepository.findByAgency_AgencyIdAndCreateAtBetween(
                    agencyId, fromDate, toDate);
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

    @Cacheable(value = "revenueReport", key = "#request.hashCode()")
    public List<RevenueReportResponseDTO> getRevenueReport(RevenueReportRequestDTO request) {

        List<Order> orders = getFilteredOrders(request);
        Map<String, RevenueData> map = new HashMap<>();

        for (Order order : orders) {
            if (order.getOrderDetails() == null) continue;

            for (OrderDetail detail : order.getOrderDetails()) {

                if (request.getVehicleTypeId() != null &&
                    !request.getVehicleTypeId().equals(
                            Optional.ofNullable(detail.getVehicleTypeDetail())
                                    .map(v -> v.getVehicleType().getVehicleTypeId())
                                    .orElse(null)
                    )) continue;

                String key = createGroupKey(detail, order);
                RevenueData data = map.computeIfAbsent(key, k -> new RevenueData(detail, order));

                data.totalOrders++;
                data.totalQuantity += Optional.ofNullable(detail.getQuantity()).orElse(0);
                data.totalRevenue = data.totalRevenue.add(
                        Optional.ofNullable(detail.getTotalAmount()).orElse(BigDecimal.ZERO));
                data.totalDiscount = data.totalDiscount.add(
                        Optional.ofNullable(detail.getDiscount()).orElse(BigDecimal.ZERO));
            }
        }

        return map.values().stream()
                .map(RevenueData::toResponseDTO)
                .sorted(Comparator.comparing(RevenueReportResponseDTO::getVehicleTypeName,
                        Comparator.nullsLast(String::compareToIgnoreCase)))
                .collect(Collectors.toList());
    }

    public RevenueReportSummaryDTO getTotalRevenueAll(RevenueReportRequestDTO request) {
        List<Order> orders = getFilteredOrders(request);

        BigDecimal totalRevenue = BigDecimal.ZERO;
        BigDecimal totalDiscount = BigDecimal.ZERO;
        int totalOrders = 0;
        int totalQuantity = 0;

        for (Order order : orders) {
            totalOrders++;

            if (order.getOrderDetails() != null) {
                for (OrderDetail d : order.getOrderDetails()) {
                    if (request.getVehicleTypeId() != null &&
                        !request.getVehicleTypeId().equals(
                                Optional.ofNullable(d.getVehicleTypeDetail())
                                        .map(v -> v.getVehicleType().getVehicleTypeId())
                                        .orElse(null)
                        )) continue;

                    totalQuantity += Optional.ofNullable(d.getQuantity()).orElse(0);
                    totalRevenue = totalRevenue.add(Optional.ofNullable(d.getTotalAmount()).orElse(BigDecimal.ZERO));
                    totalDiscount = totalDiscount.add(Optional.ofNullable(d.getDiscount()).orElse(BigDecimal.ZERO));
                }
            }
        }

        return new RevenueReportSummaryDTO(totalRevenue, totalDiscount, totalOrders, totalQuantity);
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

    public byte[] exportRevenueReportToExcel(RevenueReportRequestDTO request) {
        List<RevenueReportResponseDTO> report = getRevenueReport(request);

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Revenue Report");

            Row headerRow = sheet.createRow(0);
            String[] headers = {"Vehicle Type", "Agency", "Total Orders", "Total Quantity", "Total Revenue", "Total Discount"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            int rowNum = 1;
            for (RevenueReportResponseDTO dto : report) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(dto.getVehicleTypeName() != null ? dto.getVehicleTypeName() : "");
                row.createCell(1).setCellValue(dto.getAgencyName() != null ? dto.getAgencyName() : "");
                row.createCell(2).setCellValue(dto.getTotalOrders());
                row.createCell(3).setCellValue(dto.getTotalQuantity());
                row.createCell(4).setCellValue(dto.getTotalRevenue() != null ? dto.getTotalRevenue().doubleValue() : 0);
                row.createCell(5).setCellValue(dto.getTotalDiscount() != null ? dto.getTotalDiscount().doubleValue() : 0);
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return out.toByteArray();
        } catch (Exception e) {
            throw new InternalServerException("Failed to export Excel: " + e.getMessage());
        }
    }

    private String createGroupKey(OrderDetail detail, Order order) {
        String vehicleType = Optional.ofNullable(detail.getVehicleTypeDetail())
                .map(v -> v.getVehicleType().getVehicleTypeName())
                .orElse("Unknown");
        String agency = Optional.ofNullable(order.getAgency())
                .map(a -> a.getAgencyName())
                .orElse("Unknown");
        return vehicleType + "_" + agency;
    }

    private static class RevenueData {
        String vehicleTypeName;
        String agencyName;
        int totalOrders = 0;
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
            dto.setTotalOrders(totalOrders);
            dto.setTotalQuantity(totalQuantity);
            dto.setTotalRevenue(totalRevenue);
            dto.setTotalDiscount(totalDiscount);
            return dto;
        }
    }
}