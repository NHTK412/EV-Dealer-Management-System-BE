package com.example.evsalesmanagement.dto;
import java.time.LocalDate;
import com.example.evsalesmanagement.model.KhachHang;
import com.example.evsalesmanagement.model.Xe;
public class LichHenLaiThu {
    private LocalDate ngayHen;
    private LocalDate gioHen;
    private KhachHang khachHang;
    private Xe xe;
    private String trangThai;

    public LichHenLaiThu() {}
    public LocalDate getNgayHen() {
        return ngayHen;
    }   
    public LocalDate setNgayHen(LocalDate ngayHen) {
        return this.ngayHen = ngayHen;
    }
    public LocalDate getGioHen() {
        return gioHen;
    }
    public LocalDate setGioHen(LocalDate gioHen) {
        return this.gioHen = gioHen;
    }
    public KhachHang getKhachHang() {
        return khachHang;   
    }
    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }
    public Xe getXe() {
        return xe;
    }
    public void setXe(Xe xe) {
        this.xe = xe;
    }
    public String getTrangThai(){
        return trangThai;
    }
    public void setTrangThai(String trangThai){
         this.trangThai = trangThai;
    }
    @Override
    public String toString() {
        return "LichHenLaiThuDTO [ngayHen=" + ngayHen + ", gioHen=" + gioHen + ", khachHang=" + khachHang + ", xe=" + xe
                + ", trangThai=" + trangThai + "]";
}
}
