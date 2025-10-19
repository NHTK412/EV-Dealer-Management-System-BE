package com.example.evsalesmanagement.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Xe")
public class Xe extends GhiNhanThoiGian{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaXe")
    private Integer maXe;

    @Column(name = "SoKhung", unique = true)
    private String soKhung;

    @Column(name = "SoMay", unique = true)
    private String soMay;

    @Column(name = "TrangThai")
    private String trangThai;

    @Column(name = "TinhTrangXe")
    private String tinhTrangXe;

    @ManyToOne
    @JoinColumn(name = "MaChiTietLoaiXe")
    private ChiTietLoaiXe chiTietLoaiXe;

    @ManyToOne
    @JoinColumn(name = "MaDaiLy")
    private DaiLy daiLy;

    // @OneToMany(mappedBy = "xe")
    // private List<LichHenLaiThu> lichHenLaiThus = new ArrayList<>();

    // @OneToMany(mappedBy = "xe")
    // private List<ChiTietPhieuXuat> chiTietPhieuXuats = new ArrayList<>();

    // @OneToMany(mappedBy = "xe")
    // private List<ChiTietPhieuNhap> chiTietPhieuNhaps = new ArrayList<>();

    public Integer getMaXe() {
        return maXe;
    }

    public void setMaXe(Integer maXe) {
        this.maXe = maXe;
    }

    public String getSoKhung() {
        return soKhung;
    }

    public void setSoKhung(String soKhung) {
        this.soKhung = soKhung;
    }

    public String getSoMay() {
        return soMay;
    }

    public void setSoMay(String soMay) {
        this.soMay = soMay;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTinhTrangXe() {
        return tinhTrangXe;
    }

    public void setTinhTrangXe(String tinhTrangXe) {
        this.tinhTrangXe = tinhTrangXe;
    }

    public ChiTietLoaiXe getChiTietLoaiXe() {
        return chiTietLoaiXe;
    }

    public void setChiTietLoaiXe(ChiTietLoaiXe chiTietLoaiXe) {
        this.chiTietLoaiXe = chiTietLoaiXe;
    }

    public DaiLy getDaiLy() {
        return daiLy;
    }

    public void setDaiLy(DaiLy daiLy) {
        this.daiLy = daiLy;
    }
}