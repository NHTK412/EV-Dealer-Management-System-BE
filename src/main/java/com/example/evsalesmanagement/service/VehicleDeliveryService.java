package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.dto.VehicleDeliveryDTO;
import com.example.evsalesmanagement.enums.VehicleDeliveryEnum;
import com.example.evsalesmanagement.repository.VehicleDeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleDeliveryService {

    @Autowired
    private VehicleDeliveryRepository repository;

    //Lấy tất cả giao xe
    public List<VehicleDeliveryDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(v -> new VehicleDeliveryDTO(v))
                .toList(); // Nếu Java 8 thì dùng .collect(Collectors.toList())
    }

    //Lấy giao xe theo trạng thái Pending
    public List<VehicleDeliveryDTO> getPendingDeliveries() {
        return repository.findPendingDeliveries()
                .stream()
                .map(v -> new VehicleDeliveryDTO(v))
                .toList();
    }

    //Lấy giao xe theo trạng thái In Progress
    public List<VehicleDeliveryDTO> getInProgressDeliveries() {
        return repository.findInProgressDeliveries()
                .stream()
                .map(v -> new VehicleDeliveryDTO(v))
                .toList();
    }

    //Lấy giao xe theo trạng thái Completed
    public List<VehicleDeliveryDTO> getCompletedDeliveries() {
        return repository.findCompletedDeliveries()
                .stream()
                .map(v -> new VehicleDeliveryDTO(v))
                .toList();
    }

    //Lấy theo trạng thái bất kỳ (dùng enum)
    public List<VehicleDeliveryDTO> getByStatus(VehicleDeliveryEnum status) {
        return switch (status) {
            case PENDING -> getPendingDeliveries();
            case IN_PROGRESS -> getInProgressDeliveries();
            case COMPLETED -> getCompletedDeliveries();
        };
    }
}
