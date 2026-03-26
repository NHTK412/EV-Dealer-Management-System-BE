package com.example.evsalesmanagement.dto.salesreport;

import java.math.BigDecimal;

import com.example.evsalesmanagement.model.MonthlySales;

public class AgencySalesDTO {

    private Integer agencyId;
    private String agencyName;
    private String address;
    private String phoneNumber;
    private String email;
    private BigDecimal salesAmount;
    private Integer year;
    private Integer month;

    public AgencySalesDTO() {
    }

    public AgencySalesDTO(MonthlySales monthlySales) {
        if (monthlySales.getAgency() != null) {
            this.agencyId = monthlySales.getAgency().getAgencyId();
            this.agencyName = monthlySales.getAgency().getAgencyName();
            this.address = monthlySales.getAgency().getAddress();
            this.phoneNumber = monthlySales.getAgency().getPhoneNumber();
            this.email = monthlySales.getAgency().getEmail();
        }
        this.salesAmount = monthlySales.getSalesAmount();
        if (monthlySales.getSalesMonth() != null) {
            this.year = monthlySales.getSalesMonth().getYear();
            this.month = monthlySales.getSalesMonth().getMonthValue();
        }
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(BigDecimal salesAmount) {
        this.salesAmount = salesAmount;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

}
