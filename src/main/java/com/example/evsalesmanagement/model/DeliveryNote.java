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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DeliveryNote")
public class DeliveryNote extends GhiNhanThoiGian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DeliveryNoteId")
    private Integer deliveryNoteId;

    @Column(name = "DeliveryDate")
    private LocalDateTime deliveryDate;

    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    @Column(name = "Note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "EmployeeId")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "OrderId", unique = true)
    private Order order;

    // @OneToMany(mappedBy = "deliveryNote")
    // private List<DeliveryDetail> deliveryDetails = new ArrayList<>();

    public Integer getDeliveryNoteId() {
        return deliveryNoteId;
    }

    public void setDeliveryNoteId(Integer deliveryNoteId) {
        this.deliveryNoteId = deliveryNoteId;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
