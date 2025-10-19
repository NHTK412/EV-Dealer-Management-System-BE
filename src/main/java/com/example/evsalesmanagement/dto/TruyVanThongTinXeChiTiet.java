package com.example.evsalesmanagement.dto;

public class TruyVanThongTinXeChiTiet {
    private Integer maChiTietLoaiXe;
    private String hinhAnhXe;
    private String cauHinh;
    private String mauSac;
    private String phienBan;
    private double giaBan;
    private String tenLoaiXe;
    private String moTa;
    private String namSanXuat;
    private String moTaChiTiet;

    // Constructor không tham số
    public TruyVanThongTinXeChiTiet() {
    }

    // Constructor đầy đủ tham số
    public TruyVanThongTinXeChiTiet(Integer maChiTietLoaiXe, String hinhAnhXe, String cauHinh, 
                                     String mauSac, String phienBan, double giaBan, 
                                     String tenLoaiXe, String moTa, String namSanXuat, String moTaChiTiet) {
        this.maChiTietLoaiXe = maChiTietLoaiXe;
        this.hinhAnhXe = hinhAnhXe;
        this.cauHinh = cauHinh;
        this.mauSac = mauSac;
        this.phienBan = phienBan;
        this.giaBan = giaBan;
        this.tenLoaiXe = tenLoaiXe;
        this.moTa = moTa;
        this.namSanXuat = namSanXuat;
        this.moTaChiTiet = moTaChiTiet;
    }

    // Getters và Setters
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

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getTenLoaiXe() {
        return tenLoaiXe;
    }

    public void setTenLoaiXe(String tenLoaiXe) {
        this.tenLoaiXe = tenLoaiXe;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getNamSanXuat() {
        return namSanXuat;
    }

    public void setNamSanXuat(String namSanXuat) {
        this.namSanXuat = namSanXuat;
    }

    public String getMoTaChiTiet() {
        return moTaChiTiet;
    }

    public void setMoTaChiTiet(String moTaChiTiet) {
        this.moTaChiTiet = moTaChiTiet;
    }

    @Override
    public String toString() {
        return "TruyVanThongTinXeChiTiet{" +
                "maChiTietLoaiXe=" + maChiTietLoaiXe +
                ", hinhAnhXe='" + hinhAnhXe + '\'' +
                ", cauHinh='" + cauHinh + '\'' +
                ", mauSac='" + mauSac + '\'' +
                ", phienBan='" + phienBan + '\'' +
                ", giaBan=" + giaBan +
                ", tenLoaiXe='" + tenLoaiXe + '\'' +
                ", moTa='" + moTa + '\'' +
                ", namSanXuat='" + namSanXuat + '\'' +
                ", moTaChiTiet='" + moTaChiTiet + '\'' +
                '}';
    }
}