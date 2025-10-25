package com.example.evsalesmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.repository.AgencyRepository;
import com.example.evsalesmanagement.dto.AgencyRequest;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;

@Service
public class AgencyService {
    @Autowired
    private AgencyRepository agencyRepository;

    public Agency createAgency(AgencyRequest request) {
        Agency agency = new Agency();
        agency.setAgencyName(request.getAgencyName());
        agency.setAddress(request.getAddress());
        agency.setPhoneNumber(request.getPhoneNumber());
        agency.setEmail(request.getEmail());
        agency.setStatus(request.getStatus());
        return agencyRepository.save(agency);
    }

    public List<Agency> getAllAgency() {
        return agencyRepository.findAll();
    }

    public Agency getAgencyById(Integer agencyId) {
        return agencyRepository.findById(agencyId).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đại lý"));
    }

    public Agency updateAgency(Integer agencyId, AgencyRequest request) {
        Agency agency = getAgencyById(agencyId);
        agency.setAgencyName(request.getAgencyName());
        agency.setAddress(request.getAddress());
        agency.setPhoneNumber(request.getPhoneNumber());
        agency.setEmail(request.getEmail());
        agency.setStatus(request.getStatus());
        return agencyRepository.save(agency);
    }
}
