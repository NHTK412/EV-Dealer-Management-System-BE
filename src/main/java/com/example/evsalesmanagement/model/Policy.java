package com.example.evsalesmanagement.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.evsalesmanagement.enums.PolicyStatusEnum;
import com.example.evsalesmanagement.enums.PolicyTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//ChinhSachChietKhau = Policy
@Entity
@Table(name = "DiscountPolicy")
public class Policy extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PolicyId")
    private Integer policyId;

    @Enumerated(EnumType.STRING)
    @Column(name = "PolicyType")
    private PolicyTypeEnum policyType;

    @Column(name = "PolicyValue")
    private BigDecimal policyValue;

    // condition = dieu kien ap dung
    @Column(name = "PolicyCondition")
    private String policyCondition;

    @Column(name = "StartDate")
    private LocalDateTime startDate;

    @Column(name = "EndDate")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private PolicyStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "AgencyId" , unique = true)
    private Agency agency;

    @OneToMany(mappedBy = "policy")
    private List<QuantityDiscountLevel> quantityDiscountLevel = new ArrayList<>(); 

    @OneToMany(mappedBy = "policy" )
    private List<SalesDiscountLevel> saleDiscountLevel = new ArrayList<>();


    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public PolicyTypeEnum getPolicyType() {
        return policyType;
    }

    public void setPolicyType(PolicyTypeEnum policyType) {
        this.policyType = policyType;
    }

    public BigDecimal getPolicyValue() {
        return policyValue;
    }

    public void setPolicyValue(BigDecimal policyValue) {
        this.policyValue = policyValue;
    }

    public String getPolicyCondition() {
        return policyCondition;
    }

    public void setPolicyCondition(String policyCondition) {
        this.policyCondition = policyCondition;
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

    public PolicyStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PolicyStatusEnum status) {
        this.status = status;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public List<QuantityDiscountLevel> getQuantityDiscountLevel() {
        return quantityDiscountLevel;
    }

    public void setQuantityDiscountLevel(List<QuantityDiscountLevel> quantityDiscountLevel) {
        this.quantityDiscountLevel = quantityDiscountLevel;
    }

    public List<SalesDiscountLevel> getSaleDiscountLevel() {
        return saleDiscountLevel;
    }

    public void setSaleDiscountLevel(List<SalesDiscountLevel> saleDiscountLevel) {
        this.saleDiscountLevel = saleDiscountLevel;
    }

    // @OneToMany(mappedBy = "chinhSachChietKhau")
    // private List<BacChietKhauSoLuong> bacChietKhauSoLuongs = new ArrayList<>();

    // @OneToMany(mappedBy = "chinhSachChietKhau")
    // private List<BacChietKhauDoanhSo> bacChietKhauDoanhSos = new ArrayList<>();

    

}
