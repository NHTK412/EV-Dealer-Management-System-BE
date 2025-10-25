package com.example.evsalesmanagement.dto;

import com.example.evsalesmanagement.model.Agency;

public class AgencyDTO {
    private Integer maDaiLy;

    private String tenDaiLy;

    private String diaChi;

    private String soDienThoai;

    private String email;

    private String trangThai;

    public AgencyDTO(Agency agency) {
        this.maDaiLy = agency.getAgencyId();
        this.tenDaiLy = agency.getAgencyName();
        this.diaChi = agency.getAddress();
        this.soDienThoai = agency.getPhoneNumber();
        this.email = agency.getEmail();
        this.trangThai = agency.getStatus();
    }

    public AgencyDTO(Integer maDaiLy, String tenDaiLy, String diaChi, String soDienThoai, String email,
            String trangThai) {
        this.maDaiLy = maDaiLy;
        this.tenDaiLy = tenDaiLy;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.trangThai = trangThai;
    }

    public AgencyDTO() {
    }

    public Integer getMaDaiLy() {
        return maDaiLy;
    }

    public void setMaDaiLy(Integer maDaiLy) {
        this.maDaiLy = maDaiLy;
    }

    public String getTenDaiLy() {
        return tenDaiLy;
    }

    public void setTenDaiLy(String tenDaiLy) {
        this.tenDaiLy = tenDaiLy;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

}
