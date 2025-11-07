package com.example.evsalesmanagement.dto.promotion;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.evsalesmanagement.model.Promotion;

public class PromotionSummaryDTO {
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

    public PromotionSummaryDTO() {
    }

    public PromotionSummaryDTO(Promotion promotion) {
        this.promotionId = promotion.getPromotionId();
        this.promotionName = promotion.getPromotionName();
        this.promotionType = promotion.getPromotionType().getDisplayName();
        this.promotionValue = promotion.getPromotionValue();
        this.criteria = promotion.getCriteria();
        this.discountAmount = promotion.getDiscountAmount();
        this.discountPercent = promotion.getDiscountPercent();
        this.startDate = promotion.getStartDate();
        this.endDate =  promotion.getEndDate();
        this.status = promotion.getStatus().getDisplayName();
    }
        

    public PromotionSummaryDTO(Integer promotionId, String promotionName, String promotionType, BigDecimal promotionValue,
            String criteria, BigDecimal discountAmount, BigDecimal discountPercent, LocalDateTime startDate,
            LocalDateTime endDate, String status) {
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

    

}
