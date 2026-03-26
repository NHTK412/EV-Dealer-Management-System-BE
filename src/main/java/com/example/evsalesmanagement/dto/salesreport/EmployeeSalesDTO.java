package com.example.evsalesmanagement.dto.salesreport;

import java.math.BigDecimal;

public class EmployeeSalesDTO {

    private Integer employeeId;
    private String employeeName;
    private String phoneNumber;
    private String email;
    private Integer totalOrders;
    private BigDecimal totalSales;
    private Integer year;
    private Integer month;

    public EmployeeSalesDTO() {
    }

    public EmployeeSalesDTO(Integer employeeId, String employeeName, String phoneNumber, String email,
            Long totalOrders, BigDecimal totalSales) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.totalOrders = totalOrders != null ? totalOrders.intValue() : 0;
        this.totalSales = totalSales != null ? totalSales : BigDecimal.ZERO;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public Integer getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
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
