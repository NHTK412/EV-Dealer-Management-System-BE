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
@Table(name = "SaleDiscountLevel")
public class SalesDiscountLevel extends Base {
    // ma bac chiet khau doanh so = salesDiscountLevelId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SalesDiscountLevelId")
    private Integer salesDiscountLevelId;

    // doanh so tu = salesFrom
    @Column(name = "SalesFrom")
    private BigDecimal salesFrom;

    // doanh so den = salesTo
    @Column(name = "SalesTo")
    private BigDecimal salesTo;

    // phan tram chiet khau = discountPercentage
    @Column(name = "DiscountPercentage")
    private BigDecimal discountPercentage;

    // ma chinh sach = policyId
    @ManyToOne
    @JoinColumn(name = "PolicyId")
    private Policy policy;

    public Integer getSalesDiscountLevelId() {
        return salesDiscountLevelId;
    }

    public void setSalesDiscountLevelId(Integer salesDiscountLevelId) {
        this.salesDiscountLevelId = salesDiscountLevelId;
    }

    public BigDecimal getSalesFrom() {
        return salesFrom;
    }

    public void setSalesFrom(BigDecimal salesFrom) {
        this.salesFrom = salesFrom;
    }

    public BigDecimal getSalesTo() {
        return salesTo;
    }

    public void setSalesTo(BigDecimal salesTo) {
        this.salesTo = salesTo;
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