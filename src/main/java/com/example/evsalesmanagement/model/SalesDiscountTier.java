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

@Entity
@Table(name = "SalesDiscountTier")
public class SalesDiscountTier extends GhiNhanThoiGian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SalesDiscountTierId")
    private Integer salesDiscountTierId;

    @Column(name = "SalesFrom")
    private Integer salesFrom;

    @Column(name = "SalesTo")
    private Integer salesTo;

    @Column(name = "DiscountPercentage")
    private BigDecimal discountPercentage;

    @ManyToOne
    @JoinColumn(name = "PolicyId")
    private DiscountPolicy discountPolicy;

    public Integer getSalesDiscountTierId() {
        return salesDiscountTierId;
    }

    public void setSalesDiscountTierId(Integer salesDiscountTierId) {
        this.salesDiscountTierId = salesDiscountTierId;
    }

    public Integer getSalesFrom() {
        return salesFrom;
    }

    public void setSalesFrom(Integer salesFrom) {
        this.salesFrom = salesFrom;
    }

    public Integer getSalesTo() {
        return salesTo;
    }

    public void setSalesTo(Integer salesTo) {
        this.salesTo = salesTo;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }

    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
}
