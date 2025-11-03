package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.dto.VehicleTypeDetailDTO;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.VehicleType;
import com.example.evsalesmanagement.model.VehicleTypeDetail;
import com.example.evsalesmanagement.repository.VehicleTypeDetailRepository;
import com.example.evsalesmanagement.repository.VehicleTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class VehicleTypeDetailService {

    @Autowired
    private VehicleTypeDetailRepository vehicleTypeDetailRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    public List<VehicleTypeDetailDTO> getAllVehicleTypeDetails(){
        return vehicleTypeDetailRepository.findAll().stream()
                .map(vtd -> new VehicleTypeDetailDTO(vtd))
                .toList();
    }

    @Transactional
    public VehicleTypeDetailDTO getVehicleTypeDetailById(Integer vehicleTypeDetailId) {
        var vehicleTypeDetail = vehicleTypeDetailRepository.findById(vehicleTypeDetailId)
                .orElseThrow(() -> new RuntimeException("Vehicle Type Detail not found"));
        VehicleTypeDetailDTO vehicleTypeDetailDTO = new VehicleTypeDetailDTO(vehicleTypeDetail);
        return vehicleTypeDetailDTO;
    }

    @Transactional
    public VehicleTypeDetailDTO createVehicleTypeDetail(VehicleTypeDetailDTO vehicleTypeDetail){
        VehicleTypeDetail newVehicleTypeDetail = new VehicleTypeDetail();
        newVehicleTypeDetail.setVehicleImage(vehicleTypeDetail.getVehicleImage());
        newVehicleTypeDetail.setConfiguration(vehicleTypeDetail.getConfiguration());
        newVehicleTypeDetail.setColor(vehicleTypeDetail.getColor());
        newVehicleTypeDetail.setVersion(vehicleTypeDetail.getVersion());
        newVehicleTypeDetail.setFeatures(vehicleTypeDetail.getFeatures());
        newVehicleTypeDetail.setPrice(vehicleTypeDetail.getPrice());
        VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeDetail.getVehicleTypeId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy chi tiết loại xe với ID: " + newVehicleTypeDetail.getVehicleType()));
        newVehicleTypeDetail.setVehicleType(vehicleType);
        VehicleTypeDetailDTO vehicleTypeDetailDTO = new VehicleTypeDetailDTO(newVehicleTypeDetail);
        return vehicleTypeDetailDTO;
    }
    
    @Transactional
    public VehicleTypeDetailDTO updateVehicleTypeDetail(Integer vehicleTypeDetailId, VehicleTypeDetailDTO vehicleTypeDetailDetails) {
        VehicleTypeDetail vehicleTypeDetail = vehicleTypeDetailRepository.findById(vehicleTypeDetailId)
                .orElseThrow(() -> new RuntimeException("Vehicle Type Detail not found"));
        vehicleTypeDetail.setVehicleImage(vehicleTypeDetailDetails.getVehicleImage());
        vehicleTypeDetail.setConfiguration(vehicleTypeDetailDetails.getConfiguration());
        vehicleTypeDetail.setColor(vehicleTypeDetailDetails.getColor());
        vehicleTypeDetail.setVersion(vehicleTypeDetailDetails.getVersion());
        vehicleTypeDetail.setFeatures(vehicleTypeDetailDetails.getFeatures());
        vehicleTypeDetail.setPrice(vehicleTypeDetailDetails.getPrice());
        VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeDetailDetails.getVehicleTypeId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy chi tiết loại xe với ID: " + vehicleTypeDetail.getVehicleType()));
        vehicleTypeDetail.setVehicleType(vehicleType);
        VehicleTypeDetailDTO vehicleTypeDetailDTO = new VehicleTypeDetailDTO(vehicleTypeDetail);
        return vehicleTypeDetailDTO;
    }

    @Transactional
    public VehicleTypeDetailDTO deleteVehicleTypeDetail(Integer vehicleTypeDetailId) {
        VehicleTypeDetail vehicleTypeDetail = vehicleTypeDetailRepository.findById(vehicleTypeDetailId)
                .orElseThrow(() -> new RuntimeException("Vehicle Type Detail not found"));
        VehicleTypeDetailDTO vehicleTypeDetailDTO = new VehicleTypeDetailDTO(vehicleTypeDetail);
        vehicleTypeDetailRepository.deleteById(vehicleTypeDetailId);
        return vehicleTypeDetailDTO;

    }
}