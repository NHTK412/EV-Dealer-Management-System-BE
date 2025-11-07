package com.example.evsalesmanagement.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.evsalesmanagement.enums.PromotionStatusEnum;
import com.example.evsalesmanagement.enums.PromotionTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

//Promotion = khuyen mai
@Entity
@Table(name = "Promotion")
public class Promotion extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PromotionId")
    private Integer promotionId;

    @Column(name = "PromotionName", nullable = false)
    private String promotionName;

    @Enumerated(EnumType.STRING)
    @Column(name = "PromotionType")
    private PromotionTypeEnum promotionType;

    // promotion value = gia tri khuyen mai --> hình như cái này thừa
    @Column(name = "PromotionValue")
    private BigDecimal promotionValue;

    // criteria = tieu chi
    @Column(name = "Criteria")
    private String criteria;

    // discount amount = so tien giam gia
    @Column(name = "DiscountAmount")
    private BigDecimal discountAmount;

    // discount percent = phan tram giam gia

    @Column(name = "DiscountPercent")
    private BigDecimal discountPercent;

    @Column(name = "StartDate")
    private LocalDateTime startDate;

    @Column(name = "EndDate")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private PromotionStatusEnum status;

    @ManyToMany
    @JoinTable(name = "Promotion_VehicleDetail", joinColumns = @JoinColumn(name = "PromotionId"), inverseJoinColumns = @JoinColumn(name = "VehicleDetailId"))
    private List<VehicleTypeDetail> vehicleDetails = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "Promotion_Agency", joinColumns = @JoinColumn(name = "PromotionId"), inverseJoinColumns = @JoinColumn(name = "AgencyId"))
    private List<Agency> agencies = new ArrayList<>();

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

    public List<VehicleTypeDetail> getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(List<VehicleTypeDetail> vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public List<Agency> getAgencies() {
        return agencies;
    }

    public void setAgencies(List<Agency> agencies) {
        this.agencies = agencies;
    }

    @Override
    public String toString() {
        return "Promotion [promotionId=" + promotionId +
                ", promotionName=" + promotionName +
                ", promotionType=" + promotionType +
                ", promotionValue=" + promotionValue +
                ", criteria=" + criteria +
                ", discountAmount=" + discountAmount +
                ", discountPercent=" + discountPercent +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", vehicleDetails=" + vehicleDetails +
                ", agencies=" + agencies + "]";
    }

}
