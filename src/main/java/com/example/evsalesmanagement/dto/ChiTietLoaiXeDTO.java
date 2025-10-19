package com.example.evsalesmanagement.dto;

import java.math.BigDecimal;

import com.example.evsalesmanagement.model.ChiTietLoaiXe;

public class ChiTietLoaiXeDTO {
    private Integer maChiTietLoaiXe;

    private String hinhAnhXe;

    private String cauHinh;

    private String mauSac;

    private String phienBan;

    private String tinhNang;

    private BigDecimal giaBan;

    public ChiTietLoaiXeDTO(ChiTietLoaiXe chiTietLoaiXe) {
        this.maChiTietLoaiXe = chiTietLoaiXe.getMaChiTietLoaiXe();
        this.hinhAnhXe = chiTietLoaiXe.getHinhAnhXe();
        this.cauHinh = chiTietLoaiXe.getCauHinh();
        this.mauSac = chiTietLoaiXe.getMauSac();
        this.phienBan = chiTietLoaiXe.getPhienBan();
        this.tinhNang = chiTietLoaiXe.getTinhNang();
        this.giaBan = chiTietLoaiXe.getGiaBan();
    }

    public ChiTietLoaiXeDTO(Integer maChiTietLoaiXe, String hinhAnhXe, String cauHinh, String mauSac, String phienBan,
            String tinhNang, BigDecimal giaBan) {
        this.maChiTietLoaiXe = maChiTietLoaiXe;
        this.hinhAnhXe = hinhAnhXe;
        this.cauHinh = cauHinh;
        this.mauSac = mauSac;
        this.phienBan = phienBan;
        this.tinhNang = tinhNang;
        this.giaBan = giaBan;
    }

    public ChiTietLoaiXeDTO() {
    }

    public Integer getMaChiTietLoaiXe() {
        return maChiTietLoaiXe;
    }

    public void setMaChiTietLoaiXe(Integer maChiTietLoaiXe) {
        this.maChiTietLoaiXe = maChiTietLoaiXe;
    }

    public String getHinhAnhXe() {
        return hinhAnhXe;
    }

    public void setHinhAnhXe(String hinhAnhXe) {
        this.hinhAnhXe = hinhAnhXe;
    }

    public String getCauHinh() {
        return cauHinh;
    }

    public void setCauHinh(String cauHinh) {
        this.cauHinh = cauHinh;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getPhienBan() {
        return phienBan;
    }

    public void setPhienBan(String phienBan) {
        this.phienBan = phienBan;
    }

    public String getTinhNang() {
        return tinhNang;
    }

    public void setTinhNang(String tinhNang) {
        this.tinhNang = tinhNang;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }
}
