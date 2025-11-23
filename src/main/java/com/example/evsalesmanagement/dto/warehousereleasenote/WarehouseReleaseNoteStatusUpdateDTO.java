package com.example.evsalesmanagement.dto.warehousereleasenote;

import com.example.evsalesmanagement.enums.WarehouseReleaseNoteStatusEnum;

public class WarehouseReleaseNoteStatusUpdateDTO {
    private WarehouseReleaseNoteStatusEnum status;

    public WarehouseReleaseNoteStatusEnum getStatus() {
        return status;
    }

    public void setStatus(WarehouseReleaseNoteStatusEnum status) {
        this.status = status;
    }
}
