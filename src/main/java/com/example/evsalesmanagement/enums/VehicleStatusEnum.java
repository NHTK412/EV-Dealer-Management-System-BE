package com.example.evsalesmanagement.enums;

public enum VehicleStatusEnum {

    IN_STOCK("Tồn Kho"),

    SOLD("Đã Bán"),

    IN_TRANSIT("Đang Trung Chuyển"),

    TEST_DRIVE("Xe Lái Thử");

    private final String displayName;

    VehicleStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
