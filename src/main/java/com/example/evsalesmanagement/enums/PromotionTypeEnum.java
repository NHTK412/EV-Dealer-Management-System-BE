package com.example.evsalesmanagement.enums;

public enum PromotionTypeEnum {

    AMOUNT("Giảm tiền"),
    
    PERCENTAGE("Giảm phần trăm");

    private final String displayName;

    PromotionTypeEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
