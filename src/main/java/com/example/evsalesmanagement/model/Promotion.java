package com.example.evsalesmanagement.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "KhuyenMai")
public class Promotion extends GhiNhanThoiGian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaKhuyenMai")
    private Integer maKhuyenMai;

    @Column(name = "TenKhuyenMai", nullable = false)
    private String tenKhuyenMai;

    @Column(name = "LoaiKhuyenMai")
    private String loaiKhuyenMai;

    @Column(name = "GiaTriKhuyenMai")
    private BigDecimal giaTriKhuyenMai;

    @Column(name = "TieuChi")
    private String tieuChi;

    @Column(name = "SoTienGiam")
    private BigDecimal soTienGiam;

    @Column(name = "PhanTramGiam")
    private BigDecimal phanTramGiam;

    @Column(name = "NgayBatDau")
    private LocalDateTime ngayBatDau;

    @Column(name = "NgayHetHan")
    private LocalDateTime ngayHetHan;

    @Column(name = "TrangThai")
    private String trangThai;

    @ManyToMany
    @JoinTable(name = "KhuyenMai_ChiTietLoaiXe", joinColumns = @JoinColumn(name = "MaKhuyenMai"), inverseJoinColumns = @JoinColumn(name = "MaChiTietLoaiXe"))
    private List<ChiTietLoaiXe> chiTietLoaiXes = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "KhuyenMai_DaiLy", joinColumns = @JoinColumn(name = "MaKhuyenMai"), inverseJoinColumns = @JoinColumn(name = "MaDaiLy"))
    private List<Agency> daiLys = new ArrayList<>();

    public Integer getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(Integer maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public String getLoaiKhuyenMai() {
        return loaiKhuyenMai;
    }

    public void setLoaiKhuyenMai(String loaiKhuyenMai) {
        this.loaiKhuyenMai = loaiKhuyenMai;
    }

    public BigDecimal getGiaTriKhuyenMai() {
        return giaTriKhuyenMai;
    }

    public void setGiaTriKhuyenMai(BigDecimal giaTriKhuyenMai) {
        this.giaTriKhuyenMai = giaTriKhuyenMai;
    }

    public String getTieuChi() {
        return tieuChi;
    }

    public void setTieuChi(String tieuChi) {
        this.tieuChi = tieuChi;
    }

    public BigDecimal getSoTienGiam() {
        return soTienGiam;
    }

    public void setSoTienGiam(BigDecimal soTienGiam) {
        this.soTienGiam = soTienGiam;
    }

    public BigDecimal getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(BigDecimal phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public LocalDateTime getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDateTime ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDateTime getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDateTime ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public List<ChiTietLoaiXe> getChiTietLoaiXes() {
        return chiTietLoaiXes;
    }

    public void setChiTietLoaiXes(List<ChiTietLoaiXe> chiTietLoaiXes) {
        this.chiTietLoaiXes = chiTietLoaiXes;
    }

    public List<Agency> getDaiLys() {
        return daiLys;
    }

    public void setDaiLys(List<Agency> daiLys) {
        this.daiLys = daiLys;
    }

    @Override
    public String toString() {
        return "KhuyenMai [maKhuyenMai=" + maKhuyenMai + ", tenKhuyenMai=" + tenKhuyenMai + ", loaiKhuyenMai="
                + loaiKhuyenMai + ", giaTriKhuyenMai=" + giaTriKhuyenMai + ", tieuChi=" + tieuChi + ", soTienGiam="
                + soTienGiam + ", phanTramGiam=" + phanTramGiam + ", ngayBatDau=" + ngayBatDau + ", ngayHetHan="
                + ngayHetHan + ", trangThai=" + trangThai + ", chiTietLoaiXes=" + chiTietLoaiXes + ", daiLys=" + daiLys
                + "]";
    }

}