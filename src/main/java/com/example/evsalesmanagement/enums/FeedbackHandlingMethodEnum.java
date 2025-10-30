package com.example.evsalesmanagement.enums;

public enum FeedbackHandlingMethodEnum {
    PHONE_NUMBER("Phone Number"),
    EMAIL("Email");

    private final String displayName;

    FeedbackHandlingMethodEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static FeedbackHandlingMethodEnum fromStringToEnum(String text) {
        for (FeedbackHandlingMethodEnum type : FeedbackHandlingMethodEnum.values()) {
            if (type.displayName.equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException(
            "Feedback handling method unavailable " + text 
        );
    }

    public static boolean enumIsValid(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }

        for (FeedbackHandlingMethodEnum type : FeedbackHandlingMethodEnum.values()) {
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