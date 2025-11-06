package com.example.evsalesmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.repository.AgencyRepository;
import com.example.evsalesmanagement.dto.agencyDTO.AgencyRequestDTO;
import com.example.evsalesmanagement.dto.agencyDTO.AgencyResponseDTO;
import com.example.evsalesmanagement.dto.agencyDTO.AgencySummaryDTO;
import com.example.evsalesmanagement.utils.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.example.evsalesmanagement.exception.ResourceNotFoundException;

@Service
public class AgencyService {
    public ApiResponse<List<AgencySummaryDTO>> getAllAgencies(Pageable pageable) {
        Page<Agency> agencies = agencyRepository.findAll(pageable);
        List<AgencySummaryDTO> summaryList = agencies.stream().map(agency -> {
            AgencySummaryDTO dto = new AgencySummaryDTO();
            dto.setAgencyId(agency.getAgencyId());
            dto.setAgencyName(agency.getAgencyName());
            dto.setStatus(agency.getStatus());
            return dto;
        }).toList();
        return new ApiResponse<>(true, null, summaryList);
    }

    @Transactional
    public ApiResponse<AgencyResponseDTO> getByIdAgency(Integer agencyId) {
        Agency agency = agencyRepository.findById(agencyId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Agency"));
        AgencyResponseDTO dto = new AgencyResponseDTO();
        dto.setAgencyId(agency.getAgencyId());
        dto.setAgencyName(agency.getAgencyName());
        dto.setAddress(agency.getAddress());
        dto.setPhoneNumber(agency.getPhoneNumber());
        dto.setEmail(agency.getEmail());
        dto.setStatus(agency.getStatus());
        return new ApiResponse<>(true, null, dto);
    }

    @Transactional
    public ApiResponse<AgencyResponseDTO> createAgency(AgencyRequestDTO agencyRequestDTO) {
        Agency agency = new Agency();
        agency.setAgencyName(agencyRequestDTO.getAgencyName());
        agency.setAddress(agencyRequestDTO.getAddress());
        agency.setPhoneNumber(agencyRequestDTO.getPhoneNumber());
        agency.setEmail(agencyRequestDTO.getEmail());
        agency.setStatus(agencyRequestDTO.getStatus());
        Agency savedAgency = agencyRepository.save(agency);
        AgencyResponseDTO dto = new AgencyResponseDTO();
        dto.setAgencyId(savedAgency.getAgencyId());
        dto.setAgencyName(savedAgency.getAgencyName());
        dto.setAddress(savedAgency.getAddress());
        dto.setPhoneNumber(savedAgency.getPhoneNumber());
        dto.setEmail(savedAgency.getEmail());
        dto.setStatus(savedAgency.getStatus());
        return new ApiResponse<>(true, null, dto);
    }

    @Transactional
    public ApiResponse<AgencyResponseDTO> updateAgency(Integer agencyId, AgencyRequestDTO agencyRequestDTO) {
        Agency agency = agencyRepository.findById(agencyId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Agency"));
        agency.setAgencyName(agencyRequestDTO.getAgencyName());
        agency.setAddress(agencyRequestDTO.getAddress());
        agency.setPhoneNumber(agencyRequestDTO.getPhoneNumber());
        agency.setEmail(agencyRequestDTO.getEmail());
        agency.setStatus(agencyRequestDTO.getStatus());
        Agency updatedAgency = agencyRepository.save(agency);
        AgencyResponseDTO dto = new AgencyResponseDTO();
        dto.setAgencyId(updatedAgency.getAgencyId());
        dto.setAgencyName(updatedAgency.getAgencyName());
        dto.setAddress(updatedAgency.getAddress());
        dto.setPhoneNumber(updatedAgency.getPhoneNumber());
        dto.setEmail(updatedAgency.getEmail());
        dto.setStatus(updatedAgency.getStatus());
        return new ApiResponse<>(true, null, dto);
    }

    @Transactional
    public ApiResponse<AgencyResponseDTO> deleteAgency(Integer agencyId) {
        Agency agency = agencyRepository.findById(agencyId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Agency"));
        agencyRepository.delete(agency);
        AgencyResponseDTO dto = new AgencyResponseDTO();
        dto.setAgencyId(agency.getAgencyId());
        dto.setAgencyName(agency.getAgencyName());
        dto.setAddress(agency.getAddress());
        dto.setPhoneNumber(agency.getPhoneNumber());
        dto.setEmail(agency.getEmail());
        dto.setStatus(agency.getStatus());
        return new ApiResponse<>(true, null, dto);
    }
    @Autowired
    private AgencyRepository agencyRepository;

    
}
