package com.example.evsalesmanagement.utils;

import java.util.List;

import com.example.evsalesmanagement.dto.AgencyWholesalePrice.AgencyWholesalePriceResponseDTO;
import com.example.evsalesmanagement.dto.AgencyWholesalePrice.AgencyWholesalePriceSummaryDTO;
import com.example.evsalesmanagement.model.AgencyWholesalePrice;

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public ApiResponse(AgencyWholesalePriceResponseDTO agencyWholesalePriceResponseDTO) {
    }

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}