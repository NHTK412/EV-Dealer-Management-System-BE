package com.example.evsalesmanagement.service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.inventoryreport.InventoryReportRequestDTO;
import com.example.evsalesmanagement.dto.inventoryreport.InventoryReportResponseDTO;
import com.example.evsalesmanagement.exception.InternalServerException;
import com.example.evsalesmanagement.repository.InventoryReportRepository;

@Service
public class InventoryReportService {

    @Autowired
    private InventoryReportRepository inventoryReportRepository;

    public List<InventoryReportResponseDTO> getInventoryReport(InventoryReportRequestDTO request) {

        if (request.getToDate() != null) {
            request.setToDate(request.getToDate().withHour(23).withMinute(59).withSecond(59));
        }

        return inventoryReportRepository.getInventoryReportGrouped(request);
    }

    public byte[] exportInventoryReportToExcel(InventoryReportRequestDTO request) {
        try {
            List<InventoryReportResponseDTO> reportData = getInventoryReport(request);

            try (Workbook workbook = new XSSFWorkbook();
                 ByteArrayOutputStream out = new ByteArrayOutputStream()) {

                Sheet sheet = workbook.createSheet("Inventory Report");
                CellStyle headerStyle = createHeaderStyle(workbook);
                CellStyle titleStyle = createTitleStyle(workbook);
                CellStyle currencyStyle = createCurrencyStyle(workbook);
                CellStyle numberStyle = createNumberStyle(workbook);
                CellStyle totalStyle = createTotalStyle(workbook);

                int rowNum = 0;

                Row titleRow = sheet.createRow(rowNum++);
                Cell titleCell = titleRow.createCell(0);
                titleCell.setCellValue("INVENTORY REPORT");
                titleCell.setCellStyle(titleStyle);
                sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, 8));

                Row dateRow = sheet.createRow(rowNum++);
                Cell dateCell = dateRow.createCell(0);
                dateCell.setCellValue("Report date: " +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, 1, 0, 8));

                rowNum++;

                Row headerRow = sheet.createRow(rowNum++);
                String[] headers = {
                    "STT", "Name car", "Manufacture year", "Version",
                    "Color", "Price", "Agency", "Quantity", "Total value"
                };

                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                    cell.setCellStyle(headerStyle);
                }

                int stt = 1;
                BigDecimal grandTotal = BigDecimal.ZERO;
                long totalQuantity = 0L;

                for (InventoryReportResponseDTO data : reportData) {
                    Row row = sheet.createRow(rowNum++);

                    row.createCell(0).setCellValue(stt++);
                    row.createCell(1).setCellValue(data.getVehicleTypeName());
                    row.createCell(2).setCellValue(data.getManufactureYear() != null ? data.getManufactureYear() : 0);
                    row.createCell(3).setCellValue(data.getVersion() != null ? data.getVersion() : "");
                    row.createCell(4).setCellValue(data.getColor() != null ? data.getColor() : "");

                    Cell priceCell = row.createCell(5);
                    if (data.getPrice() != null) {
                        priceCell.setCellValue(data.getPrice().doubleValue());
                        priceCell.setCellStyle(currencyStyle);
                    }

                    row.createCell(6).setCellValue(data.getAgencyName() != null ? data.getAgencyName() : "Unassigned");

                    Cell qtyCell = row.createCell(7);
                    if (data.getTotalQuantity() != null) {
                        qtyCell.setCellValue(data.getTotalQuantity());
                        qtyCell.setCellStyle(numberStyle);
                        totalQuantity += data.getTotalQuantity();
                    }

                    Cell totalCell = row.createCell(8);
                    if (data.getTotalValue() != null) {
                        totalCell.setCellValue(data.getTotalValue().doubleValue());
                        totalCell.setCellStyle(currencyStyle);
                        grandTotal = grandTotal.add(data.getTotalValue());
                    }
                }

                Row totalRow = sheet.createRow(rowNum);
                Cell totalLabelCell = totalRow.createCell(0);
                totalLabelCell.setCellValue("SUMMARY");
                totalLabelCell.setCellStyle(totalStyle);
                sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(rowNum, rowNum, 0, 6));

                Cell totalQtyCell = totalRow.createCell(7);
                totalQtyCell.setCellValue(totalQuantity);
                totalQtyCell.setCellStyle(totalStyle);

                Cell grandTotalCell = totalRow.createCell(8);
                grandTotalCell.setCellValue(grandTotal.doubleValue());
                grandTotalCell.setCellStyle(totalStyle);

                for (int i = 0; i < headers.length; i++) {
                    sheet.autoSizeColumn(i);
                }

                workbook.write(out);
                return out.toByteArray();
            }

        } catch (Exception e) {
            throw new InternalServerException("Error when exporting inventory report", e);
        }
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private CellStyle createTitleStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        font.setColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    private CellStyle createCurrencyStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("#,##0 ₫"));
        style.setAlignment(HorizontalAlignment.RIGHT);
        return style;
    }

    private CellStyle createNumberStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("#,##0"));
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private CellStyle createTotalStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("#,##0 ₫"));
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderTop(BorderStyle.DOUBLE);
        return style;
    }
}
