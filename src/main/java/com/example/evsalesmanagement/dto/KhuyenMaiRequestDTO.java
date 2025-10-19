package com.example.evsalesmanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class KhuyenMaiRequestDTO {

    private String tenKhuyenMai;

    private String loaiKhuyenMai;

    private BigDecimal giaTriKhuyenMai;

    private String tieuChi;

    private BigDecimal soTienGiam;

    private BigDecimal phanTramGiam;

    private LocalDateTime ngayBatDau;

    private LocalDateTime ngayHetHan;

    private String trangThai;

    private List<Integer> maChiTietLoaiXes;

    private List<Integer> maDaiLys;

    public KhuyenMaiRequestDTO() {
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

    public List<Integer> getMaChiTietLoaiXes() {
        return maChiTietLoaiXes;
    }

    public void setMaChiTietLoaiXes(List<Integer> maChiTietLoaiXes) {
        this.maChiTietLoaiXes = maChiTietLoaiXes;
    }

    public List<Integer> getMaDaiLys() {
        return maDaiLys;
    }

    public void setMaDaiLys(List<Integer> maDaiLys) {
        this.maDaiLys = maDaiLys;
    }

}
