package com.example.evsalesmanagement.dto.order;

import java.util.List;

import com.example.evsalesmanagement.dto.orderdetail.OrderDetailRequestDTO;

public class OrderRequestDTO {

    private String notes;

    private Integer agencyId;

    private List<OrderDetailRequestDTO> orderDetails;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

 

    public List<OrderDetailRequestDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailRequestDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    

}
