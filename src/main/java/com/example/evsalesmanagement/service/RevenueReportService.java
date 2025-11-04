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

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.RevenueReportRequestDTO;
import com.example.evsalesmanagement.dto.RevenueReportResponseDTO;
import com.example.evsalesmanagement.enums.RevenueReportEnum;
import com.example.evsalesmanagement.model.Order;
import com.example.evsalesmanagement.model.OrderDetail;
import com.example.evsalesmanagement.repository.RevenueReportRepository;

@Service
public class RevenueReportService {

    @Autowired
    private RevenueReportRepository revenueReportRepository;


    @Cacheable(value = "revenueReport", key = "#request.hashCode()")
    public List<RevenueReportResponseDTO> getRevenueReport(RevenueReportRequestDTO request) {

        if (request.getStatus() != null && !request.getStatus().isBlank()) {
            try {
                RevenueReportEnum.fromString(request.getStatus());
            } catch (Exception e) {
                throw new RuntimeException("Status is invalid: " + request.getStatus(), e);
            }
        }


        List<Order> orders = getFilteredOrders(request);
        Map<String, RevenueData> map = new HashMap<>();

        for (Order order : orders) {
            if (order.getOrderDetails() == null) continue;

            for (OrderDetail detail : order.getOrderDetails()) {
                if (request.getVehicleTypeId() != null &&
                    (detail.getVehicleTypeDetail() == null ||
                     detail.getVehicleTypeDetail().getVehicleType() == null ||
                     !request.getVehicleTypeId().equals(detail.getVehicleTypeDetail().getVehicleType().getVehicleTypeId()))) {
                    continue;
                }

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
                .sorted(Comparator.comparing(
                        RevenueReportResponseDTO::getVehicleTypeName,
                        Comparator.nullsLast(String::compareToIgnoreCase)))
                .collect(Collectors.toList());
    }


    private List<Order> getFilteredOrders(RevenueReportRequestDTO request) {
        Integer agencyId = request.getAgencyId();
        String status = request.getStatus();
        LocalDateTime from = request.getFromDate();
        LocalDateTime to = request.getToDate();

        boolean hasAgency = agencyId != null;
        boolean hasStatus = status != null && !status.isBlank();
        boolean hasDate = from != null && to != null;

        if (hasAgency && hasStatus && hasDate)
            return revenueReportRepository.findByAgency_AgencyIdAndStatusAndCreateAtBetween(agencyId, status, from, to);
        if (hasAgency && hasStatus)
            return revenueReportRepository.findByAgency_AgencyIdAndStatus(agencyId, status);
        if (hasStatus && hasDate)
            return revenueReportRepository.findByStatusAndCreateAtBetween(status, from, to);
        if (hasAgency && hasDate)
            return revenueReportRepository.findByAgency_AgencyIdAndCreateAtBetween(agencyId, from, to);
        if (hasAgency)
            return revenueReportRepository.findByAgency_AgencyId(agencyId);
        if (hasStatus)
            return revenueReportRepository.findByStatus(status);
        if (hasDate)
            return revenueReportRepository.findByCreateAtBetween(from, to);

        return revenueReportRepository.findAll();
    }


    public byte[] exportRevenueReportToExcel(RevenueReportRequestDTO request) {
        try {
            List<RevenueReportResponseDTO> reportData = getRevenueReport(request);

            try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                Sheet sheet = workbook.createSheet("Revenue Report");

                CellStyle headerStyle = workbook.createCellStyle();
                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerStyle.setFont(headerFont);
                headerStyle.setAlignment(HorizontalAlignment.CENTER);
                headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerStyle.setBorderBottom(BorderStyle.THIN);
                headerStyle.setBorderTop(BorderStyle.THIN);
                headerStyle.setBorderLeft(BorderStyle.THIN);
                headerStyle.setBorderRight(BorderStyle.THIN);

                CellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setBorderBottom(BorderStyle.THIN);
                cellStyle.setBorderTop(BorderStyle.THIN);
                cellStyle.setBorderLeft(BorderStyle.THIN);
                cellStyle.setBorderRight(BorderStyle.THIN);

                String[] headers = {"STT", "Type", "Version", "Color", "Agency", "Order Count",
                        "Total Quantity", "Total Revenue", "Total Discount", "Net Revenue"};
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                    cell.setCellStyle(headerStyle);
                }

                int rowNum = 1;
                int index = 1;
                BigDecimal totalRevenue = BigDecimal.ZERO;
                BigDecimal totalDiscount = BigDecimal.ZERO;
                BigDecimal totalNet = BigDecimal.ZERO;
                int totalQuantity = 0;
                int totalOrders = 0;

                for (RevenueReportResponseDTO dto : reportData) {
                    Row row = sheet.createRow(rowNum++);
                    int col = 0;

                    row.createCell(col++).setCellValue(index++);
                    row.createCell(col++).setCellValue(dto.getVehicleTypeName());
                    row.createCell(col++).setCellValue(dto.getVersion());
                    row.createCell(col++).setCellValue(dto.getColor());
                    row.createCell(col++).setCellValue(dto.getAgencyName());
                    row.createCell(col++).setCellValue(dto.getTotalOrders());
                    row.createCell(col++).setCellValue(dto.getTotalQuantity());
                    row.createCell(col++).setCellValue(dto.getTotalRevenue().doubleValue());
                    row.createCell(col++).setCellValue(dto.getTotalDiscount().doubleValue());
                    row.createCell(col++).setCellValue(dto.getNetRevenue().doubleValue());

                    for (int i = 0; i < headers.length; i++) {
                        row.getCell(i).setCellStyle(cellStyle);
                    }

                    totalOrders += Optional.ofNullable(dto.getTotalOrders()).orElse(0);
                    totalQuantity += Optional.ofNullable(dto.getTotalQuantity()).orElse(0);
                    totalRevenue = totalRevenue.add(dto.getTotalRevenue());
                    totalDiscount = totalDiscount.add(dto.getTotalDiscount());
                    totalNet = totalNet.add(dto.getNetRevenue());
                }

                // Sum 
                Row totalRow = sheet.createRow(rowNum);
                Cell totalLabel = totalRow.createCell(0);
                totalLabel.setCellValue("SUMMARY");
                totalLabel.setCellStyle(headerStyle);

                for (int i = 1; i < headers.length; i++) {
                    Cell c = totalRow.createCell(i);
                    c.setCellStyle(headerStyle);
                }

                totalRow.getCell(5).setCellValue(totalOrders);
                totalRow.getCell(6).setCellValue(totalQuantity);
                totalRow.getCell(7).setCellValue(totalRevenue.doubleValue());
                totalRow.getCell(8).setCellValue(totalDiscount.doubleValue());
                totalRow.getCell(9).setCellValue(totalNet.doubleValue());

                for (int i = 0; i < headers.length; i++) sheet.autoSizeColumn(i);

                workbook.write(outputStream);
                return outputStream.toByteArray();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error exporting revenue report to Excel", e);
        }
    }


    private String createGroupKey(OrderDetail d, Order o) {
        Integer vt = (d.getVehicleTypeDetail() != null && d.getVehicleTypeDetail().getVehicleType() != null)
                ? d.getVehicleTypeDetail().getVehicleType().getVehicleTypeId() : 0;
        Integer vtd = d.getVehicleTypeDetail() != null ? d.getVehicleTypeDetail().getVehicleTypeDetailId() : 0;
        Integer agency = o.getAgency() != null ? o.getAgency().getAgencyId() : 0;
        return vt + "_" + vtd + "_" + agency;
    }

    private static class RevenueData {
        Integer vehicleTypeId;
        String vehicleTypeName;
        Integer vehicleTypeDetailId;
        String version;
        String color;
        String agencyName;
        Integer totalOrders = 0;
        Integer totalQuantity = 0;
        BigDecimal totalRevenue = BigDecimal.ZERO;
        BigDecimal totalDiscount = BigDecimal.ZERO;

        RevenueData(OrderDetail detail, Order order) {
            this.vehicleTypeId = Optional.ofNullable(detail.getVehicleTypeDetail())
                    .map(vtd -> vtd.getVehicleType().getVehicleTypeId()).orElse(null);
            this.vehicleTypeName = Optional.ofNullable(detail.getVehicleTypeDetail())
                    .map(vtd -> vtd.getVehicleType().getVehicleTypeName()).orElse(null);
            this.vehicleTypeDetailId = Optional.ofNullable(detail.getVehicleTypeDetail())
                    .map(vtd -> vtd.getVehicleTypeDetailId()).orElse(null);
            this.version = Optional.ofNullable(detail.getVehicleTypeDetail())
                    .map(vtd -> vtd.getVersion()).orElse(null);
            this.color = Optional.ofNullable(detail.getVehicleTypeDetail())
                    .map(vtd -> vtd.getColor()).orElse(null);
            this.agencyName = Optional.ofNullable(order.getAgency())
                    .map(a -> a.getAgencyName()).orElse("Unassigned");
        }

        RevenueReportResponseDTO toResponseDTO() {
            return new RevenueReportResponseDTO(vehicleTypeId, vehicleTypeName, vehicleTypeDetailId,
                    version, color, agencyName, totalOrders, totalQuantity, totalRevenue, totalDiscount);
        }
    }
}
