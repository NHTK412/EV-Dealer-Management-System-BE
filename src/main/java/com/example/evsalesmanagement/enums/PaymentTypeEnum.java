package com.example.evsalesmanagement.enums;

public enum PaymentTypeEnum {

    FULL_PAYMENT("Trả thẳng"),

    INSTALLMENT("Trả góp");

    private final String displayName;

    PaymentTypeEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
