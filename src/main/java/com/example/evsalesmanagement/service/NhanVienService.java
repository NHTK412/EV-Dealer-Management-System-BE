package com.example.evsalesmanagement.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.NhanVienDTO;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.NhanVien;
import com.example.evsalesmanagement.repository.DaiLyRepository;
import com.example.evsalesmanagement.repository.NhanVienRepository;

@Service
public class NhanVienService {

    @Autowired
    NhanVienRepository nhanVienRepository;

    @Autowired
    DaiLyRepository daiLyRepository;

    public NhanVienDTO layNhanVienTheoMa(Integer maNhanVien) {
        NhanVien nhanVien = nhanVienRepository.findById(maNhanVien)
                .orElseThrow(() -> new ResourceNotFoundException("Mã nhân viên không hợp lệ"));
        return new NhanVienDTO(nhanVien);

    }

    public List<NhanVienDTO> layTatCaNhanVien(Pageable pageable) {
        Page<NhanVien> nhanViens = nhanVienRepository.findAll(pageable);

        return nhanViens.stream().map((nhanVien) -> new NhanVienDTO(nhanVien)).toList();

    }

    public NhanVienDTO xoaNhanVien(Integer maNhanVien) {
        NhanVien nhanVien = nhanVienRepository.findById(maNhanVien)
                .orElseThrow(() -> new ResourceNotFoundException("Mã nhân viên không hợp lệ"));

        NhanVienDTO nhanVienDTO = new NhanVienDTO(nhanVien);

        nhanVienRepository.deleteById(maNhanVien);
        return nhanVienDTO;
    }

    // // Chỗ này k biết nên tách thành NhanVienDTO và NhanVienRequestDTO k
    // public NhanVienDTO chinhSuaNhanVien(Integer maNhanVien, NhanVienDTO nhanVienRequestDTO) {
    //     NhanVien nhanVien = nhanVienRepository.findById(maNhanVien)
    //             .orElseThrow(() -> new ResourceNotFoundException("Mã nhân viên không hợp lệ"));
    //     nhanVien.setChucVu(nhanVienRequestDTO.getChucVu());
    //     nhanVien.setEmail(nhanVienRequestDTO.getEmail());
    //     nhanVien.setTenNhanVien(nhanVienRequestDTO.getTenNhanVien());
    //     nhanVien.setDaiLy(daiLyRepository.getById(nhanVienRequestDTO.getMaDaiLy())
    //             .orElseThrow(() -> new ResourceNotFoundException("Mã đại lý không hợp lệ")));

    //     ;

    //     // NhanVienDTO nhanVienDTO = new NhanVienDTO(nhanVien);

    //     return nhanVienDTO;
    // }
}
