package com.example.evsalesmanagement.enums;

public enum FeedbackHandlingStatusEnum {
    COMPLETE ("Complete");

    private final String displayName;

    FeedbackHandlingStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static FeedbackHandlingStatusEnum getDefault() {
        return COMPLETE;
    }

    @Override
    public String toString() {
        return displayName;
    }
}