package com.example.evsalesmanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.evsalesmanagement.model.KhuyenMai;

public class KhuyenMaiChiTietDTO {

    private Integer maKhuyenMai;

    private String tenKhuyenMai;

    private String loaiKhuyenMai;

    private BigDecimal giaTriKhuyenMai;

    private String tieuChi;

    private BigDecimal soTienGiam;

    private BigDecimal phanTramGiam;

    private LocalDateTime ngayBatDau;

    private LocalDateTime ngayHetHan;

    private String trangThai;

    private List<ChiTietLoaiXeDTO> chiTietLoaiXes;

    private List<DaiLyDTO> daiLys;

    public KhuyenMaiChiTietDTO(KhuyenMai khuyenMai) {
        this.maKhuyenMai = khuyenMai.getMaKhuyenMai();
        this.tenKhuyenMai = khuyenMai.getTenKhuyenMai();
        this.loaiKhuyenMai = khuyenMai.getLoaiKhuyenMai();
        this.giaTriKhuyenMai = khuyenMai.getGiaTriKhuyenMai();
        this.tieuChi = khuyenMai.getTieuChi();
        this.soTienGiam = khuyenMai.getSoTienGiam();
        this.phanTramGiam = khuyenMai.getPhanTramGiam();
        this.ngayBatDau = khuyenMai.getNgayBatDau();
        this.ngayHetHan = khuyenMai.getNgayHetHan();
        this.trangThai = khuyenMai.getTrangThai();
    }

    public KhuyenMaiChiTietDTO(Integer maKhuyenMai, String tenKhuyenMai, String loaiKhuyenMai,
            BigDecimal giaTriKhuyenMai, String tieuChi, BigDecimal soTienGiam, BigDecimal phanTramGiam,
            LocalDateTime ngayBatDau, LocalDateTime ngayHetHan, String trangThai, List<ChiTietLoaiXeDTO> chiTietLoaiXes,
            List<DaiLyDTO> daiLys) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.loaiKhuyenMai = loaiKhuyenMai;
        this.giaTriKhuyenMai = giaTriKhuyenMai;
        this.tieuChi = tieuChi;
        this.soTienGiam = soTienGiam;
        this.phanTramGiam = phanTramGiam;
        this.ngayBatDau = ngayBatDau;
        this.ngayHetHan = ngayHetHan;
        this.trangThai = trangThai;
        this.chiTietLoaiXes = chiTietLoaiXes;
        this.daiLys = daiLys;
    }

    public KhuyenMaiChiTietDTO() {
    }

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

    public List<ChiTietLoaiXeDTO> getChiTietLoaiXes() {
        return chiTietLoaiXes;
    }

    public void setChiTietLoaiXes(List<ChiTietLoaiXeDTO> chiTietLoaiXes) {
        this.chiTietLoaiXes = chiTietLoaiXes;
    }

    public List<DaiLyDTO> getDaiLys() {
        return daiLys;
    }

    public void setDaiLys(List<DaiLyDTO> daiLys) {
        this.daiLys = daiLys;
    }

}
