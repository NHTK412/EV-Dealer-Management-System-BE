package com.example.evsalesmanagement.enums;

public enum GenderEnum {

    MALE("Nam Giới"),

    FEMALE("Nữ Giới");

    private final String displayName;

    GenderEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
