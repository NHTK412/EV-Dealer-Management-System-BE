package com.example.evsalesmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.evsalesmanagement.repository.VehicleTypeDetailRepository;
import com.example.evsalesmanagement.repository.AgencyRepository;
import com.example.evsalesmanagement.repository.VehicleRepository;
import com.example.evsalesmanagement.dto.VehicleDTO;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.VehicleTypeDetail;
import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.model.Vehicle;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private VehicleTypeDetailRepository vehicleTypeDetailRepository;

    public Vehicle createVehicle(VehicleDTO request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setChassicNumber(request.getChassisNumber());
        vehicle.setMachineNumber(request.getMachineNumber());
        vehicle.setStatus(request.getStatus());
        vehicle.setVehicleCondition(request.getVehicleCondition());
        

        // Lấy đối tượng vehicleTypeDetail theo ID
        VehicleTypeDetail vehicleTypeDetail = vehicleTypeDetailRepository.findById(request.getVehicleTypeDetailId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy chi tiết loại xe với ID: " + request.getVehicleTypeDetailId()));
        vehicle.setVehicleTypeDetail(vehicleTypeDetail);

        // Lấy đối tượng DaiLy theo ID
        Agency agency = agencyRepository.findById(request.getAgencyId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy đại lý với ID: " + request.getAgencyId()));
        vehicle.setAgency(agency);

        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public Page<VehicleDTO> getAllVehicle(Pageable pageable) {
        Page<Vehicle> vehiclePage = vehicleRepository.findAll(pageable);
        return vehiclePage.map(VehicleDTO::new);
    }


    public Vehicle getVehicleById(Integer vehicleId) {
        return vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy xe với ID: " + vehicleId));
    }

    public Vehicle updateVehicle(Integer vehicleId, VehicleDTO request) {
        Vehicle vehicle = getVehicleById(vehicleId);

        vehicle.setChassicNumber(request.getChassisNumber());
        vehicle.setMachineNumber(request.getMachineNumber());
        vehicle.setStatus(request.getStatus());
        vehicle.setVehicleCondition(request.getVehicleCondition());
    
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Integer vehicle) {
        vehicleRepository.deleteById(vehicle);
    }
}
