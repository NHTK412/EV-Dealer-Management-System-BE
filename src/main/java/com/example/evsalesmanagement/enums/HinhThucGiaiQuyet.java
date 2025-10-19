package com.example.evsalesmanagement.enums;

/**
 * Enum định nghĩa các hình thức giải quyết 
 * EMAIL, DIEN_THOAI
 */
public enum HinhThucGiaiQuyet {
    DIEN_THOAI("Điện thoại"),
    EMAIL("Email");

    private final String displayName;

    HinhThucGiaiQuyet(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Chuyển từ String qua Enum
     */
    public static HinhThucGiaiQuyet fromStringToEnum(String text) {
        for (HinhThucGiaiQuyet type : HinhThucGiaiQuyet.values()) {
            if (type.displayName.equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException(
            "Hình thức giải quyết không hợp lệ: " + text 
        );
    }

    /**
     * Kiểm tra xem text có phải enum hợp lệ hay không
     */
    public static boolean enumIsValid(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }

        for (HinhThucGiaiQuyet type : HinhThucGiaiQuyet.values()) {
            if (type.displayName.equalsIgnoreCase(text.trim())) {
                return true;
            }
        }
        return false;
    }


 @Override
    public String toString() {
        return displayName;
    }
}