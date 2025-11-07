package com.example.evsalesmanagement.enums;

public enum PolicyTypeEnum {

    QUANTITY("Chiết Khấu Theo Số Lượng"),

    SALES("Chiết Khấu Theo Doanh Số");

    private final String displayName;

    PolicyTypeEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

}
