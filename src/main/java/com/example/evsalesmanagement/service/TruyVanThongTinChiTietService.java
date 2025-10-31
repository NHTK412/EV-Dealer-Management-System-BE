package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.dto.TruyVanThongTinXeChiTiet;
import com.example.evsalesmanagement.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TruyVanThongTinChiTietService {

    @Autowired
    private VehicleTypeRepository repository;

    /**
     * Lấy thông tin chi tiết của tất cả các loại xe
     * @return Danh sách thông tin chi tiết xe
     */
    public List<TruyVanThongTinXeChiTiet> layThongTinChiTietXe() {
        List<Object[]> results = repository.layThongTinChiTietXe();
        List<TruyVanThongTinXeChiTiet> danhSachXe = new ArrayList<>();

        for (Object[] row : results) {
            try {
                TruyVanThongTinXeChiTiet dto = new TruyVanThongTinXeChiTiet();
                
                // ma_loai_xe - index 0
                if (row[0] != null) {
                    dto.setMaChiTietLoaiXe(Integer.valueOf(row[0].toString()));
                } else {
                    dto.setMaChiTietLoaiXe(0);
                }
                
                // ten_loai_xe - index 1
                dto.setTenLoaiXe(row[1] != null ? row[1].toString() : "");
                
                // hinh_anh_xe - index 2
                dto.setHinhAnhXe(row[2] != null ? row[2].toString() : "");
                
                // nam_san_xuat - index 3
                dto.setNamSanXuat(row[3] != null ? row[3].toString() : "");
                
                // gia_ban - index 4
                if (row[4] != null) {
                    if (row[4] instanceof BigDecimal) {
                        dto.setGiaBan(((BigDecimal) row[4]).doubleValue());
                    } else if (row[4] instanceof Number) {
                        dto.setGiaBan(((Number) row[4]).doubleValue());
                    } else {
                        dto.setGiaBan(Double.parseDouble(row[4].toString()));
                    }
                } else {
                    dto.setGiaBan(0.0);
                }
                
                // mo_ta - index 5
                dto.setMoTa(row[5] != null ? row[5].toString() : "");
                
                // cau_hinh - index 6
                dto.setCauHinh(row[6] != null ? row[6].toString() : "");
                
                // mau_sac - index 7
                dto.setMauSac(row[7] != null ? row[7].toString() : "");
                
                // phien_ban - index 8
                dto.setPhienBan(row[8] != null ? row[8].toString() : "");
                
                // mo_ta_chi_tiet - Nếu có trong query (index 9)
                // Query hiện tại không có mo_ta_chi_tiet nên set rỗng
                dto.setMoTaChiTiet("");
                
                danhSachXe.add(dto);
            } catch (Exception e) {
                System.err.println("Lỗi khi xử lý dữ liệu chi tiết xe: " + e.getMessage());
                e.printStackTrace();
                // Bỏ qua bản ghi lỗi và tiếp tục
            }
        }

        return danhSachXe;
    }
}