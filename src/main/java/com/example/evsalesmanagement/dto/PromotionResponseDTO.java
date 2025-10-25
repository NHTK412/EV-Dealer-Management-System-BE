package com.example.evsalesmanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.evsalesmanagement.model.Promotion;

public class PromotionResponseDTO{

    private Integer promotionId;
    private String promotionName;
    private String promotionType;
    private BigDecimal promotionValue;
    private String condition;
    private BigDecimal discountAmount;
    private BigDecimal discountPercent;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;
    private List<VehicleTypeDetailDTO> vehicleTypeDetails;
    private List<AgencyDTO> agencies;

    public PromotionResponseDTO(Promotion promotion) {
        this.promotionId = promotion.getPromotionId();
        this.promotionName = promotion.getPromotionName();
        this.promotionType = promotion.getPromotionType();
        this.promotionValue = promotion.getPromotionValue(); // bạn cần sửa getter trong entity Promotion tương ứng
        this.condition = promotion.getCondition();
        this.discountAmount = promotion.getDiscountAmount();
        this.discountPercent = promotion.getDiscountPercent();
        this.startDate = promotion.getStartDate();
        this.endDate = promotion.getEndDate();
        this.status = promotion.getStatus();
    }

    public PromotionResponseDTO(Integer promotionId, String promotionName, String promotionType,
            BigDecimal promotionValue, String condition, BigDecimal discountAmount, BigDecimal discountPercent,
            LocalDateTime startDate, LocalDateTime endDate, String status,
            List<VehicleTypeDetailDTO> vehicleTypeDetails, List<AgencyDTO> agencies) {
        this.promotionId = promotionId;
        this.promotionName = promotionName;
        this.promotionType = promotionType;
        this.promotionValue = promotionValue;
        this.condition = condition;
        this.discountAmount = discountAmount;
        this.discountPercent = discountPercent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.vehicleTypeDetails = vehicleTypeDetails;
        this.agencies = agencies;
    }

    public PromotionDetailDTO() {
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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

    public List<VehicleTypeDetailDTO> getVehicleTypeDetails() {
        return vehicleTypeDetails;
    }

    public void setVehicleTypeDetails(List<VehicleTypeDetailDTO> vehicleTypeDetails) {
        this.vehicleTypeDetails = vehicleTypeDetails;
    }

    public List<AgencyDTO> getAgencies() {
        return agencies;
    }

    public void setAgencies(List<AgencyDTO> agencies) {
        this.agencies = agencies;
    }
}
