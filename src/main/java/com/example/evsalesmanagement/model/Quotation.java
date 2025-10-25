package com.example.evsalesmanagement.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Quotation")
public class Quotation extends GhiNhanThoiGian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuotationId")
    private Integer quotationId;

    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    // @Column(name = "NgayTaoPhieu")
    // private LocalDateTime ngayTaoPhieu;

    @Column(name = "Status")
    private String status;

    @OneToOne
    @JoinColumn(name = "OrderId", unique = true)
    private Order order;

    // @OneToMany(mappedBy = "baoGia")
    // private List<ChiTietBaoGia> chiTietBaoGias = new ArrayList<>();

    public Integer getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(Integer quotationId) {
        this.quotationId = quotationId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    // public LocalDateTime getNgayTaoPhieu() {
    //     return ngayTaoPhieu;
    // }

    // public void setNgayTaoPhieu(LocalDateTime ngayTaoPhieu) {
    //     this.ngayTaoPhieu = ngayTaoPhieu;
    // }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
