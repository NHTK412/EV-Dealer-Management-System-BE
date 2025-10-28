package com.example.evsalesmanagement.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//ChinhSachChietKhau = Policy
@Entity
@Table(name = "DiscountPolicy")
public class Policy extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PolicyId")
    private Integer policyId;

    @Column(name = "PolicyType")
    private String PolicyType;

    @Column(name = "PolicyValue")
    private BigDecimal policyValue;

    //condition = dieu kien ap dung
    @Column(name = "Condition")
    private String condition;

    @Column(name = "StartDate")
    private LocalDateTime startDate;

    @Column(name = "EndDate")
    private LocalDateTime endDate;

    @Column(name = "Status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "AgencyId")
    private Agency agency;

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public String getPolicyType() {
        return PolicyType;
    }

    public void setPolicyType(String policyType) {
        PolicyType = policyType;
    }

    public BigDecimal getPolicyValue() {
        return policyValue;
    }

    public void setPolicyValue(BigDecimal policyValue) {
        this.policyValue = policyValue;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    

    // @OneToMany(mappedBy = "chinhSachChietKhau")
    // private List<BacChietKhauSoLuong> bacChietKhauSoLuongs = new ArrayList<>();

    // @OneToMany(mappedBy = "chinhSachChietKhau")
    // private List<BacChietKhauDoanhSo> bacChietKhauDoanhSos = new ArrayList<>();

   
}
