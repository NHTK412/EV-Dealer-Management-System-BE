package com.example.evsalesmanagement.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.evsalesmanagement.model.YeuCauNhapHang;

public class YeuCauNhapHangDTO {
    private Integer maYeuCau;
    private String trangThai;

    private String ghiChu;

    private NhanVienDTO nhanVienDTO;

    private List<ChiTietYeuCauDTO> chiTietYeuCaus;

    public YeuCauNhapHangDTO(YeuCauNhapHang yeuCauNhapHang) {
        this.maYeuCau = yeuCauNhapHang.getMaYeuCau();
        this.trangThai = yeuCauNhapHang.getTrangThai();
        this.ghiChu = yeuCauNhapHang.getGhiChu();
        this.chiTietYeuCaus = new ArrayList<>();
        this.nhanVienDTO = new NhanVienDTO(yeuCauNhapHang.getNhanVien());
    }

    public YeuCauNhapHangDTO() {
    }

    public Integer getMaYeuCau() {
        return maYeuCau;
    }

    public void setMaYeuCau(Integer maYeuCau) {
        this.maYeuCau = maYeuCau;
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

    public NhanVienDTO getNhanVienDTO() {
        return nhanVienDTO;
    }

    public void setNhanVienDTO(NhanVienDTO nhanVienDTO) {
        this.nhanVienDTO = nhanVienDTO;
    }

    public List<ChiTietYeuCauDTO> getChiTietYeuCaus() {
        return chiTietYeuCaus;
    }

    public void setChiTietYeuCaus(List<ChiTietYeuCauDTO> chiTietYeuCaus) {
        this.chiTietYeuCaus = chiTietYeuCaus;
    }

}
