package com.example.evsalesmanagement.dto.promotion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.evsalesmanagement.enums.PromotionStatusEnum;
import com.example.evsalesmanagement.enums.PromotionTypeEnum;

public class PromotionRequestDTO {

    private String promotionName;

    private PromotionTypeEnum promotionType;

    private BigDecimal promotionValue;

    private String criteria;

    private BigDecimal discountAmount;

    private BigDecimal discountPercent;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private PromotionStatusEnum status;

    private List<Integer> vehicleTypeDetailsId;


    public PromotionRequestDTO() {
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public PromotionTypeEnum getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(PromotionTypeEnum promotionType) {
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

    public PromotionStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PromotionStatusEnum status) {
        this.status = status;
    }

    public List<Integer> getVehicleTypeDetailsId() {
        return vehicleTypeDetailsId;
    }

    public void setVehicleTypeDetailsId(List<Integer> vehicleTypeDetailsId) {
        this.vehicleTypeDetailsId = vehicleTypeDetailsId;
    }


    //     this.agencysId = agencysId;

}
