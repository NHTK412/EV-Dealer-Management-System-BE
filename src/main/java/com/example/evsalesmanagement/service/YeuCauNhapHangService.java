package com.example.evsalesmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.ChiTietYeuCauDTO;
import com.example.evsalesmanagement.dto.ChiTietYeuCauRequestDTO;
import com.example.evsalesmanagement.dto.YeuCauNhapHangDTO;
import com.example.evsalesmanagement.dto.YeuCauNhapHangRequestDTO;
import com.example.evsalesmanagement.model.ChiTietLoaiXe;
import com.example.evsalesmanagement.model.ChiTietYeuCau;
import com.example.evsalesmanagement.model.NhanVien;
import com.example.evsalesmanagement.model.YeuCauNhapHang;
import com.example.evsalesmanagement.repository.ChiTietLoaiXeRepository;
import com.example.evsalesmanagement.repository.ChiTietYeuCauRepository;
import com.example.evsalesmanagement.repository.NhanVienRepository;
import com.example.evsalesmanagement.repository.YeuCauNhapHangReponsitory;

import jakarta.transaction.Transactional;

@Service
public class YeuCauNhapHangService {

    @Autowired
    YeuCauNhapHangReponsitory yeuCauNhapHangReponsitory;

    @Autowired
    ChiTietYeuCauRepository chiTietYeuCauRepository;

    @Autowired
    NhanVienRepository nhanVienRepository;

    @Autowired
    ChiTietLoaiXeRepository chiTietLoaiXeRepository;

    @Transactional
    public YeuCauNhapHangDTO taoYeuCauNhapHang(YeuCauNhapHangRequestDTO yeuCauNhapHangRequestDTO) {

        YeuCauNhapHang yeuCauNhapHang = new YeuCauNhapHang();
        yeuCauNhapHang.setTrangThai("Đã Yêu Cầu");
        yeuCauNhapHang.setGhiChu(yeuCauNhapHangRequestDTO.getGhiChu());

        NhanVien nhanVien = nhanVienRepository.findById(yeuCauNhapHangRequestDTO.getMaNhanVien())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));

        yeuCauNhapHang.setNhanVien(nhanVien);
        yeuCauNhapHang = yeuCauNhapHangReponsitory.save(yeuCauNhapHang);

        YeuCauNhapHangDTO yeuCauNhapHangDTO = new YeuCauNhapHangDTO(yeuCauNhapHang);

        List<ChiTietYeuCau> chiTietYeuCau = new ArrayList<>();

        for (ChiTietYeuCauRequestDTO chiTietYeuCauDTO : yeuCauNhapHangRequestDTO.getChiTietYeuCaus()) {
            ChiTietLoaiXe chiTietLoaiXe = chiTietLoaiXeRepository.findById(chiTietYeuCauDTO.getMaChiTietLoaiXe())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiết loại xe"));

            ChiTietYeuCau ctyc = new ChiTietYeuCau();

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

}
