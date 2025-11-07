package com.example.evsalesmanagement.enums;

public enum VehicleDeliveryStatusEnum {

    PREPARING("Chờ Chuẩn Bị"),

    DELIVERING("Đang Giao"),

    DELIVERED("Đã Giao"),

    RESCHEDULED("Đã Hẹn Lại");

    private final String displayName;

    VehicleDeliveryStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
