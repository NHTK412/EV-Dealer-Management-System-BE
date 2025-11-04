package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.dto.Warehouse.WarehouseReceiptRequestDTO;
import com.example.evsalesmanagement.dto.Warehouse.WarehouseReceiptResponseDTO;
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

@Service
public class WarehouseExportService {
    @Autowired
    private WarehouseReceiptRepository warehouseReceiptRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private AgencyRepository agencyRepository;

    // POST phiếu xuất: agencyId = 1, chuyển các xe về null
    @Transactional
    public ApiResponse<WarehouseReceiptResponseDTO> exportReceipt(WarehouseReceiptRequestDTO request) {
        WarehouseReceipt receipt = new WarehouseReceipt();
        receipt.setWarehouseReceiptDate(request.getWarehouseReceiptDate());
        receipt.setReason(request.getReason());
        receipt.setTotalAmount(request.getTotalAmount());
        receipt.setNote(request.getNote());
        // Gán agencyId = 1
        Optional<Agency> agencyOpt = agencyRepository.findById(1);
        if (agencyOpt.isEmpty()) {
            return new ApiResponse<>(false, "Không tìm thấy đại lý mặc định (id=1)", null);
        }
        receipt.setAgencyId(agencyOpt.get());
        // receipt.setEmployeeId(...);
        // Lấy danh sách xe và chuyển agency về null
        List<Vehicle> vehicles = vehicleRepository.findAllById(request.getVehicleIds());
        for (Vehicle v : vehicles) {
            v.setAgency(null);
        }
        vehicleRepository.saveAll(vehicles);
        warehouseReceiptRepository.save(receipt);
        WarehouseReceiptResponseDTO responseDTO = new WarehouseReceiptResponseDTO(receipt);
        // responseDTO.setVehicles(...); // map lại nếu cần
        return new ApiResponse<>(true, "Xuất kho thành công", responseDTO);
    }
}
