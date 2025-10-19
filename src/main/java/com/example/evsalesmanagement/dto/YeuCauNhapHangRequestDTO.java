package com.example.evsalesmanagement.dto;

import java.util.List;

public class YeuCauNhapHangRequestDTO {

    private String trangThai;

    private String ghiChu;

    private Integer maNhanVien;

    private List<ChiTietYeuCauRequestDTO> chiTietYeuCaus;

    public YeuCauNhapHangRequestDTO() {
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Integer getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(Integer maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public List<ChiTietYeuCauRequestDTO> getChiTietYeuCaus() {
        return chiTietYeuCaus;
    }

    public void setChiTietYeuCaus(List<ChiTietYeuCauRequestDTO> chiTietYeuCaus) {
        this.chiTietYeuCaus = chiTietYeuCaus;
    }

}
