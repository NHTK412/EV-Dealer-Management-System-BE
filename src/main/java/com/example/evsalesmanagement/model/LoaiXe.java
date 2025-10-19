package com.example.evsalesmanagement.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LoaiXe")
public class LoaiXe extends GhiNhanThoiGian{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLoaiXe")
    private Integer maLoaiXe;

    @Column(name = "TenLoaiXe", nullable = false)
    private String tenLoaiXe;

    @Column(name = "NamSanXuat")
    private Integer namSanXuat;

    @Column(name = "MoTa")
    private String moTa;

    // @ManyToMany(mappedBy = "loaiXes")
    // private List<DanhMucXe> danhMucXes = new ArrayList<>();

    // @OneToMany(mappedBy = "loaiXe")
    // private List<ChiTietLoaiXe> chiTietLoaiXes = new ArrayList<>();

    public Integer getMaLoaiXe() {
        return maLoaiXe;
    }

    public void setMaLoaiXe(Integer maLoaiXe) {
        this.maLoaiXe = maLoaiXe;
    }

    public String getTenLoaiXe() {
        return tenLoaiXe;
    }

    public void setTenLoaiXe(String tenLoaiXe) {
        this.tenLoaiXe = tenLoaiXe;
    }

    public Integer getNamSanXuat() {
        return namSanXuat;
    }

    public void setNamSanXuat(Integer namSanXuat) {
        this.namSanXuat = namSanXuat;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}