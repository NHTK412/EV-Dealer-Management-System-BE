package com.example.evsalesmanagement.enums;

public enum CustomerMembershipLevelEnum {

    COPPER("Hạng Đồng"),

    SILVER("Hạng Bạc"),

    GOLD("Hạng Vàng"),

    DIAMOND("Hạng Kim Cương");

    private final String displayName;

    CustomerMembershipLevelEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
