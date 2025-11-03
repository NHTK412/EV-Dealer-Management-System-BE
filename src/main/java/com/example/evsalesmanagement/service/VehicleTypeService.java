package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.dto.VehicleTypeDTO;
import com.example.evsalesmanagement.model.VehicleType;
import com.example.evsalesmanagement.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class VehicleTypeService {
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Transactional
    public VehicleTypeDTO createVehicleType(VehicleTypeDTO vehicleType) {
        VehicleType newVehicleType = new VehicleType();
        newVehicleType.setVehicleTypeName(vehicleType.getVehicleTypeName());
        newVehicleType.setManufactureYear(vehicleType.getManufactureYear());
        newVehicleType.setDescription(vehicleType.getDescription());
        vehicleTypeRepository.save(newVehicleType);
        VehicleTypeDTO vehicleTypeDTO = new VehicleTypeDTO(newVehicleType);
        return vehicleTypeDTO;
        }
    
    public List<VehicleTypeDTO> getAllVehicleTypes() {
        return vehicleTypeRepository.findAll().stream()
                .map(vt -> new VehicleTypeDTO(vt))
                .toList();
    }

    @Transactional
    public VehicleTypeDTO getVehicleTypeById(Integer vehicleTypeId) {
        VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeId)
                .orElseThrow(() -> new RuntimeException("Vehicle Type not found"));
        VehicleTypeDTO vehicleTypeDTO = new VehicleTypeDTO(vehicleType);
        return vehicleTypeDTO;
    }
    
    @Transactional
    public VehicleTypeDTO updateVehicleType(Integer vehicleTypeId, VehicleTypeDTO vehicleTypeDetails) {
        VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeId)
                .orElseThrow(() -> new RuntimeException("Vehicle Type not found")); 
        vehicleType.setVehicleTypeName(vehicleTypeDetails.getVehicleTypeName());
        vehicleType.setManufactureYear(vehicleTypeDetails.getManufactureYear());
        vehicleType.setDescription(vehicleTypeDetails.getDescription());
        VehicleTypeDTO vehicleTypeDTO = new VehicleTypeDTO(vehicleType);
        return vehicleTypeDTO;
    }

    @Transactional
    public VehicleTypeDTO deleteVehicleType(Integer vehicleTypeId) {
        VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeId)
                .orElseThrow(() -> new RuntimeException("Vehicle Type not found"));
        VehicleTypeDTO vehicleTypeDTO = new VehicleTypeDTO(vehicleType);
        vehicleTypeRepository.deleteById(vehicleTypeId);
        return vehicleTypeDTO;
    }
}

   

    