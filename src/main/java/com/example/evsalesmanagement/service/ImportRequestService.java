package com.example.evsalesmanagement.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.importRequest.ImportRequestRequestDTO;
import com.example.evsalesmanagement.dto.importRequest.ImportRequestResponseDTO;
import com.example.evsalesmanagement.dto.importRequest.ImportRequestSummaryDTO;
import com.example.evsalesmanagement.dto.importRequestDetail.ImportRequestDetailResponseDTO;
import com.example.evsalesmanagement.dto.importRequestDetail.ImportRequestDetailRequestDTO;
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
        public ImportRequestResponseDTO createImportRequest(ImportRequestRequestDTO importRequestRequestDTO) {

                // Khởi tạo 1 yêu cầu mới
                ImportRequest importRequest = new ImportRequest();

                importRequest.setStatus("Đã Yêu Cầu");

                importRequest.setNote(importRequestRequestDTO.getNote());

                Employee employee = employeeRepository.findById(importRequestRequestDTO.getEmployeeId())
                                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));

                // Gắn nhân viên tạo đơn
                importRequest.setEmployee(employee);

                importRequest = importRequestRepository.save(importRequest);

                ImportRequestResponseDTO importRequestDTO = new ImportRequestResponseDTO(importRequest);

                List<Integer> vehicleTypeDetailIds = importRequestRequestDTO.getImportRequestDetails()
                                .stream()
                                .map((importRequestDetail) -> {
                                        return importRequestDetail.getVehicleTypeDetailId();
                                })
                                .toList();

                // Gắn chi tiết yêu cầu
                List<VehicleTypeDetail> vehicleTypeDetails = vehicleTypeDetailRepository
                                .getAllByIdWithVehicleType(vehicleTypeDetailIds);

                if (vehicleTypeDetails.size() != vehicleTypeDetailIds.size()) {
                        throw new ResourceNotFoundException("Danh sách loại xe không hợp lệ ");
                }

                Map<Integer, VehicleTypeDetail> vehicleTypeDetailMap = vehicleTypeDetails
                                .stream()
                                .collect(Collectors.toMap(
                                                vehicleTypeDetail -> vehicleTypeDetail.getVehicleTypeDetailId(),
                                                vehicleTypeDetail -> vehicleTypeDetail));

                for (ImportRequestDetailRequestDTO importRequestDetailRequestDTO : importRequestRequestDTO
                                .getImportRequestDetails()) {

                        ImportRequestDetail importRequestDetail = new ImportRequestDetail();

                        // Gắn quan hệ từ con --> cha
                        importRequestDetail.setVehicleTypeDetail(
                                        vehicleTypeDetailMap
                                                        .get(importRequestDetailRequestDTO.getVehicleTypeDetailId()));

                        importRequestDetail.setImportRequest(importRequest);

                        importRequestDetail.setQuantity(importRequestDetailRequestDTO.getQuantity());

                        // Gắn quan hệ từ cha --> con
                        importRequest.getImportRequestDetails().add(importRequestDetail);

                        importRequestDTO.getImportRequestDetails()
                                        .add(new ImportRequestDetailResponseDTO(importRequestDetail));
                }

                // importRequest = importRequestRepository.save(importRequest);

                // importRequest.getImportRequestDetails().forEach(
                // detail -> importRequestDTO.getImportRequestDetails()
                // .add(new ImportRequestDetailResponseDTO(detail)));

                return importRequestDTO;

        }

        @Transactional
        public ImportRequestResponseDTO deleteImportRequest(Integer importRequestId) {

                ImportRequestResponseDTO importRequestResponseDTO = new ImportRequestResponseDTO(
                                importRequestRepository.findById(importRequestId)
                                                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên")));

                importRequestResponseDTO.setImportRequestDetails(
                                importRequestDetailRepository.findByImportRequest_ImportRequestId(importRequestId)
                                                .stream()
                                                .map((ctyc) -> {
                                                        return new ImportRequestDetailResponseDTO(ctyc);
                                                }).toList());

                importRequestDetailRepository.deleteByImportRequest_ImportRequestId(importRequestId);

                importRequestRepository.deleteById(importRequestId);

                return importRequestResponseDTO;

        }

        @Transactional
        public ImportRequestResponseDTO updateImportRequest(Integer importRequestId,
                        ImportRequestRequestDTO importRequestRequestDTO) {

                ImportRequest importRequest = importRequestRepository.findById(importRequestId)
                                .orElseThrow(() -> new RuntimeException("Không tìm thấy yêu cầu"));

                if (!importRequest.getStatus().equals("Chờ duyệt")) {
                        throw new ConflictException("Yêu cầu hiện tại không thể chỉnh sửa");
                }

                ImportRequestResponseDTO importRequestResponseDTO = new ImportRequestResponseDTO(importRequest);

                importRequest.setNote(importRequest.getNote());

                importRequest.getImportRequestDetails().clear();

                List<Integer> vehicleTypeDetailIds = importRequestRequestDTO.getImportRequestDetails()
                                .stream()
                                .map((importRequestDetail) -> {
                                        return importRequestDetail.getVehicleTypeDetailId();
                                })
                                .toList();

                // Gắn chi tiết yêu cầu
                List<VehicleTypeDetail> vehicleTypeDetails = vehicleTypeDetailRepository
                                .getAllByIdWithVehicleType(vehicleTypeDetailIds);

                if (vehicleTypeDetails.size() != vehicleTypeDetailIds.size()) {
                        throw new ResourceNotFoundException("Danh sách loại xe không hợp lệ ");
                }

                Map<Integer, VehicleTypeDetail> vehicleTypeDetailMap = vehicleTypeDetails
                                .stream()
                                .collect(Collectors.toMap(
                                                vehicleTypeDetail -> vehicleTypeDetail.getVehicleTypeDetailId(),
                                                vehicleTypeDetail -> vehicleTypeDetail));

                for (ImportRequestDetailRequestDTO importRequestDetailRequestDTO : importRequestRequestDTO
                                .getImportRequestDetails()) {

                        ImportRequestDetail importRequestDetail = new ImportRequestDetail();

                        // Gắn quan hệ từ con --> cha
                        importRequestDetail.setVehicleTypeDetail(
                                        vehicleTypeDetailMap
                                                        .get(importRequestDetailRequestDTO.getVehicleTypeDetailId()));

                        importRequestDetail.setImportRequest(importRequest);

                        importRequestDetail.setQuantity(importRequestDetailRequestDTO.getQuantity());

                        // Gắn quan hệ từ cha --> con
                        importRequest.getImportRequestDetails().add(importRequestDetail);

                        importRequestResponseDTO.getImportRequestDetails()
                                        .add(new ImportRequestDetailResponseDTO(importRequestDetail));
                }
                return importRequestResponseDTO;
        }

        @Transactional
        public List<ImportRequestSummaryDTO> getAllImportRequests(Pageable pageable,
                        Integer employeeId) {

                Page<ImportRequest> importRequests = employeeId == null ? importRequestRepository.findAll(pageable)
                                : importRequestRepository.findByEmployee_EmployeeId(employeeId, pageable);

                // System.err.println("----> " + yeuCauNhapHangs.toString());
                return importRequests.map(importRequest -> new ImportRequestSummaryDTO(importRequest)).toList();
        }

        @Transactional
        public ImportRequestResponseDTO getImportRequestDetail(Integer importRequestId) {
                ImportRequest importRequest = importRequestRepository.findById(importRequestId)
                                .orElseThrow(() -> new ResourceNotFoundException("Mã yêu cầu không hợp lệ"));

                ImportRequestResponseDTO importRequestDTO = new ImportRequestResponseDTO(importRequest);

                List<ImportRequestDetail> importRequestDetails = importRequestDetailRepository
                                .findByImportRequest_ImportRequestId(importRequestId);

                List<ImportRequestDetailResponseDTO> importRequestDetailDTOs = importRequestDetails.stream()
                                .map((importRequestDetail) -> {
                                        return new ImportRequestDetailResponseDTO(importRequestDetail);
                                }).toList();

                importRequestDTO.setImportRequestDetails(importRequestDetailDTOs);

                return importRequestDTO;
        }

}
