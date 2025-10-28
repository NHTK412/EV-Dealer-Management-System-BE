package com.example.evsalesmanagement.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * DTO response chi tiết phản hồi 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeedbackDetailResponse {

    // Thông tin phản hồi
    // private Integer maPhanHoi;
    private String tieuDePhanHoi;
    private String noiDungPhanHoi;
    private String trangThai;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ngayTao;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ngayCapNhat;
    
    // Thông tin khách hàng
    // private Integer maKhachHang;
    private String tenKhachHang;
    private String emailKhachHang;
    private String soDienThoaiKhachHang;
    
    // Thông tin xử lý 
    private XuLyInfo xuLyInfo;

    public static class XuLyInfo {
        // private Integer maXuLy;
        private String noiDungXuLy;
        private String hinhThucGiaiQuyet;
        
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime ngayXuLy;
        
        private Integer maNhanVien;
        private String tenNhanVien;
        private String emailNhanVien;

        // Getters and Setters
        // public Integer getMaXuLy() { return maXuLy; }
        // public void setMaXuLy(Integer maXuLy) { this.maXuLy = maXuLy; }
        public String getNoiDungXuLy() { return noiDungXuLy; }
        public void setNoiDungXuLy(String noiDungXuLy) { this.noiDungXuLy = noiDungXuLy; }
        public String getHinhThucGiaiQuyet() { return hinhThucGiaiQuyet; }
        public void setHinhThucGiaiQuyet(String hinhThucGiaiQuyet) { this.hinhThucGiaiQuyet = hinhThucGiaiQuyet; }
        public LocalDateTime getNgayXuLy() { return ngayXuLy; }
        public void setNgayXuLy(LocalDateTime ngayXuLy) { this.ngayXuLy = ngayXuLy; }
        public Integer getMaNhanVien() { return maNhanVien; }
        public void setMaNhanVien(Integer maNhanVien) { this.maNhanVien = maNhanVien; }
        public String getTenNhanVien() { return tenNhanVien; }
        public void setTenNhanVien(String tenNhanVien) { this.tenNhanVien = tenNhanVien; }
        public String getEmailNhanVien() { return emailNhanVien; }
        public void setEmailNhanVien(String emailNhanVien) { this.emailNhanVien = emailNhanVien; }
    }

    public FeedbackDetailResponse() {}

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private FeedbackDetailResponse response = new FeedbackDetailResponse();

        // public Builder maPhanHoi(Integer maPhanHoi) {
        //     response.maPhanHoi = maPhanHoi;
        //     return this;
        // }

        public Builder tieuDePhanHoi(String tieuDePhanHoi) {
            response.tieuDePhanHoi = tieuDePhanHoi;
            return this;
        }

        public Builder noiDungPhanHoi(String noiDungPhanHoi) {
            response.noiDungPhanHoi = noiDungPhanHoi;
            return this;
        }

        public Builder trangThai(String trangThai) {
            response.trangThai = trangThai;
            return this;
        }

        public Builder ngayTao(LocalDateTime ngayTao) {
            response.ngayTao = ngayTao;
            return this;
        }

        public Builder ngayCapNhat(LocalDateTime ngayCapNhat) {
            response.ngayCapNhat = ngayCapNhat;
            return this;
        }

        // public Builder maKhachHang(Integer maKhachHang) {
        //     response.maKhachHang = maKhachHang;
        //     return this;
        // }

        public Builder tenKhachHang(String tenKhachHang) {
            response.tenKhachHang = tenKhachHang;
            return this;
        }

        public Builder emailKhachHang(String emailKhachHang) {
            response.emailKhachHang = emailKhachHang;
            return this;
        }

        public Builder soDienThoaiKhachHang(String soDienThoaiKhachHang) {
            response.soDienThoaiKhachHang = soDienThoaiKhachHang;
            return this;
        }

        public Builder xuLyInfo(XuLyInfo xuLyInfo) {
            response.xuLyInfo = xuLyInfo;
            return this;
        }

        public FeedbackDetailResponse build() {
            return response;
        }
    }

    // Getters
    // public Integer getMaPhanHoi() { return maPhanHoi; }
    public String getTieuDePhanHoi() { return tieuDePhanHoi; }
    public String getNoiDungPhanHoi() { return noiDungPhanHoi; }
    public String getTrangThai() { return trangThai; }
    public LocalDateTime getNgayTao() { return ngayTao; }
    public LocalDateTime getNgayCapNhat() { return ngayCapNhat; }
    // public Integer getMaKhachHang() { return maKhachHang; }
    public String getTenKhachHang() { return tenKhachHang; }
    public String getEmailKhachHang() { return emailKhachHang; }
    public String getSoDienThoaiKhachHang() { return soDienThoaiKhachHang; }
    public XuLyInfo getXuLyInfo() { return xuLyInfo; }
}