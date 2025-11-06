package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryResponseDTO;
import com.example.evsalesmanagement.enums.VehicleDeliveryEnum;
import com.example.evsalesmanagement.repository.VehicleDeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleDeliveryService {

    @Autowired
    private VehicleDeliveryRepository repository;

    // Lấy tất cả giao xe
    public List<VehicleDeliveryResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(v -> new VehicleDeliveryResponseDTO(v))
                .toList(); // Nếu Java 8 thì dùng .collect(Collectors.toList())
    }

    // Lấy giao xe theo trạng thái Pending
    public List<VehicleDeliveryResponseDTO> getPendingDeliveries() {
        return repository.findPendingDeliveries()
                .stream()
                .map(v -> new VehicleDeliveryResponseDTO(v))
                .toList();
    }

    // Lấy giao xe theo trạng thái In Progress
    public List<VehicleDeliveryResponseDTO> getInProgressDeliveries() {
        return repository.findInProgressDeliveries()
                .stream()
                .map(v -> new VehicleDeliveryResponseDTO(v))
                .toList();
    }

    // Lấy giao xe theo trạng thái Completed
    public List<VehicleDeliveryResponseDTO> getCompletedDeliveries() {
        return repository.findCompletedDeliveries()
                .stream()
                .map(v -> new VehicleDeliveryResponseDTO(v))
                .toList();
    }

    // Lấy theo trạng thái bất kỳ (dùng enum)
    public List<VehicleDeliveryResponseDTO> getByStatus(VehicleDeliveryEnum status) {
        return switch (status) {
            case PENDING -> getPendingDeliveries();
            case IN_PROGRESS -> getInProgressDeliveries();
            case COMPLETED -> getCompletedDeliveries();
        };
    }
}
