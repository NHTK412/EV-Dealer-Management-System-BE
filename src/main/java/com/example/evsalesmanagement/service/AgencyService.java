package com.example.evsalesmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.repository.AgencyRepository;
import com.example.evsalesmanagement.dto.DaiLyRequest;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;

@Service
public class AgencyService {
    @Autowired
    private AgencyRepository agencyRepository;

    public Agency createDaiLy(DaiLyRequest request) {
        Agency agency = new Agency();
        agency.setAgencyName(request.getTenDaiLy());
        agency.setAddress(request.getDiaChi());
        agency.setPhoneNumber(request.getSoDienThoai());
        agency.setEmail(request.getEmail());
        agency.setStatus(request.getTrangThai());
        return agencyRepository.save(agency);
    }

    public List<Agency> getAllDaiLy() {
        return agencyRepository.findAll();
    }

    public Agency getDaiLyById(Integer maDaiLy) {
        return agencyRepository.findById(maDaiLy)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đại lý"));
    }

    public Agency updateDaiLy(Integer maDaiLy, DaiLyRequest request) {
        Agency agency = getDaiLyById(maDaiLy);

        agency.setAgencyName(request.getTenDaiLy());
        agency.setAddress(request.getDiaChi());
        agency.setPhoneNumber(request.getSoDienThoai());
        agency.setEmail(request.getEmail());
        agency.setStatus(request.getTrangThai());
        return agencyRepository.save(agency);
    }
}
