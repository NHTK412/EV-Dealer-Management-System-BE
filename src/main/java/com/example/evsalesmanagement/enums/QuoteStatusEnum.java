package com.example.evsalesmanagement.enums;

public enum QuoteStatusEnum {

    CREATE("Đã Khởi Tạo"),

    PROCESSING("Đang xử lý"),

    REJECTED("Đã từ chối"),

    ORDERED("Đã chuyển thành đơn hàng");

    private final String displayName;

    QuoteStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
