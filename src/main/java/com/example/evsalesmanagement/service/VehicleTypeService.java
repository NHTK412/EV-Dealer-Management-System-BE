package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.dto.Vehicle.VehicleTypeDTO;
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
    public VehicleTypeDTO createVehicleType(VehicleTypeDTO vehicleType) {
        VehicleType newVehicleType = new VehicleType();
        newVehicleType.setVehicleTypeName(vehicleType.getVehicleTypeName());
        newVehicleType.setManufactureYear(vehicleType.getManufactureYear());
        newVehicleType.setDescription(vehicleType.getDescription());
        vehicleTypeRepository.save(newVehicleType);
        VehicleTypeDTO vehicleTypeDTO = new VehicleTypeDTO(newVehicleType);
        return vehicleTypeDTO;
        }
    
     @Transactional
    public Page<VehicleTypeDTO> getAllVehicleType(Pageable pageable) {
        Page<VehicleType> vehicleTypePage = vehicleTypeRepository.findAll(pageable);
        return vehicleTypePage.map(VehicleTypeDTO::new);
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

   

    