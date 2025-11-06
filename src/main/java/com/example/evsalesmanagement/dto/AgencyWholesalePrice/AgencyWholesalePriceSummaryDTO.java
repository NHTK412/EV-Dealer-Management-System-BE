package com.example.evsalesmanagement.dto.agencywholesaleprice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.evsalesmanagement.model.AgencyWholesalePrice;

public class AgencyWholesalePriceSummaryDTO {
    private Integer agencyWholesalePriceId;
    private BigDecimal wholesalePrice;
    private Integer minimumQuantity;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;

    public AgencyWholesalePriceSummaryDTO() {
    }
    
    public AgencyWholesalePriceSummaryDTO(AgencyWholesalePrice agencyWholesalePrice) {
        this.agencyWholesalePriceId = agencyWholesalePrice.getAgencyWholesalePriceId();
        this.wholesalePrice = agencyWholesalePrice.getWholesalePrice();
        this.minimumQuantity = agencyWholesalePrice.getMinimumQuantity();
        this.startDate = agencyWholesalePrice.getStartDate();
        this.endDate = agencyWholesalePrice.getEndDate();
        this.status = agencyWholesalePrice.getStatus();
    }

    public AgencyWholesalePriceSummaryDTO(Integer agencyWholesalePriceId, BigDecimal wholesalePrice, Integer minimumQuantity,
            LocalDateTime startDate, LocalDateTime endDate, String status, List<Integer> agenciesId,
            List<Integer> vehicleTypeDetailsId) {
        this.agencyWholesalePriceId = agencyWholesalePriceId;
        this.wholesalePrice = wholesalePrice;
        this.minimumQuantity = minimumQuantity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Integer getAgencyWholesalePriceId() {
        return agencyWholesalePriceId;
    }

    public void setAgencyWholesalePriceId(Integer agencyWholesalePriceId) {
        this.agencyWholesalePriceId = agencyWholesalePriceId;
    }

    public BigDecimal getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(BigDecimal wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}

