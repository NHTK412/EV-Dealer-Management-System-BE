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
@Table(name = "QuantityDiscountTier")
public class QuantityDiscountTier extends GhiNhanThoiGian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuantityDiscountTierId")
    private Integer quantityDiscountTierId;

    @Column(name = "QuantityFrom")
    private Integer quantityFrom;

    @Column(name = "QuantityTo")
    private Integer quantityTo;

    @Column(name = "DiscountPercentage")
    private BigDecimal discountPercentage;

    @ManyToOne
    @JoinColumn(name = "PolicyId")
    private DiscountPolicy discountPolicy;

    public Integer getQuantityDiscountTierId() {
        return quantityDiscountTierId;
    }

    public void setQuantityDiscountTierId(Integer quantityDiscountTierId) {
        this.quantityDiscountTierId = quantityDiscountTierId;
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

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }

    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
}
