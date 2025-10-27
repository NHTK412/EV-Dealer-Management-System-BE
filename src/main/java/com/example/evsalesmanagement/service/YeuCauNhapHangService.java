package com.example.evsalesmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.ChiTietYeuCauDTO;
import com.example.evsalesmanagement.dto.ChiTietYeuCauRequestDTO;
import com.example.evsalesmanagement.dto.YeuCauNhapHangDTO;
import com.example.evsalesmanagement.dto.YeuCauNhapHangRequestDTO;
import com.example.evsalesmanagement.exception.ConflictException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.ChiTietLoaiXe;
import com.example.evsalesmanagement.model.ImportRequestDetail;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.model.ImportRequest;
import com.example.evsalesmanagement.repository.ChiTietLoaiXeRepository;
import com.example.evsalesmanagement.repository.ImportRequestDetailRepository;
import com.example.evsalesmanagement.repository.EmployeeRepository;
import com.example.evsalesmanagement.repository.YeuCauNhapHangReponsitory;

import jakarta.transaction.Transactional;

@Service
public class YeuCauNhapHangService {

    @Autowired
    YeuCauNhapHangReponsitory yeuCauNhapHangReponsitory;

    @Autowired
    ImportRequestDetailRepository chiTietYeuCauRepository;

    @Autowired
    EmployeeRepository nhanVienRepository;

    @Autowired
    ChiTietLoaiXeRepository chiTietLoaiXeRepository;

