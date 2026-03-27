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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.inventoryreport.InventoryReportRequestDTO;
import com.example.evsalesmanagement.dto.inventoryreport.InventoryReportResponseDTO;
import com.example.evsalesmanagement.dto.inventoryreport.InventorySummaryDTO;
import com.example.evsalesmanagement.dto.vehicle.VehicleResponseDTO;
import com.example.evsalesmanagement.dto.vehicle.VehicleSummaryDTO;
import com.example.evsalesmanagement.exception.InternalServerException;
import com.example.evsalesmanagement.model.Vehicle;
import com.example.evsalesmanagement.repository.InventoryReportRepository;
import com.example.evsalesmanagement.repository.VehicleTypeDetailRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InventoryReportService {

    @Autowired
    private InventoryReportRepository inventoryReportRepository;

    @Autowired
    private VehicleTypeDetailRepository vehicleTypeDetailRepository;

    public List<InventorySummaryDTO> getFullInventoryReport(Integer agencyId) {

        return vehicleTypeDetailRepository.findInventoryByAgencyId(agencyId);

    }

    public List<VehicleSummaryDTO> getVehiclesByVehicleTypeDetailInInventory(
            Integer agencyId, Integer vehicleTypeDetailId) {

        List<Vehicle> vehicles = vehicleTypeDetailRepository
                .findVehiclesByVehicleTypeDetailInInventory(agencyId, vehicleTypeDetailId);

        return vehicles.stream()
                .map(v -> new VehicleSummaryDTO(v))
                .toList();

    }

    public List<InventoryReportResponseDTO> getInventoryReport(InventoryReportRequestDTO request) {
        if (request.getToDate() != null) {
            request.setToDate(request.getToDate().withHour(23).withMinute(59).withSecond(59));
        }
        
        List<InventoryReportResponseDTO> result = inventoryReportRepository.getInventoryReportGrouped(request);
        log.info("Found {} records for inventory report", result.size());
        
        return result;
    }

    public byte[] exportInventoryReportToExcel(InventoryReportRequestDTO request) {
        try {
            // Get data first
            List<InventoryReportResponseDTO> reportData = getInventoryReport(request);
            log.info("Exporting {} records to Excel", reportData.size());

            try (Workbook workbook = new XSSFWorkbook();
                    ByteArrayOutputStream out = new ByteArrayOutputStream()) {

                Sheet sheet = workbook.createSheet("Inventory Report");
                
                // Tạo các kiểu cell styles
                CellStyle headerStyle = createHeaderStyle(workbook);
                CellStyle titleStyle = createTitleStyle(workbook);
                CellStyle currencyStyle = createCurrencyStyle(workbook);
                CellStyle numberStyle = createNumberStyle(workbook);
                CellStyle totalLabelStyle = createTotalLabelStyle(workbook);
                CellStyle totalNumberStyle = createTotalNumberStyle(workbook);
                CellStyle totalCurrencyStyle = createTotalCurrencyStyle(workbook);

                int rowNum = 0;

                // Tiêu đề báo cáo
                Row titleRow = sheet.createRow(rowNum++);
                Cell titleCell = titleRow.createCell(0);
                titleCell.setCellValue("BÁO CÁO TỒN KHO");
                titleCell.setCellStyle(titleStyle);
                
                // Tạo các ô trống
                for (int i = 1; i <= 8; i++) {
                    titleRow.createCell(i);
                }
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));

                // Dòng ngày
                Row dateRow = sheet.createRow(rowNum++);
                Cell dateCell = dateRow.createCell(0);
                String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                dateCell.setCellValue("Ngày xuất báo cáo: " + currentDate);
                
                // Tạo các ô trống
                for (int i = 1; i <= 8; i++) {
                    dateRow.createCell(i);
                }
                sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 8));

                rowNum++;

                // Dòng tiêu đề
                Row headerRow = sheet.createRow(rowNum++);
                String[] headers = {
                    "STT", "Tên xe", "Năm SX", "Phiên bản",
                    "Màu sắc", "Giá bán", "Đại lý", "Số lượng", "Tổng giá trị"
                };

                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                    cell.setCellStyle(headerStyle);
                }

                // Dòng dữ liệu
                int stt = 1;
                BigDecimal grandTotal = BigDecimal.ZERO;
                long totalQuantity = 0L;

                if (reportData.isEmpty()) {
                    log.warn("No data found for export");
                    Row noDataRow = sheet.createRow(rowNum++);
                    Cell noDataCell = noDataRow.createCell(0);
                    noDataCell.setCellValue("Không có dữ liệu");
                    sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 0, 8));
                } else {
                    for (InventoryReportResponseDTO data : reportData) {
                        Row row = sheet.createRow(rowNum++);

                        // STT
                        Cell sttCell = row.createCell(0);
                        sttCell.setCellValue(stt++);
                        
                        // Vehicle Type Name
                        Cell nameCell = row.createCell(1);
                        nameCell.setCellValue(data.getVehicleTypeName() != null ? data.getVehicleTypeName() : "");
                        
                        // Manufacture Year
                        Cell yearCell = row.createCell(2);
                        yearCell.setCellValue(data.getManufactureYear() != null ? data.getManufactureYear() : 0);
                        
                        // Version
                        Cell versionCell = row.createCell(3);
                        versionCell.setCellValue(data.getVersion() != null ? data.getVersion() : "");
                        
                        // Color
                        Cell colorCell = row.createCell(4);
                        colorCell.setCellValue(data.getColor() != null ? data.getColor() : "");

                        // Price
                        Cell priceCell = row.createCell(5);
                        if (data.getPrice() != null) {
                            priceCell.setCellValue(data.getPrice().doubleValue());
                            priceCell.setCellStyle(currencyStyle);
                        } else {
                            priceCell.setCellValue(0);
                            priceCell.setCellStyle(currencyStyle);
                        }

                        // Agency Name
                        Cell agencyCell = row.createCell(6);
                        agencyCell.setCellValue(data.getAgencyName() != null ? data.getAgencyName() : "Chưa phân bổ");

                        // Quantity
                        Cell qtyCell = row.createCell(7);
                        if (data.getTotalQuantity() != null) {
                            qtyCell.setCellValue(data.getTotalQuantity());
                            qtyCell.setCellStyle(numberStyle);
                            totalQuantity += data.getTotalQuantity();
                        } else {
                            qtyCell.setCellValue(0);
                            qtyCell.setCellStyle(numberStyle);
                        }

                        // Total Value
                        Cell totalCell = row.createCell(8);
                        if (data.getTotalValue() != null) {
                            totalCell.setCellValue(data.getTotalValue().doubleValue());
                            totalCell.setCellStyle(currencyStyle);
                            grandTotal = grandTotal.add(data.getTotalValue());
                        } else {
                            totalCell.setCellValue(0);
                            totalCell.setCellStyle(currencyStyle);
                        }
                    }

                    // Tổng hợp dòng
                    Row totalRow = sheet.createRow(rowNum);
                    
                    Cell totalLabelCell = totalRow.createCell(0);
                    totalLabelCell.setCellValue("TỔNG CỘNG");
                    totalLabelCell.setCellStyle(totalLabelStyle);
                    
                    for (int i = 1; i <= 6; i++) {
                        Cell cell = totalRow.createCell(i);
                        cell.setCellStyle(totalLabelStyle);
                    }
                    
                    sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 6));

                    // Tổng số lượng
                    Cell totalQtyCell = totalRow.createCell(7);
                    totalQtyCell.setCellValue(totalQuantity);
                    totalQtyCell.setCellStyle(totalNumberStyle);

                    // Tổng giá trị
                    Cell grandTotalCell = totalRow.createCell(8);
                    grandTotalCell.setCellValue(grandTotal.doubleValue());
                    grandTotalCell.setCellStyle(totalCurrencyStyle);
                }

                // Tự động điều chỉnh kích thước cột
                for (int i = 0; i < headers.length; i++) {
                    sheet.autoSizeColumn(i);
                    int currentWidth = sheet.getColumnWidth(i);
                    sheet.setColumnWidth(i, currentWidth + 512);
                }

                // Ghi dữ liệu ra OutputStream
                workbook.write(out);
                byte[] result = out.toByteArray();
                log.info("Excel file created successfully, size: {} bytes", result.length);
                
                return result;
            }

        } catch (Exception e) {
            log.error("Error exporting inventory report to Excel", e);
            throw new InternalServerException("Failed to export inventory report to Excel", e);
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

    private CellStyle createTotalLabelStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderTop(BorderStyle.DOUBLE);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private CellStyle createTotalNumberStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("#,##0"));
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderTop(BorderStyle.DOUBLE);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private CellStyle createTotalCurrencyStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("#,##0 ₫"));
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderTop(BorderStyle.DOUBLE);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }
}