package com.example.evsalesmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.repository.ChiTietLoaiXeRepository;
import com.example.evsalesmanagement.repository.AgencyRepository;
import com.example.evsalesmanagement.repository.XeRepository;
import com.example.evsalesmanagement.dto.XeDTO;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.ChiTietLoaiXe;
import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.model.Xe;

@Service
public class XeService {
    @Autowired
    private XeRepository xeRepository;

    @Autowired
    private AgencyRepository daiLyRepository;

    @Autowired
    private ChiTietLoaiXeRepository chiTietLoaiRepository;

    public Xe createXe(XeDTO request) {
        Xe xe = new Xe();
        xe.setSoKhung(request.getSoKhung());
        xe.setSoMay(request.getSoMay());
        xe.setTrangThai(request.getTrangThai());
        xe.setTinhTrangXe(request.getTinhTrangXe());

        // Lấy đối tượng ChiTietLoaiXe theo ID
        ChiTietLoaiXe chiTietLoaiXe = chiTietLoaiRepository.findById(request.getMaChiTietLoaiXe())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy ChiTietLoaiXe với ID: " + request.getMaChiTietLoaiXe()));
        xe.setChiTietLoaiXe(chiTietLoaiXe);

        // Lấy đối tượng DaiLy theo ID
        Agency daiLy = daiLyRepository.findById(request.getMaDaiLy())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy DaiLy với ID: " + request.getMaDaiLy()));
        xe.setDaiLy(daiLy);

        return xeRepository.save(xe);
    }

    public List<Xe> getAllXe() {
        return xeRepository.findAll();
    }

    public Xe getXeById(Integer maXe) {
        return xeRepository.findById(maXe).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy xe với ID: " + maXe));
    }

    public Xe updateXe(Integer maXe, XeDTO request) {
        Xe xe = getXeById(maXe);

        xe.setSoKhung(request.getSoKhung());
        xe.setSoMay(request.getSoMay());
        xe.setTrangThai(request.getTrangThai());
        xe.setTinhTrangXe(request.getTinhTrangXe());
        return xeRepository.save(xe);
    }

    public void deleteXe(Integer maXe) {
        xeRepository.deleteById(maXe);
    }
}
