package com.example.evsalesmanagement.enums;

public enum CustomerGenderEnum {

    MALE("Nam Giới"),

    FEMALE("Nữ Giới");

    private final String displayName;

    CustomerGenderEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
