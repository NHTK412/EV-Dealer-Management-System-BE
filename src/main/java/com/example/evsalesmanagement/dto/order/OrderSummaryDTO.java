package com.example.evsalesmanagement.dto.order;

import java.math.BigDecimal;

import com.example.evsalesmanagement.enums.OrderStatusEnum;
import com.example.evsalesmanagement.model.Order;

public class OrderSummaryDTO {

    private Integer orderId;

    private String notes;

    private String contractNumber;


    private String employeeName;



    private BigDecimal totalAmount;

    private OrderStatusEnum status;


    private String customerName;

    
    

    public OrderSummaryDTO(Order order) {
        this.orderId = order.getOrderId();
        this.notes = order.getNotes();
        this.contractNumber = order.getContractNumber();
        this.employeeName = order.getEmployee().getEmployeeName();
        this.totalAmount = order.getTotalAmount();
        this.status = order.getStatus();
        this.customerName = (order.getCustomer() != null) ? order.getCustomer().getCustomerName() : null;
    }

    public OrderSummaryDTO() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }






    // ArrayList<>();

}
