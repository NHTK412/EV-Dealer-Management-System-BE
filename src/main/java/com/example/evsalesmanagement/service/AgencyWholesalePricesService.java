package com.example.evsalesmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evsalesmanagement.dto.AgencyWholesalePriceDTO;
import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.model.AgencyWholesalePrice;
import com.example.evsalesmanagement.model.VehicleTypeDetail;
import com.example.evsalesmanagement.repository.AgencyRepository;
import com.example.evsalesmanagement.repository.AgencyWholesalePriceRepository;
import com.example.evsalesmanagement.repository.VehicleTypeDetailRepository;

@Service
public class AgencyWholesalePricesService {
    
    @Autowired
    private AgencyWholesalePriceRepository agencyWholesalePriceRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private VehicleTypeDetailRepository vehicleTypeDetailRepository;

    @Transactional
    public AgencyWholesalePrice createAgencyWholesalePrice(AgencyWholesalePriceDTO request) {
        AgencyWholesalePrice price = new AgencyWholesalePrice();
        price.setWholesalePrice(request.getWholesalePrice());
        price.setMinimumQuantity(request.getMinimumQuantity());
        price.setStartDate(request.getStartDate());
        price.setEndDate(request.getEndDate());
        price.setStatus(request.getStatus());

        Agency agency = agencyRepository.findById(request.getAgencyId())
            .orElseThrow(() -> new RuntimeException("Không tìm thấy Agency với ID: " + request.getAgencyId()));
        price.setAgency(agency);

        VehicleTypeDetail vehicleTypeDetail = vehicleTypeDetailRepository.findById(request.getVehicleTypeDetailId())
            .orElseThrow(() -> new RuntimeException("Không tìm thấy VehicleTypeDetail với ID: " + request.getVehicleTypeDetailId()));
        price.setVehicleTypeDetail(vehicleTypeDetail);

        return agencyWholesalePriceRepository.save(price);
    }

    @Transactional
    public Page<AgencyWholesalePrice> getAllAgencyWholesalePrices(Pageable pageable) {
        return agencyWholesalePriceRepository.findAll(pageable);
    }

    public AgencyWholesalePrice getAgencyWholesalePriceById(Integer id) {
        return agencyWholesalePriceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy AgencyWholesalePrice với ID: " + id));
    }

    @Transactional
    public AgencyWholesalePrice updateAgencyWholesalePrice(Integer id, AgencyWholesalePriceDTO request) {
        AgencyWholesalePrice price = getAgencyWholesalePriceById(id);
        
        price.setWholesalePrice(request.getWholesalePrice());
        price.setMinimumQuantity(request.getMinimumQuantity());
        price.setStartDate(request.getStartDate());
        price.setEndDate(request.getEndDate());
        price.setStatus(request.getStatus());

        if (request.getAgencyId() != null) {
            Agency agency = agencyRepository.findById(request.getAgencyId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Agency với ID: " + request.getAgencyId()));
            price.setAgency(agency);
        }

        if (request.getVehicleTypeDetailId() != null) {
            VehicleTypeDetail vehicleTypeDetail = vehicleTypeDetailRepository.findById(request.getVehicleTypeDetailId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy VehicleTypeDetail với ID: " + request.getVehicleTypeDetailId()));
            price.setVehicleTypeDetail(vehicleTypeDetail);
        }

        return agencyWholesalePriceRepository.save(price);
    }

    public void deleteAgencyWholesalePrice(Integer id) {
        agencyWholesalePriceRepository.deleteById(id);
    }
}