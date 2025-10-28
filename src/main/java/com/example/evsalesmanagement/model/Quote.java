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


//BaoGia = Quote
@Entity
@Table(name = "Quote")
public class Quote extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuoteId")
    private Integer quoteId;

    //TongTien = totalAmount
    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    // @Column(name = "NgayTaoPhieu")
    // private LocalDateTime ngayTaoPhieu;

    @Column(name = "Status")
    private String status;

    @OneToOne
    @JoinColumn(name = "OderId", unique = true)
    private Oder oder;

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

    public Oder getOder() {
        return oder;
    }

    public void setOder(Oder oder) {
        this.oder = oder;
    }

    // @OneToMany(mappedBy = "baoGia")
    // private List<ChiTietBaoGia> chiTietBaoGias = new ArrayList<>();

    

    // public LocalDateTime getNgayTaoPhieu() {
    //     return ngayTaoPhieu;
    // }

    // public void setNgayTaoPhieu(LocalDateTime ngayTaoPhieu) {
    //     this.ngayTaoPhieu = ngayTaoPhieu;
    // }

}