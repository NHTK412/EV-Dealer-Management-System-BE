package com.example.evsalesmanagement.enums;

public enum OrderTypeEnum {

    // RETAIL_CUSTOMER("Bán lẻ cho khách hàng"),

    // WHOLESALE_CUSTOMER("Bán sỉ cho khách hàng"),

    // RETAIL_AGENCY("Bán lẻ cho đại lý"),

    // WHOLESALE_AGENCY("Bán sỉ cho đại lý");

    CUSTOMER("Bán cho khách hàng"),

    AGENCY("Bán cho đại lý");

    private final String displayName;

    OrderTypeEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

}
