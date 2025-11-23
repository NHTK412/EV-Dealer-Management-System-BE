package com.example.evsalesmanagement.dto.warehouseimportreceipt;

import com.example.evsalesmanagement.enums.WarehouseReceiptStatusEnum;

public class WarehouseReceiptStatusUpdateDTO {
    private WarehouseReceiptStatusEnum status;

    public WarehouseReceiptStatusEnum getStatus() {
        return status;
    }

    public void setStatus(WarehouseReceiptStatusEnum status) {
        this.status = status;
    }
}
