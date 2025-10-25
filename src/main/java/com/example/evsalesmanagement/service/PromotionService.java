package com.example.evsalesmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.KhuyenMaiRequestDTO;
import com.example.evsalesmanagement.model.Promotion;
import com.example.evsalesmanagement.repository.ChiTietLoaiXeRepository;
import com.example.evsalesmanagement.repository.DaiLyRepository;
import com.example.evsalesmanagement.repository.PromotionRepository;

@Service
public class PromotionService {

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    DaiLyRepository daiLyRepository;

    @Autowired
    ChiTietLoaiXeRepository chiTietLoaiXeRepository;

    public List<Promotion> layTatCaKhuyenMai() {
        return promotionRepository.findAll();
    }

    public Promotion layKhuyenMaiTheoMa(Integer maKhuyenMai) {
        Promotion khuyenMai = promotionRepository.findById(maKhuyenMai)
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

    public Promotion taoKhuyenMai(KhuyenMaiRequestDTO khuyenMai) {

        Promotion khuyenMaiMoi = new Promotion();

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

        return promotionRepository.save(khuyenMaiMoi);

        // return
    }

    public Promotion xoaKhuyenMai(Integer maKhuyenMai) {
        Promotion khuyenMai = promotionRepository.findById(maKhuyenMai)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy KhuyenMai"));

        khuyenMai.getChiTietLoaiXes().size();
        khuyenMai.getDaiLys().size();
        promotionRepository.deleteById(maKhuyenMai);
        return khuyenMai;

    }

    public Promotion capKhuyenMai(Integer maKhuyenMai, KhuyenMaiRequestDTO khuyenMai) {
        Promotion khuyenMaiCapNhat = promotionRepository.findById(maKhuyenMai)
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

        return promotionRepository.save(khuyenMaiCapNhat);
    }

}
