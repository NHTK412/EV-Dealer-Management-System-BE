package com.example.evsalesmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// ==================== REQUEST DTOs ====================

/**
 * DTO để xử lý phản hồi
 */
public class XuLyPhanHoiRequest {

    // @NotNull(message = "Mã phản hồi không được để trống")
    // @Positive(message = "Mã phản hồi phải là số dương")
    // private Integer maPhanHoi;

    @NotBlank(message = "Nội dung xử lý không được để trống")
    @Size(min = 10, max = 2000, message = "Nội dung xử lý phải từ 10-2000 ký tự")
    private String noiDungXuLy;

    @NotBlank(message = "Hình thức giải quyết không được để trống")
    private String hinhThucGiaiQuyet; // "Điện thoại" hoặc "Email"

    
    public XuLyPhanHoiRequest() {}

    // public Integer getMaPhanHoi() {
    //     return maPhanHoi;
    // }

    // public void setMaPhanHoi(Integer maPhanHoi) {
    //     this.maPhanHoi = maPhanHoi;
    // }

    public String getNoiDungXuLy() {
        return noiDungXuLy;
    }

    public void setNoiDungXuLy(String noiDungXuLy) {
        this.noiDungXuLy = noiDungXuLy;
    }

    public String getHinhThucGiaiQuyet() {
        return hinhThucGiaiQuyet;
    }

    public void setHinhThucGiaiQuyet(String hinhThucGiaiQuyet) {
        this.hinhThucGiaiQuyet = hinhThucGiaiQuyet;
    }
}