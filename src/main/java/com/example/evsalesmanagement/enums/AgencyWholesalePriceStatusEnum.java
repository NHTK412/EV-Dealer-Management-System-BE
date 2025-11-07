package com.example.evsalesmanagement.enums;

public enum AgencyWholesalePriceStatusEnum {
    NOT_ACTIVE("Chưa Hoạt Động"),

    ACTIVE("Đang Hoạt Động"),

    INACTIVE("Dừng Hoạt Động");

    private final String displayName;

    AgencyWholesalePriceStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
