package com.example.evsalesmanagement.enums;

public enum FeedbackStatusEnum {
    NOT_YET_PROCESSED("Not yet processed"),
    IN_PROCESSED("In processed"),
    PROCESSED("Processed");

    private final String displayName;

    FeedbackStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


    public static FeedbackStatusEnum fromStringToEnum(String text) {
        for (FeedbackStatusEnum status : FeedbackStatusEnum.values()) {
            if (status.displayName.equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException(
            "invalid status: " + text);
    }


    public static boolean enumIsValid(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }
        
        for (FeedbackStatusEnum status : FeedbackStatusEnum.values()) {
            if (status.displayName.equalsIgnoreCase(text.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean canTransitionToNewStatus(FeedbackStatusEnum newStatus) {
        return switch (this) {
            case NOT_YET_PROCESSED-> newStatus == IN_PROCESSED;
            case IN_PROCESSED-> newStatus == PROCESSED;
            case PROCESSED -> false;
        };
    }


  public FeedbackStatusEnum getNext() {
        return switch (this) {
            case NOT_YET_PROCESSED -> IN_PROCESSED;
            case IN_PROCESSED -> PROCESSED;
            case PROCESSED -> null;
        };
    }


     public static FeedbackStatusEnum getDefaultStatus() {
        return NOT_YET_PROCESSED;
    }


    public boolean isFinalStatus() {
        return this == PROCESSED;
    }

    @Override 
    public String toString() {
        return this.displayName;
    }
}
