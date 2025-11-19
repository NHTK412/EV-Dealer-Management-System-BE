package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.utils.ApiResponse;
import com.example.evsalesmanagement.dto.vehicletypedetail.VehicleTypeDetailRequestDTO;
import com.example.evsalesmanagement.dto.vehicletypedetail.VehicleTypeDetailResponseDTO;
import com.example.evsalesmanagement.dto.vehicletypedetail.VehicleTypeDetailSummaryDTO;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.VehicleType;
import com.example.evsalesmanagement.model.VehicleTypeDetail;
import com.example.evsalesmanagement.repository.VehicleTypeDetailRepository;
import com.example.evsalesmanagement.repository.VehicleTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class VehicleTypeDetailService {

    @Autowired
    private VehicleTypeDetailRepository vehicleTypeDetailRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Transactional
    public ApiResponse<org.springframework.data.domain.Page<VehicleTypeDetailSummaryDTO>> getAllVehicleTypeDetails(
            Pageable pageable, Integer vehicleTypeId) {

        Page<VehicleTypeDetail> page = (vehicleTypeId != null)
                ? vehicleTypeDetailRepository.findByVehicleType_VehicleTypeId(vehicleTypeId,
                        pageable)
                : vehicleTypeDetailRepository.findAll(pageable);

        Page<VehicleTypeDetailSummaryDTO> summaryPage = page.map(entity -> {
            VehicleTypeDetailSummaryDTO dto = new VehicleTypeDetailSummaryDTO();
            dto.setVehicleTypeDetailId(entity.getVehicleTypeDetailId());
            dto.setVehicleImage(entity.getVehicleImage());
            dto.setVersion(entity.getVersion());
            return dto;
        });
        return new ApiResponse<>(true, null, summaryPage);
    }

    @Transactional
    public ApiResponse<VehicleTypeDetailResponseDTO> getById(Integer vehicleTypeDetailId) {
        VehicleTypeDetail entity = vehicleTypeDetailRepository.findById(vehicleTypeDetailId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy chi tiết loại xe với ID: " + vehicleTypeDetailId));
        VehicleTypeDetailResponseDTO dto = new VehicleTypeDetailResponseDTO();
        dto.setVehicleTypeDetailId(entity.getVehicleTypeDetailId());
        dto.setVehicleImage(entity.getVehicleImage());
        dto.setConfiguration(entity.getConfiguration());
        dto.setColor(entity.getColor());
        dto.setVersion(entity.getVersion());
        dto.setFeatures(entity.getFeatures());
        dto.setPrice(entity.getPrice());
        dto.setVehicleTypeId(entity.getVehicleType() != null ? entity.getVehicleType().getVehicleTypeId() : null);
        return new ApiResponse<>(true, null, dto);
    }

    @Transactional
    public ApiResponse<VehicleTypeDetailResponseDTO> create(VehicleTypeDetailRequestDTO requestDTO) {
        VehicleTypeDetail entity = new VehicleTypeDetail();
        entity.setVehicleImage(requestDTO.getVehicleImage());
        entity.setConfiguration(requestDTO.getConfiguration());
        entity.setColor(requestDTO.getColor());
        entity.setVersion(requestDTO.getVersion());
        entity.setFeatures(requestDTO.getFeatures());
        entity.setPrice(requestDTO.getPrice());
        VehicleType vehicleType = vehicleTypeRepository.findById(requestDTO.getVehicleTypeId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy loại xe với ID: " + requestDTO.getVehicleTypeId()));
        entity.setVehicleType(vehicleType);
        VehicleTypeDetail saved = vehicleTypeDetailRepository.save(entity);
        VehicleTypeDetailResponseDTO dto = new VehicleTypeDetailResponseDTO();
        dto.setVehicleTypeDetailId(saved.getVehicleTypeDetailId());
        dto.setVehicleImage(saved.getVehicleImage());
        dto.setConfiguration(saved.getConfiguration());
        dto.setColor(saved.getColor());
        dto.setVersion(saved.getVersion());
        dto.setFeatures(saved.getFeatures());
        dto.setPrice(saved.getPrice());
        dto.setVehicleTypeId(saved.getVehicleType() != null ? saved.getVehicleType().getVehicleTypeId() : null);
        return new ApiResponse<>(true, null, dto);
    }

    @Transactional
    public ApiResponse<VehicleTypeDetailResponseDTO> update(Integer vehicleTypeDetailId,
            VehicleTypeDetailRequestDTO requestDTO) {
        VehicleTypeDetail entity = vehicleTypeDetailRepository.findById(vehicleTypeDetailId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy chi tiết loại xe với ID: " + vehicleTypeDetailId));
        entity.setVehicleImage(requestDTO.getVehicleImage());
        entity.setConfiguration(requestDTO.getConfiguration());
        entity.setColor(requestDTO.getColor());
        entity.setVersion(requestDTO.getVersion());
        entity.setFeatures(requestDTO.getFeatures());
        entity.setPrice(requestDTO.getPrice());
        VehicleType vehicleType = vehicleTypeRepository.findById(requestDTO.getVehicleTypeId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy loại xe với ID: " + requestDTO.getVehicleTypeId()));
        entity.setVehicleType(vehicleType);
        VehicleTypeDetail saved = vehicleTypeDetailRepository.save(entity);
        VehicleTypeDetailResponseDTO dto = new VehicleTypeDetailResponseDTO();
        dto.setVehicleTypeDetailId(saved.getVehicleTypeDetailId());
        dto.setVehicleImage(saved.getVehicleImage());
        dto.setConfiguration(saved.getConfiguration());
        dto.setColor(saved.getColor());
        dto.setVersion(saved.getVersion());
        dto.setFeatures(saved.getFeatures());
        dto.setPrice(saved.getPrice());
        dto.setVehicleTypeId(saved.getVehicleType() != null ? saved.getVehicleType().getVehicleTypeId() : null);
        return new ApiResponse<>(true, null, dto);
    }

    @Transactional
    public ApiResponse<VehicleTypeDetailResponseDTO> delete(Integer vehicleTypeDetailId) {
        VehicleTypeDetail entity = vehicleTypeDetailRepository.findById(vehicleTypeDetailId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy chi tiết loại xe với ID: " + vehicleTypeDetailId));
        vehicleTypeDetailRepository.delete(entity);
        VehicleTypeDetailResponseDTO dto = new VehicleTypeDetailResponseDTO();
        dto.setVehicleTypeDetailId(entity.getVehicleTypeDetailId());
        dto.setVehicleImage(entity.getVehicleImage());
        dto.setConfiguration(entity.getConfiguration());
        dto.setColor(entity.getColor());
        dto.setVersion(entity.getVersion());
        dto.setFeatures(entity.getFeatures());
        dto.setPrice(entity.getPrice());
        dto.setVehicleTypeId(entity.getVehicleType() != null ? entity.getVehicleType().getVehicleTypeId() : null);
        return new ApiResponse<>(true, null, dto);
    }
}