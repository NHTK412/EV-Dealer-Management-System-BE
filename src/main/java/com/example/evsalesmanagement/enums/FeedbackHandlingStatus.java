package com.example.evsalesmanagement.enums;
/**
 * Enum trạng thái xử lý phản hồi, vì sau khi xử lý xong chỉ có 1 trạng thái là "Hoàn thành"
 */
public enum FeedbackHandlingStatus {
    //HOAN_THANH = COMPLETE
    COMPLETE ("Complete");

    private final String displayName;

    FeedbackHandlingStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static FeedbackHandlingStatus getDefault() {
        return COMPLETE;
    }

    @Override
    public String toString() {
        return displayName;
    }
}