package com.example.evsalesmanagement.enums;

public enum AgencyTypeEnum {

    DEALER("Đại lý phân phối"),
    MANUFACTURER("Hãng phân phối");

    private final String displayName;

    AgencyTypeEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

}
