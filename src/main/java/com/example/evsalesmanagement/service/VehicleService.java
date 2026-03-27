package com.example.evsalesmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.evsalesmanagement.repository.VehicleTypeDetailRepository;
import com.example.evsalesmanagement.repository.AgencyRepository;
import com.example.evsalesmanagement.repository.VehicleRepository;
import com.example.evsalesmanagement.dto.agency.AgencyResponseDTO;
import com.example.evsalesmanagement.dto.vehicle.VehicleRequestDTO;
import com.example.evsalesmanagement.dto.vehicle.VehicleResponseDTO;
import com.example.evsalesmanagement.dto.vehicle.VehicleSummaryDTO;
import com.example.evsalesmanagement.dto.vehicletypedetail.VehicleTypeDetailResponseDTO;
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

        @Transactional
        public VehicleResponseDTO createVehicle(VehicleRequestDTO request) {
                Vehicle newVehicle = new Vehicle();
                newVehicle.setChassicNumber(request.getChassisNumber());
                newVehicle.setMachineNumber(request.getMachineNumber());
                newVehicle.setStatus(request.getStatus());
                newVehicle.setVehicleCondition(request.getVehicleCondition());

                // Lấy đối tượng vehicleTypeDetail theo ID
                VehicleTypeDetail vehicleTypeDetail = vehicleTypeDetailRepository
                                .findById(request.getVehicleTypeDetailId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy chi tiết loại xe với ID: "
                                                                + request.getVehicleTypeDetailId()));
                newVehicle.setVehicleTypeDetail(vehicleTypeDetail);

                // Lấy đối tượng DaiLy theo ID
                Agency agency = agencyRepository.findById(request.getAgencyId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy đại lý với ID: " + request.getAgencyId()));
                newVehicle.setAgency(agency);
                vehicleRepository.save(newVehicle);
                VehicleResponseDTO response = new VehicleResponseDTO(newVehicle);
                response.setVehicleTypeDetail(new VehicleTypeDetailResponseDTO(vehicleTypeDetail));
                return response;

        }

       @Transactional
        public Page<VehicleSummaryDTO> getAllVehicle(Pageable pageable) {
        Page<Vehicle> vehicles = vehicleRepository.findAll(pageable);
        return vehicles.map(vehicle -> new VehicleSummaryDTO(vehicle));
        }


        // @Cacheable(value = "vehicle", key = "#vehicleId")
        @Transactional
        public VehicleResponseDTO getByIdVehicle(Integer vehicleId) {
                Vehicle vehicle = vehicleRepository.findById(vehicleId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy xe với ID: " + vehicleId));
                VehicleResponseDTO response = new VehicleResponseDTO(vehicle);
                response.setVehicleTypeDetail(new VehicleTypeDetailResponseDTO(vehicle.getVehicleTypeDetail()));
                response.setAgency(new AgencyResponseDTO(vehicle.getAgency()));
                return response;
        }

        // @CachePut(value = "vehicle", key = "#vehicleId")
        @Transactional
        public VehicleResponseDTO updateVehicle(Integer vehicleId, VehicleRequestDTO request) {
                Vehicle updateVehicle = vehicleRepository.findById(vehicleId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy xe với ID: " + vehicleId));
                updateVehicle.setChassicNumber(request.getChassisNumber());
                updateVehicle.setMachineNumber(request.getMachineNumber());
                updateVehicle.setStatus(request.getStatus());
                updateVehicle.setVehicleCondition(request.getVehicleCondition());

                // Lấy đối tượng vehicleTypeDetail theo ID
                VehicleTypeDetail vehicleTypeDetail = vehicleTypeDetailRepository
                                .findById(request.getVehicleTypeDetailId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy chi tiết loại xe với ID: "
                                                                + request.getVehicleTypeDetailId()));
                updateVehicle.setVehicleTypeDetail(vehicleTypeDetail);
                // Lấy đối tượng DaiLy theo ID
                Agency agency = agencyRepository.findById(request.getAgencyId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy đại lý với ID: " + request.getAgencyId()));
                updateVehicle.setAgency(agency);
                vehicleRepository.save(updateVehicle);
                VehicleResponseDTO response = new VehicleResponseDTO(updateVehicle);
                response.setVehicleTypeDetail(new VehicleTypeDetailResponseDTO(vehicleTypeDetail));
                return response;
        }

        // @CacheEvict(value = "vehicle", key = "#vehicleId")
        @Transactional
        public VehicleResponseDTO deleteVehicle(Integer vehicleId) {
                Vehicle vehicle = vehicleRepository.findById(vehicleId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy xe với ID: " + vehicleId));
                VehicleResponseDTO response = new VehicleResponseDTO(vehicle);
                response.setVehicleTypeDetail(new VehicleTypeDetailResponseDTO(vehicle.getVehicleTypeDetail()));
                vehicleRepository.deleteById(vehicleId);
                return response;
        }
}
