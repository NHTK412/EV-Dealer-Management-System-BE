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
public class ImportRequestService {

    @Autowired
    ImportRequestRepository importRequestRepository;

    @Autowired
    ImportRequestDetailRepository importRequestDetailRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    VehicleTypeDetailRepository vehicleTypeDetailRepository;

    @Transactional
    public ImportRequestDTO createImportRequest(ImportRequestRequestDTO importRequestRequestDTO) {

        ImportRequest importRequest = new ImportRequest();
        importRequest.setStatus("Đã Yêu Cầu");
        importRequest.setNote(importRequestRequestDTO.getNote());

        Employee employee = employeeRepository.findById(importRequestRequestDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));

        importRequest.setEmployee(employee);
        importRequest = importRequestRepository.save(importRequest);

        ImportRequestDTO importRequestDTO = new ImportRequestDTO(importRequest);
        List<ImportRequestDetail> importRequestDetails = new ArrayList<>();

        for (ImportRequestDetailRequestDTO importRequestDetailRequestDTO: importRequestRequestDTO.getImportRequestDetails()) {
            VehicleTypeDetail vehicleTypeDetail = vehicleTypeDetailRepository.findById(importRequestDetailRequestDTO.getVehicleTypeDetailId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiết loại xe"));

            ImportRequestDetail importRequestDetail = new ImportRequestDetail();

            importRequestDetail.setVehicleTypeDetailId(vehicleTypeDetail.getVehicleTypeDetailId());
            importRequestDetail.setVehicleTypeDetail(vehicleTypeDetail);

            importRequestDetail.setQuantity(importRequestDetailRequestDTO.getQuantity());
            importRequestDetail.setImportRequestId(importRequest.getImportRequestId());
            importRequestDetail.setImportRequest(importRequest);

            importRequestDetailRepository.save(importRequestDetail);
            importRequestDetails.add(importRequestDetail);

            importRequestDTO.getImportRequestDetails().add(new ImportRequestDetailDTO(importRequestDetail));


        }

        return importRequestDTO;

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
    public ImportRequestDTO deleteImportRequest(Integer importRequestId) {

        ImportRequestDTO importRequestDTO = new ImportRequestDTO(importRequestRepository.findById(importRequestId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên")));

        importRequestDTO.setImportRequestDetails(
                importRequestDetailRepository.findByImportRequest_ImportRequestId(importRequestId).stream().map((ctyc) -> {
                    return new ImportRequestDetailDTO(ctyc);
                }).toList());

        importRequestDetailRepository.deleteByImportRequest_ImportRequestId(importRequestId);

        importRequestRepository.deleteById(importRequestId);

        return importRequestDTO;

    }

    @Transactional
    public ImportRequestDTO updateImportRequest(Integer importRequestId,
            ImportRequestRequestDTO importRequestRequestDTO) {
        ImportRequest importRequest = importRequestRepository.findById(importRequestId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy yêu cầu"));

        if (!importRequest.getStatus().equals("Chờ duyệt")) {
            throw new ConflictException("Yêu cầu hiện tại không thể chỉnh sửa");
        }

        ImportRequestDTO importRequestDTO = new ImportRequestDTO(importRequest);

        importRequest.setNote(importRequest.getNote());

        importRequestDetailRepository.deleteByImportRequest_ImportRequestId(importRequestId);
        List<ImportRequestDetail> importRequestDetails = new ArrayList<>();

         for (ImportRequestDetailRequestDTO importRequestDetailDTO : importRequestRequestDTO.getImportRequestDetails()) {
            VehicleTypeDetail vehicleTypeDetail = vehicleTypeDetailRepository.findById(importRequestDetailDTO.getVehicleTypeDetailId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiết loại xe"));

            ImportRequestDetail importRequestDetail = new ImportRequestDetail();

            importRequestDetail.setVehicleTypeDetailId(vehicleTypeDetail.getVehicleTypeDetailId());
            importRequestDetail.setVehicleTypeDetail(vehicleTypeDetail);

            importRequestDetail.setQuantity(importRequestDetailDTO.getQuantity());
            importRequestDetail.setImportRequestId(importRequest.getImportRequestId());
            importRequestDetail.setImportRequest(importRequest);
            importRequestDetailRepository.save(importRequestDetail);
            importRequestDetails.add(importRequestDetail);
         

            importRequestDTO.getImportRequestDetails().add(new ImportRequestDetailDTO(importRequestDetail));

        }
        return importRequestDTO;
    }

    @Transactional
    public List<ImportRequestDTO> getAllImportRequests(Pageable pageable, Integer employeeId) {

        Page<ImportRequest> importRequests = employeeId == null ? importRequestRepository.findAll(pageable)
                : importRequestRepository.findByEmployee_EmployeeId(employeeId, pageable);
                
        // System.err.println("----> " + yeuCauNhapHangs.toString());
        return importRequests.map(importRequest -> new ImportRequestDTO(importRequest)).toList();
    }

    @Transactional
    public ImportRequestDTO getImportRequestDetail(Integer importRequestId) {
        ImportRequest importRequest = importRequestRepository.findById(importRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Mã yêu cầu không hợp lệ"));

        ImportRequestDTO importRequestDTO = new ImportRequestDTO(importRequest);

        List<ImportRequestDetail> importRequestDetails =importRequestDetailRepository.findByImportRequest_ImportRequestId(importRequestId);

        List<ImportRequestDetailDTO> importRequestDetailDTOs = importRequestDetails.stream().map((importRequestDetail) -> {
            return new ImportRequestDetailDTO(importRequestDetail);
        }).toList();

       importRequestDTO.setImportRequestDetails(importRequestDetailDTOs);

        return importRequestDTO;
    }

}
