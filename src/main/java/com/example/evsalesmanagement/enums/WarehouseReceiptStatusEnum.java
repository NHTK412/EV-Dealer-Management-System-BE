package com.example.evsalesmanagement.enums;

public enum WarehouseReceiptStatusEnum {

    CREATED("Đã tạo"),

    PROCESSING("Đang xử lý"),

    RECEIVED("Đã nhập kho"),

    CANCELLED("Đã hủy"),

    PENDING_APPROVAL("Chờ phê duyệt");

    private final String displayName;

    WarehouseReceiptStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
