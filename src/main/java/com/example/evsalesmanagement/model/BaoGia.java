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
@Table(name = "BaoGia")
public class BaoGia extends GhiNhanThoiGian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBaoGia")
    private Integer maBaoGia;

    @Column(name = "TongTien")
    private BigDecimal tongTien;

    // @Column(name = "NgayTaoPhieu")
    // private LocalDateTime ngayTaoPhieu;

    @Column(name = "TrangThai")
    private String trangThai;

    @OneToOne
    @JoinColumn(name = "MaDonHang", unique = true)
    private DonHang donHang;

    // @OneToMany(mappedBy = "baoGia")
    // private List<ChiTietBaoGia> chiTietBaoGias = new ArrayList<>();

    public Integer getMaBaoGia() {
        return maBaoGia;
    }

    public void setMaBaoGia(Integer maBaoGia) {
        this.maBaoGia = maBaoGia;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    // public LocalDateTime getNgayTaoPhieu() {
    //     return ngayTaoPhieu;
    // }

    // public void setNgayTaoPhieu(LocalDateTime ngayTaoPhieu) {
    //     this.ngayTaoPhieu = ngayTaoPhieu;
    // }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public DonHang getDonHang() {
        return donHang;
    }

    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
    }
}