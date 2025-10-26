package com.example.evsalesmanagement.enums;

/*
 * Enum định nghĩa trạng thái phản hồi 
 * CHUA_XU_LY -> DANG_XU_LY -> DA_XU_LY
 */

public enum FeedbackStatus {
    NOT_YET_PROCESSED("Not yet processed"),
    IN_PROCESSED("In processed"),
    PROCESSED("Processed");

    private final String displayName;

    FeedbackStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


    /*
     * Chuyển từ String qua Enum
     */
    public static FeedbackStatus fromStringToEnum(String text) {
        for (FeedbackStatus status : FeedbackStatus.values()) {
            if (status.displayName.equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException(
            "invalid status: " + text);
    }


    /*
     * Kiểm tra xem text có phải enum hợp lệ hay không
     */
    public static boolean enumIsValid(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }
        
        for (FeedbackStatus status : FeedbackStatus.values()) {
            if (status.displayName.equalsIgnoreCase(text.trim())) {
                return true;
            }
        }
        return false;
    }


    /**
     * Kiểm tra chuyển trạng thái hợp lệ hay không
     */
    public boolean canTransitionToNewStatus(FeedbackStatus newStatus) {
        return switch (this) {
            case NOT_YET_PROCESSED-> newStatus == IN_PROCESSED;
            case IN_PROCESSED-> newStatus == PROCESSED;
            case PROCESSED -> false;
        };
    }


    /**
     * Lấy trạng thái tiếp theo
     */
  public FeedbackStatus getNext() {
        return switch (this) {
            case NOT_YET_PROCESSED -> IN_PROCESSED;
            case IN_PROCESSED -> PROCESSED;
            case PROCESSED -> null;
        };
    }


    /**
     * Trạng thái mặc định khi tạo phản hồi mới
     */
     public static FeedbackStatus getDefaultStatus() {
        return NOT_YET_PROCESSED;
    }


    /**
     * Kiểm tra xem trạng thái có phải là trạng thái cuối cùng không
     */
    public boolean isFinalStatus() {
        return this == PROCESSED;
    }

    @Override 
    public String toString() {
        return this.displayName;
    }
}