    @Transactional
    public YeuCauNhapHangDTO taoYeuCauNhapHang(YeuCauNhapHangRequestDTO yeuCauNhapHangRequestDTO) {

        ImportRequest yeuCauNhapHang = new ImportRequest();
        yeuCauNhapHang.setTrangThai("Đã Yêu Cầu");
        yeuCauNhapHang.setGhiChu(yeuCauNhapHangRequestDTO.getGhiChu());

        Employee nhanVien = nhanVienRepository.findById(yeuCauNhapHangRequestDTO.getMaNhanVien())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));

        yeuCauNhapHang.setNhanVien(nhanVien);
        yeuCauNhapHang = yeuCauNhapHangReponsitory.save(yeuCauNhapHang);

        YeuCauNhapHangDTO yeuCauNhapHangDTO = new YeuCauNhapHangDTO(yeuCauNhapHang);

        List<ImportRequestDetail> chiTietYeuCau = new ArrayList<>();

        for (ChiTietYeuCauRequestDTO chiTietYeuCauDTO : yeuCauNhapHangRequestDTO.getChiTietYeuCaus()) {
            ChiTietLoaiXe chiTietLoaiXe = chiTietLoaiXeRepository.findById(chiTietYeuCauDTO.getMaChiTietLoaiXe())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiết loại xe"));

            ImportRequestDetail ctyc = new ImportRequestDetail();

            ctyc.setMaChiTietLoaiXe(chiTietLoaiXe.getMaChiTietLoaiXe());
            ctyc.setChiTietLoaiXe(chiTietLoaiXe);

            ctyc.setSoLuong(chiTietYeuCauDTO.getSoLuong());
            ctyc.setMaYeuCau(yeuCauNhapHang.getMaYeuCau());
            ctyc.setYeuCauNhapHang(yeuCauNhapHang);

            chiTietYeuCauRepository.save(ctyc);
            chiTietYeuCau.add(ctyc);

            yeuCauNhapHangDTO.getChiTietYeuCaus().add(new ChiTietYeuCauDTO(ctyc));

        }

        return yeuCauNhapHangDTO;

        // YeuCauNhapHang yeuCauNhapHang = new YeuCauNhapHang();
        // yeuCauNhapHang.setTrangThai("Đã Yêu Cầu");
        // yeuCauNhapHang.setGhiChu(yeuCauNhapHangRequestDTO.getGhiChu());

        // NhanVien nhanVien =
        // nhanVienRepository.findById(yeuCauNhapHangRequestDTO.getMaNhanVien())
        // .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));

        // yeuCauNhapHang.setNhanVien(nhanVien);
        // yeuCauNhapHang = yeuCauNhapHangReponsitory.save(yeuCauNhapHang);
        // List<ChiTietYeuCau> chiTietYeuCau = new ArrayList<>();

        // for (ChiTietYeuCauRequestDTO chiTietYeuCauDTO :
        // yeuCauNhapHangRequestDTO.getChiTietYeuCaus()) {
        // ChiTietLoaiXe chiTietLoaiXe =
        // chiTietLoaiXeRepository.findById(chiTietYeuCauDTO.getMaChiTietLoaiXe()).orElseThrow(()
        // -> new RuntimeException("Không tìm thấy chi tiết loại xe"));

        // ChiTietYeuCau ctyc = new ChiTietYeuCau();

        // ctyc.setMaChiTietLoaiXe(chiTietLoaiXe.getMaChiTietLoaiXe());
        // ctyc.setChiTietLoaiXe(chiTietLoaiXe);

        // ctyc.setSoLuong(chiTietYeuCauDTO.getSoLuong());
        // ctyc.setMaYeuCau(yeuCauNhapHang.getMaYeuCau());
        // ctyc.setYeuCauNhapHang(yeuCauNhapHang);

        // chiTietYeuCauRepository.save(ctyc);
        // chiTietYeuCau.add(ctyc);

        // }
        // return yeuCauNhapHang;
    }

    @Transactional
    public YeuCauNhapHangDTO xoaYeuCauNhapHang(Integer maYeuCauNhapHang) {

        YeuCauNhapHangDTO yeuCauNhapHangDTO = new YeuCauNhapHangDTO(yeuCauNhapHangReponsitory.findById(maYeuCauNhapHang)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên")));

        yeuCauNhapHangDTO.setChiTietYeuCaus(
                chiTietYeuCauRepository.findByYeuCauNhapHang_MaYeuCau(maYeuCauNhapHang).stream().map((ctyc) -> {
                    return new ChiTietYeuCauDTO(ctyc);
                }).toList());

        chiTietYeuCauRepository.deleteByYeuCauNhapHang_MaYeuCau(maYeuCauNhapHang);

        yeuCauNhapHangReponsitory.deleteById(maYeuCauNhapHang);

        return yeuCauNhapHangDTO;

    }

    @Transactional
    public YeuCauNhapHangDTO chinhSuaYeuCauNhapHang(Integer maYeuCauNhapHang,
            YeuCauNhapHangRequestDTO yeuCauNhapHangRequestDTO) {
        ImportRequest yeuCauNhapHang = yeuCauNhapHangReponsitory.findById(maYeuCauNhapHang)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy yêu cầu"));

        if (!yeuCauNhapHang.getTrangThai().equals("Chờ duyệt")) {
            throw new ConflictException("Yêu cầu hiện tại không thể chỉnh sửa");
        }

        YeuCauNhapHangDTO yeuCauNhapHangDTO = new YeuCauNhapHangDTO(yeuCauNhapHang);

        yeuCauNhapHang.setGhiChu(yeuCauNhapHangRequestDTO.getGhiChu());

        chiTietYeuCauRepository.deleteByYeuCauNhapHang_MaYeuCau(maYeuCauNhapHang);
        List<ImportRequestDetail> chiTietYeuCaus = new ArrayList<>();

        for (ChiTietYeuCauRequestDTO chiTietYeuCauDTO : yeuCauNhapHangRequestDTO.getChiTietYeuCaus()) {
            ChiTietLoaiXe chiTietLoaiXe = chiTietLoaiXeRepository.findById(chiTietYeuCauDTO.getMaChiTietLoaiXe())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiết loại xe"));

            ImportRequestDetail ctyc = new ImportRequestDetail();

            ctyc.setMaChiTietLoaiXe(chiTietLoaiXe.getMaChiTietLoaiXe());
            ctyc.setChiTietLoaiXe(chiTietLoaiXe);

            ctyc.setSoLuong(chiTietYeuCauDTO.getSoLuong());
            ctyc.setMaYeuCau(yeuCauNhapHang.getMaYeuCau());
            ctyc.setYeuCauNhapHang(yeuCauNhapHang);

            chiTietYeuCauRepository.save(ctyc);
            chiTietYeuCaus.add(ctyc);

            yeuCauNhapHangDTO.getChiTietYeuCaus().add(new ChiTietYeuCauDTO(ctyc));

        }
        return yeuCauNhapHangDTO;
    }

    @Transactional
    public List<YeuCauNhapHangDTO> layTatCaYeuCauNhapHang(Pageable pageable, Integer maNhanVien) {

        Page<ImportRequest> yeuCauNhapHangs = maNhanVien == null ? yeuCauNhapHangReponsitory.findAll(pageable)
                : yeuCauNhapHangReponsitory.findByNhanVien_MaNhanVien(maNhanVien, pageable);
        // System.err.println("----> " + yeuCauNhapHangs.toString());
        return yeuCauNhapHangs.map(yeuCauNhapHang -> new YeuCauNhapHangDTO(yeuCauNhapHang)).toList();
    }

    @Transactional
    public YeuCauNhapHangDTO layChiTietYeuCauNhapHang(Integer maYeuCauNhapHang) {
        ImportRequest yeuCauNhapHang = yeuCauNhapHangReponsitory.findById(maYeuCauNhapHang)
                .orElseThrow(() -> new ResourceNotFoundException("Mã yêu cầu không hợp lệ"));

        YeuCauNhapHangDTO yeuCauNhapHangDTO = new YeuCauNhapHangDTO(yeuCauNhapHang);

        List<ImportRequestDetail> chiTietYeuCaus = chiTietYeuCauRepository.findByYeuCauNhapHang_MaYeuCau(maYeuCauNhapHang);

        List<ChiTietYeuCauDTO> chiTietYeuCauDTOs = chiTietYeuCaus.stream().map((chiTietYeuCau) -> {
            return new ChiTietYeuCauDTO(chiTietYeuCau);
        }).toList();

        yeuCauNhapHangDTO.setChiTietYeuCaus(chiTietYeuCauDTOs);

        return yeuCauNhapHangDTO;
    }

}
