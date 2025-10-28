package com.example.evsalesmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.ImportRequestDetailDTO;
import com.example.evsalesmanagement.dto.ImportRequestDetailRequestDTO;
import com.example.evsalesmanagement.dto.ImportRequestDTO;
import com.example.evsalesmanagement.dto.ImportRequestRequestDTO;
import com.example.evsalesmanagement.exception.ConflictException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.VehicleTypeDetail;
import com.example.evsalesmanagement.model.ImportRequestDetail;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.model.ImportRequest;
import com.example.evsalesmanagement.repository.VehicleTypeDetailRepository;
import com.example.evsalesmanagement.repository.ImportRequestDetailRepository;
import com.example.evsalesmanagement.repository.EmployeeRepository;
import com.example.evsalesmanagement.repository.ImportRequestRepository;

import jakarta.transaction.Transactional;

@Service
public class YeuCauNhapHangService {

    @Autowired
    ImportRequestRepository importRequestRepository;

    @Autowired
    ImportRequestDetailRepository importRequestDetailRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    VehicleTypeDetailRepository vehicleTypeDetailRepository;

    @Transactional
    public ImportRequestDTO taoYeuCauNhapHang(ImportRequestRequestDTO importRequestRequestDTO) {

        ImportRequest importRequest = new ImportRequest();
        importRequest.setStatus("Đã Yêu Cầu");
        importRequest.setNote(importRequestRequestDTO.getNote());

        Employee employee = employeeRepository.findById(importRequestRequestDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));

        importRequest.setEmployee(employee);
        importRequest = importRequest.save(importRequest);

        ImportRequestDTO importRequestDTO = new ImportRequestDTO(importRequest);

        List<ImportRequestDetail> importRequestDetails = new ArrayList<>();

        for (ImportRequestDetailRequestDTO importRequestDetailRequestDTO: importRequestRequestDTO.getImportRequestDetails()) {
            VehicleTypeDetail vehicleTypeDetail = vehicleTypeDetailRepository.findById(importRequestDetailRequestDTO.getVehicleTypeDetailId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiết loại xe"));

            ImportRequestDetail importRequestDetail = new ImportRequestDetail();

            importRequestDetail.setMaChiTietLoaiXe(vehicleTypeDetail.getMaChiTietLoaiXe());
            importRequestDetail.setChiTietLoaiXe(vehicleTypeDetail);

            importRequestDetail.setSoLuong(importRequestDetailRequestDTO.getSoLuong());
            importRequestDetail.setMaYeuCau(importRequest.getMaYeuCau());
            importRequestDetail.setYeuCauNhapHang(importRequest);

            importRequestDetailRepository.save(importRequestDetail);
            importRequestDetails.add(importRequestDetail);

            yeuCauNhapHangDTO.getChiTietYeuCaus().add(new ImportRequestDetailDTO(ctyc));

        }

        return yeuCauNhapHangDTO;

        // YeuCauNhapHang yeuCauNhapHang = new YeuCauNhapHang();
        // yeuCauNhapHang.setTrangThai("Đã Yêu Cầu");
        // yeuCauNhapHang.setGhiChu(importRequestRequestDTO.getGhiChu());

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
    public ImportRequestDTO xoaYeuCauNhapHang(Integer maYeuCauNhapHang) {

        ImportRequestDTO yeuCauNhapHangDTO = new ImportRequestDTO(yeuCauNhapHangReponsitory.findById(maYeuCauNhapHang)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên")));

        yeuCauNhapHangDTO.setChiTietYeuCaus(
                chiTietYeuCauRepository.findByYeuCauNhapHang_MaYeuCau(maYeuCauNhapHang).stream().map((ctyc) -> {
                    return new ImportRequestDetailDTO(ctyc);
                }).toList());

        chiTietYeuCauRepository.deleteByYeuCauNhapHang_MaYeuCau(maYeuCauNhapHang);

        yeuCauNhapHangReponsitory.deleteById(maYeuCauNhapHang);

        return yeuCauNhapHangDTO;

    }

    @Transactional
    public ImportRequestDTO chinhSuaYeuCauNhapHang(Integer maYeuCauNhapHang,
            ImportRequestRequestDTO yeuCauNhapHangRequestDTO) {
        ImportRequest yeuCauNhapHang = yeuCauNhapHangReponsitory.findById(maYeuCauNhapHang)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy yêu cầu"));

        if (!yeuCauNhapHang.getTrangThai().equals("Chờ duyệt")) {
            throw new ConflictException("Yêu cầu hiện tại không thể chỉnh sửa");
        }

        ImportRequestDTO yeuCauNhapHangDTO = new ImportRequestDTO(yeuCauNhapHang);

        yeuCauNhapHang.setGhiChu(yeuCauNhapHangRequestDTO.getGhiChu());

        chiTietYeuCauRepository.deleteByYeuCauNhapHang_MaYeuCau(maYeuCauNhapHang);
        List<ImportRequestDetail> chiTietYeuCaus = new ArrayList<>();

        for (ImportRequestDetailRequestDTO chiTietYeuCauDTO : yeuCauNhapHangRequestDTO.getChiTietYeuCaus()) {
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

            yeuCauNhapHangDTO.getChiTietYeuCaus().add(new ImportRequestDetailDTO(ctyc));

        }
        return yeuCauNhapHangDTO;
    }

    @Transactional
    public List<ImportRequestDTO> layTatCaYeuCauNhapHang(Pageable pageable, Integer maNhanVien) {

        Page<ImportRequest> yeuCauNhapHangs = maNhanVien == null ? yeuCauNhapHangReponsitory.findAll(pageable)
                : yeuCauNhapHangReponsitory.findByNhanVien_MaNhanVien(maNhanVien, pageable);
        // System.err.println("----> " + yeuCauNhapHangs.toString());
        return yeuCauNhapHangs.map(yeuCauNhapHang -> new ImportRequestDTO(yeuCauNhapHang)).toList();
    }

    @Transactional
    public ImportRequestDTO layChiTietYeuCauNhapHang(Integer maYeuCauNhapHang) {
        ImportRequest yeuCauNhapHang = yeuCauNhapHangReponsitory.findById(maYeuCauNhapHang)
                .orElseThrow(() -> new ResourceNotFoundException("Mã yêu cầu không hợp lệ"));

        ImportRequestDTO yeuCauNhapHangDTO = new ImportRequestDTO(yeuCauNhapHang);

        List<ImportRequestDetail> chiTietYeuCaus = chiTietYeuCauRepository.findByYeuCauNhapHang_MaYeuCau(maYeuCauNhapHang);

        List<ImportRequestDetailDTO> chiTietYeuCauDTOs = chiTietYeuCaus.stream().map((chiTietYeuCau) -> {
            return new ImportRequestDetailDTO(chiTietYeuCau);
        }).toList();

        yeuCauNhapHangDTO.setChiTietYeuCaus(chiTietYeuCauDTOs);

        return yeuCauNhapHangDTO;
    }

}
