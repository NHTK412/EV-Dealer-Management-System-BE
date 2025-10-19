package com.example.evsalesmanagement.exception;

/**
 * Exception khi không tìm thấy resource
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

