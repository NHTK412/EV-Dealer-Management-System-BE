package com.example.evsalesmanagement.dto.Warehouse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.example.evsalesmanagement.model.WarehouseReceipt;

public class WarehouseReceiptSummaryDTO {
    private Integer warehouseReceiptId;
    private LocalDateTime warehouseReceiptDate;
    private String reason;
    private BigDecimal totalAmount;
    private String note;

    public WarehouseReceiptSummaryDTO() {}

    public WarehouseReceiptSummaryDTO(WarehouseReceipt receipt) {
        this.warehouseReceiptId = receipt.getWarehouseReceiptId();
        this.warehouseReceiptDate = receipt.getWareHouseReceiptDate();
        this.reason = receipt.getReason();
        this.totalAmount = receipt.getTotalAmount();
        this.note = receipt.getNote();
    }

    public Integer getWarehouseReceiptId() {
        return warehouseReceiptId;
    }
    public void setWarehouseReceiptId(Integer warehouseReceiptId) {
        this.warehouseReceiptId = warehouseReceiptId;
    }
    public LocalDateTime getWarehouseReceiptDate() {
        return warehouseReceiptDate;
    }
    public void setWarehouseReceiptDate(LocalDateTime warehouseReceiptDate) {
        this.warehouseReceiptDate = warehouseReceiptDate;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
}