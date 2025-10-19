package com.example.evsalesmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.KhuyenMaiRequestDTO;
import com.example.evsalesmanagement.model.KhuyenMai;
import com.example.evsalesmanagement.repository.ChiTietLoaiXeRepository;
import com.example.evsalesmanagement.repository.DaiLyRepository;
import com.example.evsalesmanagement.repository.KhuyenMaiRepository;

@Service
public class KhuyenMaiService {

    @Autowired
    KhuyenMaiRepository khuyenMaiRepository;

    @Autowired
    DaiLyRepository daiLyRepository;

    @Autowired
    ChiTietLoaiXeRepository chiTietLoaiXeRepository;

    public List<KhuyenMai> layTatCaKhuyenMai() {
        return khuyenMaiRepository.findAll();
    }

    public KhuyenMai layKhuyenMaiTheoMa(Integer maKhuyenMai) {
        KhuyenMai khuyenMai = khuyenMaiRepository.findById(maKhuyenMai)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy KhuyenMai"));

        // KhuyenMaiChiTietDTO khuyenMaiChiTiet = new KhuyenMaiChiTietDTO(khuyenMai);
        // khuyenMaiChiTiet.setChiTietLoaiXes(
        // khuyenMai.getChiTietLoaiXes()
        // .stream()
        // .map(ctlx -> new ChiTietLoaiXeDTO(ctlx))
        // .toList());
        // khuyenMaiChiTiet.setDaiLys(
        // khuyenMai.getDaiLys()
        // .stream()
        // .map(daiLy -> new DaiLyDTO(daiLy))
        // .toList());

        return khuyenMai;
    }

    public KhuyenMai taoKhuyenMai(KhuyenMaiRequestDTO khuyenMai) {

        KhuyenMai khuyenMaiMoi = new KhuyenMai();

        khuyenMaiMoi.setTenKhuyenMai(khuyenMai.getTenKhuyenMai());
        khuyenMaiMoi.setLoaiKhuyenMai(khuyenMai.getLoaiKhuyenMai());
        khuyenMaiMoi.setGiaTriKhuyenMai(khuyenMai.getGiaTriKhuyenMai());
        khuyenMaiMoi.setTieuChi(khuyenMai.getTieuChi());
        khuyenMaiMoi.setSoTienGiam(khuyenMai.getSoTienGiam());
        khuyenMaiMoi.setPhanTramGiam(khuyenMai.getPhanTramGiam());
        khuyenMaiMoi.setNgayBatDau(khuyenMai.getNgayBatDau());
        khuyenMaiMoi.setNgayHetHan(khuyenMai.getNgayHetHan());

        // Mặc định là đang hoạt động
        khuyenMaiMoi.setTrangThai("Hoạt động");

        khuyenMaiMoi.setChiTietLoaiXes(chiTietLoaiXeRepository.findAllById(khuyenMai.getMaChiTietLoaiXes()));
        khuyenMaiMoi.setDaiLys(daiLyRepository.findAllById(khuyenMai.getMaDaiLys()));

        return khuyenMaiRepository.save(khuyenMaiMoi);

        // return
    }

    public KhuyenMai xoaKhuyenMai(Integer maKhuyenMai) {
        KhuyenMai khuyenMai = khuyenMaiRepository.findById(maKhuyenMai)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy KhuyenMai"));

        khuyenMai.getChiTietLoaiXes().size();
        khuyenMai.getDaiLys().size();
        khuyenMaiRepository.deleteById(maKhuyenMai);
        return khuyenMai;

    }

    public KhuyenMai capKhuyenMai(Integer maKhuyenMai, KhuyenMaiRequestDTO khuyenMai) {
        KhuyenMai khuyenMaiCapNhat = khuyenMaiRepository.findById(maKhuyenMai)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy KhuyenMai"));

        khuyenMaiCapNhat.setTenKhuyenMai(khuyenMai.getTenKhuyenMai());
        khuyenMaiCapNhat.setLoaiKhuyenMai(khuyenMai.getLoaiKhuyenMai());
        khuyenMaiCapNhat.setGiaTriKhuyenMai(khuyenMai.getGiaTriKhuyenMai());
        khuyenMaiCapNhat.setTieuChi(khuyenMai.getTieuChi());
        khuyenMaiCapNhat.setSoTienGiam(khuyenMai.getSoTienGiam());
        khuyenMaiCapNhat.setPhanTramGiam(khuyenMai.getPhanTramGiam());
        khuyenMaiCapNhat.setNgayBatDau(khuyenMai.getNgayBatDau());
        khuyenMaiCapNhat.setNgayHetHan(khuyenMai.getNgayHetHan());

        // Mặc định là đang hoạt động
        khuyenMaiCapNhat.setTrangThai(khuyenMai.getTrangThai());

        khuyenMaiCapNhat.setChiTietLoaiXes(chiTietLoaiXeRepository.findAllById(khuyenMai.getMaChiTietLoaiXes()));
        khuyenMaiCapNhat.setDaiLys(daiLyRepository.findAllById(khuyenMai.getMaDaiLys()));

        return khuyenMaiRepository.save(khuyenMaiCapNhat);
    }

}
