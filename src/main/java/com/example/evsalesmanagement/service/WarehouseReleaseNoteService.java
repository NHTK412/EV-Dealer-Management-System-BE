package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.model.Vehicle;
import com.example.evsalesmanagement.model.WarehouseReceipt;
import com.example.evsalesmanagement.model.WarehouseReleaseNote;
import com.example.evsalesmanagement.repository.AgencyRepository;
import com.example.evsalesmanagement.repository.VehicleRepository;
import com.example.evsalesmanagement.repository.WarehouseReleaseNoteRepository;
import com.example.evsalesmanagement.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.evsalesmanagement.dto.vehicle.VehicleResponseDTO;
import com.example.evsalesmanagement.dto.warehousereleasenote.WarehouseReleaseNoteRequestDTO;
import com.example.evsalesmanagement.dto.warehousereleasenote.WarehouseReleaseNoteResponseDTO;
import com.example.evsalesmanagement.dto.warehousereleasenote.WarehouseReleaseNoteStatusUpdateDTO;
import com.example.evsalesmanagement.dto.warehousereleasenote.WarehouseReleaseNoteSummaryDTO;
import com.example.evsalesmanagement.enums.WarehouseReleaseNoteStatusEnum;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;

@Service
public class WarehouseReleaseNoteService {
    @Autowired
    private com.example.evsalesmanagement.repository.EmployeeRepository employeeRepository;
    @Autowired
    private WarehouseReleaseNoteRepository warehouseReleaseNoteRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private AgencyRepository agencyRepository;

    public List<WarehouseReleaseNoteSummaryDTO> getAllWarehouseExports(Pageable pageable) {
        Page<WarehouseReleaseNote> release = warehouseReleaseNoteRepository.findAll(pageable);
        return release.stream().map(WarehouseReleaseNoteSummaryDTO::new).toList();
    }

    @Cacheable(value = "warehouse-receipt", key = "#id")
    @Transactional
    public WarehouseReleaseNoteResponseDTO getByIdWarehouseExport(Integer id) {
        WarehouseReleaseNote release = warehouseReleaseNoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phiếu xuất kho với id:" + id));
        return new WarehouseReleaseNoteResponseDTO(release);
    }

    @Transactional
    public ApiResponse<WarehouseReleaseNoteResponseDTO> exportReceipt(WarehouseReleaseNoteRequestDTO request) {
        WarehouseReleaseNote release = new WarehouseReleaseNote();
        release.setReleaseDate(request.getReleaseDate());
        release.setReason(request.getReason());
        release.setTotalAmount(request.getTotalAmount());
        release.setNote(request.getNote());
        release.setStatus(request.getStatus());
        Optional<Agency> agencyOpt = agencyRepository.findById(1);
        if (agencyOpt.isEmpty()) {
            return new ApiResponse<>(false, "Không tìm thấy đại lý mặc định (id=1)", null);
        }
        release.setAgencyId(agencyOpt.get());
        if (request.getEmployeeId() != null) {
            Optional<Employee> employeeOpt = employeeRepository
                    .findById(request.getEmployeeId());
            if (employeeOpt.isPresent()) {
                release.setEmployeeId(employeeOpt.get());
            }
        }
        List<Vehicle> vehicles = vehicleRepository.findAllById(request.getVehicleIds());
        for (Vehicle v : vehicles) {
            v.setAgency(null);
        }
        vehicleRepository.saveAll(vehicles);
        release.setVehicles(vehicles.stream().collect(Collectors.toSet())); // Gán danh sách xe vào entity
        warehouseReleaseNoteRepository.save(release);
        WarehouseReleaseNoteResponseDTO responseDTO = new WarehouseReleaseNoteResponseDTO(release);
        responseDTO.setVehicles(
                vehicles.stream()
                        .map(VehicleResponseDTO::new)
                        .collect(java.util.stream.Collectors.toList()));
        return new ApiResponse<>(true, "Xuất kho thành công", responseDTO);
    }

    @CachePut(value = "warehouse-release-note", key = "#id")
    @Transactional
    public ApiResponse<WarehouseReleaseNoteResponseDTO> updateWarehouseExport(Integer id,
            WarehouseReleaseNoteStatusUpdateDTO request) {
        WarehouseReleaseNote release = warehouseReleaseNoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phiếu xuất kho với id:" + id));
        if (release.getStatus() != null){
            release.setStatus(request.getStatus());
        }
        warehouseReleaseNoteRepository.save(release);
        WarehouseReleaseNoteResponseDTO responseDTO = new WarehouseReleaseNoteResponseDTO(release);
        return new ApiResponse<>(true, "Cập nhật trạng thái phiếu xuất kho thành công", responseDTO);
    }

    // phiếu"
    @CacheEvict(value = "warehouse-receipt", key = "#id")
    @Transactional
    public ApiResponse<Void> deleteWarehouseExport(Integer id) {
        WarehouseReleaseNote release = warehouseReleaseNoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phiếu xuất kho với id:" + id));
         if (release.getStatus() != WarehouseReleaseNoteStatusEnum.PENDING_APPROVAL) {
            return new ApiResponse<>(false, "Chỉ được xóa khi trạng thái là 'chờ phê duyệt'", null);
        }
        warehouseReleaseNoteRepository.delete(release);
        return new ApiResponse<>(true, "Xóa phiếu xuất kho thành công", null);
    }

}
