package com.example.evsalesmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.repository.AgencyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.example.evsalesmanagement.dto.agency.AgencyRequestDTO;
import com.example.evsalesmanagement.dto.agency.AgencyResponseDTO;
import com.example.evsalesmanagement.dto.agency.AgencySummaryDTO;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;

@Service
public class AgencyService {
    @Autowired
    private AgencyRepository agencyRepository;

    // @Cacheable(value = "agency-all", key = "#pageable")
    public List<AgencySummaryDTO> getAllAgencies(Pageable pageable) {
        Page<Agency> agencies = agencyRepository.findAll(pageable);
        List<AgencySummaryDTO> summaryList = agencies.stream().map(agency -> {
            AgencySummaryDTO agencySummaryDTO = new AgencySummaryDTO();
            agencySummaryDTO.setAgencyId(agency.getAgencyId());
            agencySummaryDTO.setAgencyName(agency.getAgencyName());
            agencySummaryDTO.setStatus(agency.getStatus().getDisplayName());
            return agencySummaryDTO;
        }).toList();
        return summaryList;
    }

    @Cacheable(value = "agency", key = "#agencyId")
    @Transactional
    public AgencyResponseDTO getByIdAgency(Integer agencyId) {
        Agency agency = agencyRepository.findById(agencyId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Agency"));
        AgencyResponseDTO agencyResponseDTO = new AgencyResponseDTO();
        agencyResponseDTO.setAgencyId(agency.getAgencyId());
        agencyResponseDTO.setAgencyName(agency.getAgencyName());
        agencyResponseDTO.setAddress(agency.getAddress());
        agencyResponseDTO.setPhoneNumber(agency.getPhoneNumber());
        agencyResponseDTO.setEmail(agency.getEmail());
        agencyResponseDTO.setStatus(agency.getStatus());
        agencyResponseDTO.setType(agency.getType());
        return agencyResponseDTO;
    }

    @Transactional
    public AgencyResponseDTO createAgency(AgencyRequestDTO agencyRequestDTO) {
        Agency agency = new Agency();
        agency.setAgencyName(agencyRequestDTO.getAgencyName());
        agency.setAddress(agencyRequestDTO.getAddress());
        agency.setPhoneNumber(agencyRequestDTO.getPhoneNumber());
        agency.setEmail(agencyRequestDTO.getEmail());
        agency.setStatus(agencyRequestDTO.getStatus());
        agency.setType(agencyRequestDTO.getType());
        Agency savedAgency = agencyRepository.save(agency);
        AgencyResponseDTO agencyResponseDTO = new AgencyResponseDTO();
        agencyResponseDTO.setAgencyId(savedAgency.getAgencyId());
        agencyResponseDTO.setAgencyName(savedAgency.getAgencyName());
        agencyResponseDTO.setAddress(savedAgency.getAddress());
        agencyResponseDTO.setPhoneNumber(savedAgency.getPhoneNumber());
        agencyResponseDTO.setEmail(savedAgency.getEmail());
        agencyResponseDTO.setStatus(savedAgency.getStatus());
        agencyResponseDTO.setType(agency.getType());

        return agencyResponseDTO;
    }

    @CachePut(value = "agency", key = "#agencyId")
    @Transactional
    public AgencyResponseDTO updateAgency(Integer agencyId, AgencyRequestDTO agencyRequestDTO) {
        Agency agency = agencyRepository.findById(agencyId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Agency"));
        agency.setAgencyName(agencyRequestDTO.getAgencyName());
        agency.setAddress(agencyRequestDTO.getAddress());
        agency.setPhoneNumber(agencyRequestDTO.getPhoneNumber());
        agency.setEmail(agencyRequestDTO.getEmail());
        agency.setStatus(agencyRequestDTO.getStatus());
        Agency updatedAgency = agencyRepository.save(agency);
        AgencyResponseDTO agencyResponseDTO = new AgencyResponseDTO();
        agencyResponseDTO.setAgencyId(updatedAgency.getAgencyId());
        agencyResponseDTO.setAgencyName(updatedAgency.getAgencyName());
        agencyResponseDTO.setAddress(updatedAgency.getAddress());
        agencyResponseDTO.setPhoneNumber(updatedAgency.getPhoneNumber());
        agencyResponseDTO.setEmail(updatedAgency.getEmail());
        agencyResponseDTO.setStatus(updatedAgency.getStatus());
        agencyResponseDTO.setType(agency.getType());

        return agencyResponseDTO;
    }

    @CacheEvict(value = "agency", key = "#agencyId")
    @Transactional
    public AgencyResponseDTO deleteAgency(Integer agencyId) {
        Agency agency = agencyRepository.findById(agencyId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Agency"));
        agencyRepository.delete(agency);
        AgencyResponseDTO agencyResponseDTO = new AgencyResponseDTO();
        agencyResponseDTO.setAgencyId(agency.getAgencyId());
        agencyResponseDTO.setAgencyName(agency.getAgencyName());
        agencyResponseDTO.setAddress(agency.getAddress());
        agencyResponseDTO.setPhoneNumber(agency.getPhoneNumber());
        agencyResponseDTO.setEmail(agency.getEmail());
        agencyResponseDTO.setStatus(agency.getStatus());
        agencyResponseDTO.setType(agency.getType());

        return agencyResponseDTO;
    }

}
