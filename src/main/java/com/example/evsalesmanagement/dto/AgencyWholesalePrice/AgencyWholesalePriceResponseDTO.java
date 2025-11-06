package com.example.evsalesmanagement.dto.AgencyWholesalePrice;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import com.example.evsalesmanagement.dto.AgencyDTO;
import com.example.evsalesmanagement.dto.VehicleTypeDetailDTO;
import com.example.evsalesmanagement.model.AgencyWholesalePrice;

public class AgencyWholesalePriceResponseDTO {
    private Integer agencyWholesalePriceId;
    private BigDecimal wholesalePrice;
    private Integer minimumQuantity;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;
    private AgencyDTO agency;
    private VehicleTypeDetailDTO vehicleTypeDetail;

    public AgencyWholesalePriceResponseDTO() {
    }
    
    public AgencyWholesalePriceResponseDTO(AgencyWholesalePrice agencyWholesalePrice) {
        this.agencyWholesalePriceId = agencyWholesalePrice.getAgencyWholesalePriceId();
        this.wholesalePrice = agencyWholesalePrice.getWholesalePrice();
        this.minimumQuantity = agencyWholesalePrice.getMinimumQuantity();
        this.startDate = agencyWholesalePrice.getStartDate();
        this.endDate = agencyWholesalePrice.getEndDate();
        this.status = agencyWholesalePrice.getStatus();
    }

    public AgencyWholesalePriceResponseDTO(Integer agencyWholesalePriceId, BigDecimal wholesalePrice, Integer minimumQuantity,
            LocalDateTime startDate, LocalDateTime endDate, String status, AgencyDTO agency,
           VehicleTypeDetailDTO vehicleTypeDetail) {
        this.agencyWholesalePriceId = agencyWholesalePriceId;
        this.wholesalePrice = wholesalePrice;
        this.minimumQuantity = minimumQuantity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.agency = agency;
        this.vehicleTypeDetail = vehicleTypeDetail;
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

    public AgencyDTO getAgency() {
        return agency;
    }

    public void setAgency(AgencyDTO agency) {
        this.agency = agency;
    }

    public VehicleTypeDetailDTO getVehicleTypeDetail() {
        return vehicleTypeDetail;
    }

    public void setVehicleTypeDetail(VehicleTypeDetailDTO vehicleTypeDetail) {
        this.vehicleTypeDetail = vehicleTypeDetail;
    }

   
   

    
}

