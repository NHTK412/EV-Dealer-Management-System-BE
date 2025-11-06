package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.dto.vehicletype.VehicleTypeRequestDTO;
import com.example.evsalesmanagement.dto.vehicletype.VehicleTypeResponseDTO;
import com.example.evsalesmanagement.dto.vehicletype.VehicleTypeSummaryDTO;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.VehicleType;
import com.example.evsalesmanagement.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class VehicleTypeService {
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Transactional
    public VehicleTypeResponseDTO createVehicleType(VehicleTypeRequestDTO request) {
        VehicleType newVehicleType = new VehicleType();
        newVehicleType.setVehicleTypeName(request.getVehicleTypeName());
        newVehicleType.setManufactureYear(request.getManufactureYear());
        newVehicleType.setDescription(request.getDescription());
        vehicleTypeRepository.save(newVehicleType);
        return new VehicleTypeResponseDTO(newVehicleType);
    }
    
     @Transactional
    public Page<VehicleTypeSummaryDTO> getAllVehicleType(Pageable pageable) {
        Page<VehicleType> vehicleTypePage = vehicleTypeRepository.findAll(pageable);
        return vehicleTypePage.map(VehicleTypeSummaryDTO::new);
    }

    @Transactional
    public VehicleTypeResponseDTO getVehicleTypeById(Integer vehicleTypeId) {
        VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy loại xe với id :" + vehicleTypeId));
        return new VehicleTypeResponseDTO(vehicleType);
    }
    
    @Transactional
    public VehicleTypeResponseDTO updateVehicleType(Integer vehicleTypeId, VehicleTypeRequestDTO request) {
        VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy loại xe với id:" + vehicleTypeId));
        vehicleType.setVehicleTypeName(request.getVehicleTypeName());
        vehicleType.setManufactureYear(request.getManufactureYear());
        vehicleType.setDescription(request.getDescription());
        vehicleTypeRepository.save(vehicleType);
        return new VehicleTypeResponseDTO(vehicleType);
    }

    @Transactional
    public VehicleTypeResponseDTO deleteVehicleType(Integer vehicleTypeId) {
        VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy loại xe với id:" + vehicleTypeId));
        VehicleTypeResponseDTO dto = new VehicleTypeResponseDTO(vehicleType);
        vehicleTypeRepository.deleteById(vehicleTypeId);
        return dto;
    }
}

   

    