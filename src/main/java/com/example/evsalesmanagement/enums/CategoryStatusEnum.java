package com.example.evsalesmanagement.enums;

public enum CategoryStatusEnum {

    ACTIVE("Hoạt Động"),

    INACTIVE("Không Hoạt Động");

    private final String displayName;

    CategoryStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
