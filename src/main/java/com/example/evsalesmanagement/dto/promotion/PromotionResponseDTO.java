package com.example.evsalesmanagement.dto.promotion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.evsalesmanagement.dto.agency.AgencyResponseDTO;
import com.example.evsalesmanagement.dto.vehicletypedetail.VehicleTypeDetailResponseDTO;
import com.example.evsalesmanagement.model.Promotion;

public class PromotionResponseDTO {

    private Integer promotionId;

    private String promotionName;

    private String promotionType;

    private BigDecimal promotionValue;

    private String criteria;

    private BigDecimal discountAmount;

    private BigDecimal discountPercent;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String status;

    private List<VehicleTypeDetailResponseDTO> vehicleTypeDetails;

    private List<AgencyResponseDTO> agencies;

    public PromotionResponseDTO(Promotion promotion) {
        this.promotionId = promotion.getPromotionId();
        this.promotionName = promotion.getPromotionName();
        this.promotionType = promotion.getPromotionType();
        this.promotionValue = promotion.getPromotionValue();
        this.criteria = promotion.getCriteria();
        this.discountAmount = promotion.getDiscountAmount();
        this.discountPercent = promotion.getDiscountPercent();
        this.startDate = promotion.getStartDate();
        this.endDate = promotion.getEndDate();
        this.status = promotion.getStatus();
    }

    public PromotionResponseDTO(Integer promotionId, String promotionName, String promotionType,
            BigDecimal promotionValue, String criteria, BigDecimal discountAmount, BigDecimal discountPercent,
            LocalDateTime startDate, LocalDateTime endDate, String status,
            List<VehicleTypeDetailResponseDTO> vehicleTypeDetails,
            List<AgencyResponseDTO> agencies) {
        this.promotionId = promotionId;
        this.promotionName = promotionName;
        this.promotionType = promotionType;
        this.promotionValue = promotionValue;
        this.criteria = criteria;
        this.discountAmount = discountAmount;
        this.discountPercent = discountPercent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.vehicleTypeDetails = vehicleTypeDetails;
        this.agencies = agencies;
    }

    public PromotionResponseDTO() {
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }

    public BigDecimal getPromotionValue() {
        return promotionValue;
    }

    public void setPromotionValue(BigDecimal promotionValue) {
        this.promotionValue = promotionValue;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
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

    public List<VehicleTypeDetailResponseDTO> getVehicleTypeDetails() {
        return vehicleTypeDetails;
    }

    public void setVehicleTypeDetails(List<VehicleTypeDetailResponseDTO> vehicleTypeDetails) {
        this.vehicleTypeDetails = vehicleTypeDetails;
    }

    public List<AgencyResponseDTO> getAgencies() {
        return agencies;
    }

    public void setAgencies(List<AgencyResponseDTO> agencies) {
        this.agencies = agencies;
    }

}
