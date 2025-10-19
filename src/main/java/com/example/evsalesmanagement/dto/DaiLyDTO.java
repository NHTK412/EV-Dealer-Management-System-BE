package com.example.evsalesmanagement.dto;

import com.example.evsalesmanagement.model.DaiLy;

public class DaiLyDTO {
    private Integer maDaiLy;

    private String tenDaiLy;

    private String diaChi;

    private String soDienThoai;

    private String email;

    private String trangThai;

    public DaiLyDTO(DaiLy daiLy) {
        this.maDaiLy = daiLy.getMaDaiLy();
        this.tenDaiLy = daiLy.getTenDaiLy();
        this.diaChi = daiLy.getDiaChi();
        this.soDienThoai = daiLy.getSoDienThoai();
        this.email = daiLy.getEmail();
        this.trangThai = daiLy.getTrangThai();
    }

    public DaiLyDTO(Integer maDaiLy, String tenDaiLy, String diaChi, String soDienThoai, String email,
            String trangThai) {
        this.maDaiLy = maDaiLy;
        this.tenDaiLy = tenDaiLy;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.trangThai = trangThai;
    }

    public DaiLyDTO() {
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
