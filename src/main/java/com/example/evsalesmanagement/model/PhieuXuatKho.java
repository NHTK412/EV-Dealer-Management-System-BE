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
@Table(name = "PhieuXuatKho")
public class PhieuXuatKho extends GhiNhanThoiGian{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaPhieuXuat")
    private Integer maPhieuXuat;

    @Column(name = "NgayXuat")
    private LocalDateTime ngayXuat;

    @Column(name = "TongTien")
    private BigDecimal tongTien;

    @Column(name = "GhiChu")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "MaNhanVien")
    private NhanVien nhanVien;

    @OneToOne
    @JoinColumn(name = "MaDonHang", unique = true)
    private DonHang donHang;

    // @OneToMany(mappedBy = "phieuXuatKho")
    // private List<ChiTietPhieuXuat> chiTietPhieuXuats = new ArrayList<>();

    public Integer getMaPhieuXuat() {
        return maPhieuXuat;
    }

    public void setMaPhieuXuat(Integer maPhieuXuat) {
        this.maPhieuXuat = maPhieuXuat;
    }

    public LocalDateTime getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(LocalDateTime ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public DonHang getDonHang() {
        return donHang;
    }

    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
    }
}