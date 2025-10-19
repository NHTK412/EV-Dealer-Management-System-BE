package com.example.evsalesmanagement.dto;


public class ChiTietYeuCauRequestDTO {

    private Integer maChiTietLoaiXe;
    private Integer soLuong;

    public Integer getMaChiTietLoaiXe() {
        return maChiTietLoaiXe;
    }

    public void setMaChiTietLoaiXe(Integer maChiTietLoaiXe) {
        this.maChiTietLoaiXe = maChiTietLoaiXe;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public ChiTietYeuCauRequestDTO() {
    }

    public ChiTietYeuCauRequestDTO(Integer maChiTietLoaiXe, Integer soLuong) {
        this.maChiTietLoaiXe = maChiTietLoaiXe;
        this.soLuong = soLuong;
    }


}
