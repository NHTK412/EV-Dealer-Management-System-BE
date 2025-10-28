package com.example.evsalesmanagement.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//BaoGia = Quote
@Entity
@Table(name = "Quote")
public class Quote extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuoteId")
    private Integer quoteId;

    // TongTien = totalAmount
    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    // @Column(name = "NgayTaoPhieu")
    // private LocalDateTime ngayTaoPhieu;

    @Column(name = "Status")
    private String status;

    // @OneToOne
    // @JoinColumn(name = "OrderId", unique = true)
    // private Order order;

    // Mới thêm quan hệ để biết ai tạo báo giá này
    @ManyToOne
    @JoinColumn(name = "EmployeeId")
    private Employee employee;

    @OneToMany(mappedBy = "quote")
    private List<QuotationDetail> quotationDetails = new ArrayList<>();

    public Integer getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // public Order getOrder() {
    // return order;
    // }

    // public void setOder(Order order) {
    // this.order = order;
    // }

    // @OneToMany(mappedBy = "baoGia")
    // private List<ChiTietBaoGia> chiTietBaoGias = new ArrayList<>();

    // public LocalDateTime getNgayTaoPhieu() {
    // return ngayTaoPhieu;
    // }

    // public void setNgayTaoPhieu(LocalDateTime ngayTaoPhieu) {
    // this.ngayTaoPhieu = ngayTaoPhieu;
    // }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<QuotationDetail> getQuotationDetails() {
        return quotationDetails;
    }

    public void setQuotationDetails(List<QuotationDetail> quotationDetails) {
        this.quotationDetails = quotationDetails;
    }

}