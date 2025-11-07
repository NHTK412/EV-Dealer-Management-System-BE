package com.example.evsalesmanagement.enums;

public enum AgencyStatusEnum {

    ACTIVE("Đang Hoạt Động"),

    INACTIVE("Dừng Hoạt Động");

    private final String displayName;

    AgencyStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

}
