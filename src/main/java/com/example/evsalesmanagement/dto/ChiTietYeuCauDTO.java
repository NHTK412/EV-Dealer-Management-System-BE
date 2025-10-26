package com.example.evsalesmanagement.dto;

import com.example.evsalesmanagement.model.ImportRequestDetail;

public class ChiTietYeuCauDTO {
    private Integer maChiTietLoaiXe;

    private String tenLoaiXe;

    private String cauHinh;

    private String mauSac;

    private String phienBan;

    private String tinhNang;

    public ChiTietYeuCauDTO(ImportRequestDetail chiTietYeuCau) {
        this.maChiTietLoaiXe = chiTietYeuCau.getMaChiTietLoaiXe();
        this.tenLoaiXe = chiTietYeuCau.getChiTietLoaiXe().getLoaiXe().getTenLoaiXe();
        this.cauHinh = chiTietYeuCau.getChiTietLoaiXe().getCauHinh();
        this.mauSac = chiTietYeuCau.getChiTietLoaiXe().getMauSac();
        this.phienBan = chiTietYeuCau.getChiTietLoaiXe().getPhienBan();
        this.tinhNang = chiTietYeuCau.getChiTietLoaiXe().getTinhNang();
    }

    public ChiTietYeuCauDTO(Integer maChiTietLoaiXe, String tenLoaiXe, String cauHinh, String mauSac, String phienBan,
            String tinhNang) {
        this.maChiTietLoaiXe = maChiTietLoaiXe;
        this.tenLoaiXe = tenLoaiXe;
        this.cauHinh = cauHinh;
        this.mauSac = mauSac;
        this.phienBan = phienBan;
        this.tinhNang = tinhNang;
    }

    public ChiTietYeuCauDTO() {
    }

    public Integer getMaChiTietLoaiXe() {
        return maChiTietLoaiXe;
    }

    public void setMaChiTietLoaiXe(Integer maChiTietLoaiXe) {
        this.maChiTietLoaiXe = maChiTietLoaiXe;
    }

    public String getTenLoaiXe() {
        return tenLoaiXe;
    }

    public void setTenLoaiXe(String tenLoaiXe) {
        this.tenLoaiXe = tenLoaiXe;
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

}
