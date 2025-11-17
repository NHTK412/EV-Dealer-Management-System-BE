package com.example.evsalesmanagement.enums;

public enum PromotionStatusEnum {

        NOT_ACTIVE("Chưa Hoạt Động"),

        ACTIVE("Đang Hoạt Động"),

        INACTIVE("Dừng Hoạt Động");

    private final String displayName;

    PromotionStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
