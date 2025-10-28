package com.example.evsalesmanagement.enums;

/**
 * Enum định nghĩa các hình thức giải quyết 
 * EMAIL, DIEN_THOAI
 */

 //HinhThucGiaiQuyet = FeedbackHandlingMethod
public enum FeedbackHandlingMethod {
    PHONE_NUMBER("Phone Number"),
    EMAIL("Email");

    private final String displayName;

    FeedbackHandlingMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Chuyển từ String qua Enum
     */
    public static FeedbackHandlingMethod fromStringToEnum(String text) {
        for (FeedbackHandlingMethod type : FeedbackHandlingMethod.values()) {
            if (type.displayName.equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException(
            "Feedback handling method unavailable " + text 
        );
    }

    /**
     * Kiểm tra xem text có phải enum hợp lệ hay không
     */
    public static boolean enumIsValid(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }

        for (FeedbackHandlingMethod type : FeedbackHandlingMethod.values()) {
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