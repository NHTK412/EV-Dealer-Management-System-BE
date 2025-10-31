package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.dto.VehicleTypeDTO;
import com.example.evsalesmanagement.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TruyVanThongTinXeService {

    @Autowired
    private VehicleTypeRepository repository;

    /**
     * Lấy thông tin cơ bản của tất cả các loại xe
     * @return Danh sách thông tin xe
     */
    public List<VehicleTypeDTO> layThongTinXe() {
        List<Object[]> results = repository.layThongTinXe();
        List<VehicleTypeDTO> danhSachXe = new ArrayList<>();

        for (Object[] row : results) {
            try {
                VehicleTypeDTO dto = new VehicleTypeDTO();
                
                // ma_loai_xe
                if (row[0] != null) {
                    dto.setMaChiTietLoaiXe(Integer.valueOf(row[0].toString()));
                }
                
                // ten_loai_xe
                dto.setTenLoaiXe(row[1] != null ? row[1].toString() : "");
                
                // hinh_anh_xe
                dto.setHinhAnhXe(row[2] != null ? row[2].toString() : "");
                
                // nam_san_xuat
                dto.setNamSanXuat(row[3] != null ? row[3].toString() : "");
                
                // gia_ban
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
                
                // mo_ta
                dto.setMoTa(row[5] != null ? row[5].toString() : "");
                
                danhSachXe.add(dto);
            } catch (Exception e) {
                System.err.println("Lỗi khi xử lý dữ liệu xe: " + e.getMessage());
                e.printStackTrace();
                // Bỏ qua bản ghi lỗi và tiếp tục
            }
        }

        return danhSachXe;
    }

}