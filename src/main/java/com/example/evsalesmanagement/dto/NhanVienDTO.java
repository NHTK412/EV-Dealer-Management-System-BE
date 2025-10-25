package com.example.evsalesmanagement.dto;

import java.time.LocalDate;

import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.model.NhanVien;


public class NhanVienDTO {
    private Integer maNhanVien;

    private String tenNhanVien;

    private String soDienThoai;

    private String email;

    private String chucVu;

    private Integer maDaiLy;

    private String tenDaiLy;

    public NhanVienDTO(NhanVien nhanVien) {
        this.maNhanVien = nhanVien.getMaNhanVien();
        this.tenNhanVien = nhanVien.getTenNhanVien();
        this.soDienThoai = nhanVien.getSoDienThoai();
        this.email = nhanVien.getEmail();
        this.chucVu = nhanVien.getChucVu();
        this.maDaiLy = nhanVien.getDaiLy().getAgencyId();
        this.tenDaiLy = nhanVien.getDaiLy().getAgencyName();
    }

    public NhanVienDTO(Integer maNhanVien, String tenNhanVien, String gioiTinh, LocalDate ngaySinh, String soDienThoai,
            String email, String diaChi, String chucVu, Agency daiLy) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.chucVu = chucVu;
    }

    public NhanVienDTO() {
    }

    public Integer getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(Integer maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
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

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
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

}
