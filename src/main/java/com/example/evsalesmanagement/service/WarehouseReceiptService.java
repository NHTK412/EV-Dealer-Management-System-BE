package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.dto.Warehouse.WarehouseImportReceiptRequestDTO;
import com.example.evsalesmanagement.dto.Warehouse.WarehouseImportReceiptResponseDTO;
import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.model.Vehicle;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.model.WarehouseReceipt;
import com.example.evsalesmanagement.dto.Vehicle.VehicleResponseDTO;
import com.example.evsalesmanagement.repository.AgencyRepository;
import com.example.evsalesmanagement.repository.VehicleRepository;
import com.example.evsalesmanagement.repository.WarehouseReceiptRepository;
import com.example.evsalesmanagement.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.evsalesmanagement.dto.Warehouse.WarehouseImportReceiptSummaryDTO;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;


@Service
public class WarehouseReceiptService {
    @Autowired
    private com.example.evsalesmanagement.repository.EmployeeRepository employeeRepository;
    @Autowired
    private WarehouseReceiptRepository warehouseReceiptRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private AgencyRepository agencyRepository;

   
    public List<WarehouseImportReceiptSummaryDTO> getAllWarehouseReceipts(Pageable pageable) {
        Page<WarehouseReceipt> receipts = warehouseReceiptRepository.findAll(pageable);
        return receipts.stream().map(WarehouseImportReceiptSummaryDTO::new).toList();
    }

    @Transactional
    public WarehouseImportReceiptResponseDTO getWarehouseReceiptById(Integer id) {
        WarehouseReceipt receipt = warehouseReceiptRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phiếu xuất nhập với id:" + id));
        return new WarehouseImportReceiptResponseDTO(receipt);
    }


    
    @Transactional
    public ApiResponse<WarehouseImportReceiptResponseDTO> importReceipt(WarehouseImportReceiptRequestDTO request) {
    WarehouseReceipt receipt = new WarehouseReceipt();
    receipt.setWarehouseReceiptDate(request.getWarehouseReceiptDate());
    receipt.setReason(request.getReason());
    receipt.setTotalAmount(request.getTotalAmount());
    receipt.setNote(request.getNote());
    receipt.setStatus(request.getStatus());
        Optional<Agency> agencyOpt = agencyRepository.findById(request.getAgencyId());
        if (agencyOpt.isEmpty()) {
            return new ApiResponse<>(false, "Không tìm thấy đại lý", null);
        }
        receipt.setAgencyId(agencyOpt.get());
        if (request.getEmployeeId() != null) {
            Optional<Employee> employeeOpt = employeeRepository.findById(request.getEmployeeId());
            if (employeeOpt.isPresent()) {
                receipt.setEmployeeId(employeeOpt.get());
            }
        }
        List<Vehicle> vehicles = vehicleRepository.findAllById(request.getVehicleIds());
        for (Vehicle v : vehicles) {
            v.setAgency(agencyOpt.get()); 
        }
    vehicleRepository.saveAll(vehicles);
    receipt.setVehicles(vehicles);
    warehouseReceiptRepository.save(receipt);
        WarehouseImportReceiptResponseDTO responseDTO = new WarehouseImportReceiptResponseDTO(receipt);
        responseDTO.setVehicles(
            vehicles.stream()
                .map(VehicleResponseDTO::new)
                .collect(java.util.stream.Collectors.toList())
        );
        return new ApiResponse<>(true, "Nhập kho thành công", responseDTO);
    }

    @Transactional
    public ApiResponse<WarehouseImportReceiptResponseDTO> updateWarehouseReceipt(Integer id, WarehouseImportReceiptRequestDTO request) {
        WarehouseReceipt receipt = warehouseReceiptRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phiếu xuất nhập với id:" + id));
        if (!"chờ xác nhận tạo phiếu".equalsIgnoreCase(receipt.getStatus())) {
            return new ApiResponse<>(false, "Chỉ được cập nhật khi trạng thái là 'chờ xác nhận tạo phiếu'", null);
        }
        receipt.setWarehouseReceiptDate(request.getWarehouseReceiptDate());
        receipt.setReason(request.getReason());
        receipt.setTotalAmount(request.getTotalAmount());
        receipt.setNote(request.getNote());
        receipt.setStatus(request.getStatus());

        Optional<Agency> agencyOpt = agencyRepository.findById(request.getAgencyId());
        if (agencyOpt.isEmpty()) {
            return new ApiResponse<>(false, "Không tìm thấy đại lý", null);
        }
        receipt.setAgencyId(agencyOpt.get());
        // receipt.setEmployeeId(...);
        List<Vehicle> vehicles = vehicleRepository.findAllById(request.getVehicleIds());
        for (Vehicle v : vehicles) {
            if (v.getAgency() == null) {
                v.setAgency(agencyOpt.get());
            }
        }
        vehicleRepository.saveAll(vehicles);
        warehouseReceiptRepository.save(receipt);
        WarehouseImportReceiptResponseDTO responseDTO = new WarehouseImportReceiptResponseDTO(receipt);
        return new ApiResponse<>(true, "Cập nhật phiếu nhập kho thành công", responseDTO);
    }

    @Transactional
    public ApiResponse<Void> deleteWarehouseReceipt(Integer id) {
        WarehouseReceipt receipt = warehouseReceiptRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phiếu xuất nhập với id:" + id));
        if (!"chờ xác nhận tạo phiếu".equalsIgnoreCase(receipt.getStatus())) {
            return new ApiResponse<>(false, "Chỉ được xóa khi trạng thái là 'chờ xác nhận tạo phiếu'", null);
        }
        warehouseReceiptRepository.delete(receipt);
        return new ApiResponse<>(true, "Xóa phiếu nhập kho thành công", null);
    }
}
