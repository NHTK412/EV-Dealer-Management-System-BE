package com.example.evsalesmanagement.dto.warehouseimportreceipt;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.evsalesmanagement.enums.WarehouseReceiptStatusEnum;

public class WarehouseReceiptSummaryDTO {
    private Integer warehouseReceiptId;
    private LocalDateTime warehouseReceiptDate;
    private String reason;
    private BigDecimal totalAmount;
    private String note;
    private WarehouseReceiptStatusEnum status;

    public WarehouseReceiptSummaryDTO() {}

    public WarehouseReceiptSummaryDTO(com.example.evsalesmanagement.model.WarehouseReceipt entity) {
        this.warehouseReceiptId = entity.getWarehouseReceiptId();
        this.warehouseReceiptDate = entity.getWarehouseReceiptDate();
        this.reason = entity.getReason();
        this.totalAmount = entity.getTotalAmount();
        this.note = entity.getNote();
        this.status = entity.getStatus();
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
    public WarehouseReceiptStatusEnum getStatus() {
        return status;
    }
    public void setStatus(WarehouseReceiptStatusEnum status) {
        this.status = status;
    }
}
