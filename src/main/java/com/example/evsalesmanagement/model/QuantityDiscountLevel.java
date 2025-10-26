package com.example.evsalesmanagement.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


//BacChietKhauSoLuong = QuantityDiscountLevel
@Entity
@Table(name = "QuantityDiscountLevel")
public class QuantityDiscountLevel extends TimeStampRecord{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuantityDiscountLevelId")
    private Integer quantityDiscountLevelId;

    //so luong tu = quantityFrom
    @Column(name = "QuantityFrom")
    private Integer quantityFrom;
    //so luong den = quantityTo1
    @Column(name = "QuantityTo")
    private Integer quantityTo;

    //phan tram chiet khau = DisountPercentage 
    @Column(name = "DiscountPercentage")
    private BigDecimal discountPercentage;

    //ma chinh sach = policyId
    @ManyToOne
    @JoinColumn(name = "PolicyId")
    private Policy policy;

    public Integer getQuantityDiscountLevelId() {
        return quantityDiscountLevelId;
    }

    public void setQuantityDiscountLevelId(Integer quantityDiscountLevelId) {
        this.quantityDiscountLevelId = quantityDiscountLevelId;
    }

    public Integer getQuantityFrom() {
        return quantityFrom;
    }

    public void setQuantityFrom(Integer quantityFrom) {
        this.quantityFrom = quantityFrom;
    }

    public Integer getQuantityTo() {
        return quantityTo;
    }

    public void setQuantityTo(Integer quantityTo) {
        this.quantityTo = quantityTo;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    
}