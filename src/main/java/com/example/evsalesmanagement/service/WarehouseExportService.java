package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.dto.Warehouse.WarehouseExportReceiptRequestDTO;
import com.example.evsalesmanagement.dto.Warehouse.WarehouseExportReceiptResponseDTO;
import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.model.Vehicle;
import com.example.evsalesmanagement.model.WarehouseReceipt;
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
import com.example.evsalesmanagement.dto.Warehouse.WarehouseExportReceiptSummaryDTO;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;

@Service
public class WarehouseExportService {
    @Autowired
    private com.example.evsalesmanagement.repository.EmployeeRepository employeeRepository;
    @Autowired
    private WarehouseReceiptRepository warehouseReceiptRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private AgencyRepository agencyRepository;

    // Lấy tất cả phiếu xuất kho (phân trang)
    public List<WarehouseExportReceiptSummaryDTO> getAllWarehouseExports(Pageable pageable) {
        Page<WarehouseReceipt> receipts = warehouseReceiptRepository.findAll(pageable);
        // Có thể filter thêm nếu cần chỉ lấy phiếu xuất (ví dụ theo agencyId = 1)
        return receipts.stream().map(WarehouseExportReceiptSummaryDTO::new).toList();
    }

    // Lấy chi tiết phiếu xuất kho theo id
    @Transactional
    public WarehouseExportReceiptResponseDTO getByIdWarehouseExport(Integer id) {
        WarehouseReceipt receipt = warehouseReceiptRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phiếu xuất kho với id:" + id));
        return new WarehouseExportReceiptResponseDTO(receipt);
    }

    // POST phiếu xuất: agencyId = 1, chuyển các xe về null
    @Transactional
    public ApiResponse<WarehouseExportReceiptResponseDTO> exportReceipt(WarehouseExportReceiptRequestDTO request) {
        WarehouseReceipt receipt = new WarehouseReceipt();
        receipt.setWarehouseReceiptDate(request.getWarehouseReceiptDate());
        receipt.setReason(request.getReason());
        receipt.setTotalAmount(request.getTotalAmount());
        receipt.setNote(request.getNote());
        receipt.setStatus(request.getStatus());
        // Gán agencyId = 1
        Optional<Agency> agencyOpt = agencyRepository.findById(1);
        if (agencyOpt.isEmpty()) {
            return new ApiResponse<>(false, "Không tìm thấy đại lý mặc định (id=1)", null);
        }
        receipt.setAgencyId(agencyOpt.get());
        if (request.getEmployeeId() != null) {
            Optional<com.example.evsalesmanagement.model.Employee> employeeOpt = employeeRepository.findById(request.getEmployeeId());
            if (employeeOpt.isPresent()) {
                receipt.setEmployeeId(employeeOpt.get());
            }
        }
        // Lấy danh sách xe và chuyển agency về null
        List<Vehicle> vehicles = vehicleRepository.findAllById(request.getVehicleIds());
        for (Vehicle v : vehicles) {
            v.setAgency(null);
        }
        vehicleRepository.saveAll(vehicles);
        receipt.setVehicles(vehicles); // Gán danh sách xe vào entity
        warehouseReceiptRepository.save(receipt);
        WarehouseExportReceiptResponseDTO responseDTO = new WarehouseExportReceiptResponseDTO(receipt);
        responseDTO.setVehicles(
            vehicles.stream()
                .map(com.example.evsalesmanagement.dto.Vehicle.VehicleResponseDTO::new)
                .collect(java.util.stream.Collectors.toList())
        );
        return new ApiResponse<>(true, "Xuất kho thành công", responseDTO);
    }

    // PUT: Cập nhật phiếu xuất kho, chỉ cho phép khi status là "chờ xác nhận tạo phiếu"
    @Transactional
    public ApiResponse<WarehouseExportReceiptResponseDTO> updateWarehouseExport(Integer id, WarehouseExportReceiptRequestDTO request) {
        WarehouseReceipt receipt = warehouseReceiptRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phiếu xuất kho với id:" + id));
        if (!"chờ xác nhận tạo phiếu".equalsIgnoreCase(receipt.getStatus())) {
            return new ApiResponse<>(false, "Chỉ được cập nhật khi trạng thái là 'chờ xác nhận tạo phiếu'", null);
        }
        receipt.setWarehouseReceiptDate(request.getWarehouseReceiptDate());
        receipt.setReason(request.getReason());
        receipt.setTotalAmount(request.getTotalAmount());
        receipt.setNote(request.getNote());
        receipt.setStatus(request.getStatus());
        // Gán agencyId = 1
        Optional<Agency> agencyOpt = agencyRepository.findById(1);
        if (agencyOpt.isEmpty()) {
            return new ApiResponse<>(false, "Không tìm thấy đại lý mặc định (id=1)", null);
        }
        receipt.setAgencyId(agencyOpt.get());
        if (request.getEmployeeId() != null) {
            Optional<com.example.evsalesmanagement.model.Employee> employeeOpt = employeeRepository.findById(request.getEmployeeId());
            if (employeeOpt.isPresent()) {
                receipt.setEmployeeId(employeeOpt.get());
            }
        }
        // Cập nhật danh sách xe: chuyển agency về null
        List<Vehicle> vehicles = vehicleRepository.findAllById(request.getVehicleIds());
        for (Vehicle v : vehicles) {
            v.setAgency(null);
        }
        vehicleRepository.saveAll(vehicles);
        warehouseReceiptRepository.save(receipt);
        WarehouseExportReceiptResponseDTO responseDTO = new WarehouseExportReceiptResponseDTO(receipt);
        return new ApiResponse<>(true, "Cập nhật phiếu xuất kho thành công", responseDTO);
    }

    // DELETE: Xóa phiếu xuất kho, chỉ cho phép khi status là "chờ xác nhận tạo phiếu"
    @Transactional
    public ApiResponse<Void> deleteWarehouseExport(Integer id) {
        WarehouseReceipt receipt = warehouseReceiptRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phiếu xuất kho với id:" + id));
        if (!"chờ xác nhận tạo phiếu".equalsIgnoreCase(receipt.getStatus())) {
            return new ApiResponse<>(false, "Chỉ được xóa khi trạng thái là 'chờ xác nhận tạo phiếu'", null);
        }
        warehouseReceiptRepository.delete(receipt);
        return new ApiResponse<>(true, "Xóa phiếu xuất kho thành công", null);
    }

}
