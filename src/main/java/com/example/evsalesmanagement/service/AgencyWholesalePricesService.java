package com.example.evsalesmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evsalesmanagement.dto.AgencyDTO;
import com.example.evsalesmanagement.dto.AgencyWholesalePriceDTO;
import com.example.evsalesmanagement.dto.VehicleTypeDetailDTO;
import com.example.evsalesmanagement.dto.AgencyWholesalePrice.AgencyWholesalePriceRequestDTO;
import com.example.evsalesmanagement.dto.AgencyWholesalePrice.AgencyWholesalePriceResponseDTO;
import com.example.evsalesmanagement.dto.AgencyWholesalePrice.AgencyWholesalePriceSummaryDTO;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
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
    public AgencyWholesalePriceResponseDTO createAgencyWholesalePrice(AgencyWholesalePriceDTO agencyWholesalePriceDTO) {
        AgencyWholesalePrice newPrice = new AgencyWholesalePrice();
        newPrice.setWholesalePrice(agencyWholesalePriceDTO.getWholesalePrice());
        newPrice.setMinimumQuantity(agencyWholesalePriceDTO.getMinimumQuantity());
        newPrice.setStartDate(agencyWholesalePriceDTO.getStartDate());
        newPrice.setEndDate(agencyWholesalePriceDTO.getEndDate());
        newPrice.setStatus("Hoat Dong");


        Agency agency = agencyRepository.findById(agencyWholesalePriceDTO.getAgencyId())
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Agency với ID: " + agencyWholesalePriceDTO.getAgencyId()));
        newPrice.setAgency(agency);    
        VehicleTypeDetail vehicleTypeDetail = vehicleTypeDetailRepository.findById(agencyWholesalePriceDTO.getVehicleTypeDetailId())
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy VehicleTypeDetail với ID: " + agencyWholesalePriceDTO.getVehicleTypeDetailId()));
        newPrice.setVehicleTypeDetail(vehicleTypeDetail);
        
        agencyWholesalePriceRepository.save(newPrice);
        AgencyWholesalePriceResponseDTO response = new AgencyWholesalePriceResponseDTO(newPrice);
        response.setAgency(new AgencyDTO(agency));
        response.setVehicleTypeDetail(new VehicleTypeDetailDTO(vehicleTypeDetail));
        return response;
        
    }

    @Transactional
    public List<AgencyWholesalePriceSummaryDTO> getAllAgencyWholesalePrices(Pageable pageable) {
        Page<AgencyWholesalePrice> agencyWholesalePrices = agencyWholesalePriceRepository.findAll(pageable);
        return agencyWholesalePrices.stream().map(gs -> new AgencyWholesalePriceSummaryDTO(gs)).toList();
    }

    public AgencyWholesalePriceResponseDTO getByIdAgencyWholesalePrice(Integer agencyWholesalePriceId) {
        AgencyWholesalePrice price = agencyWholesalePriceRepository.findById(agencyWholesalePriceId)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy AgencyWholesalePrice với ID: " + agencyWholesalePriceId));
        AgencyWholesalePriceResponseDTO agencyWholesalePriceResponseDTO = new AgencyWholesalePriceResponseDTO(price);
        agencyWholesalePriceResponseDTO.setAgency(new AgencyDTO(price.getAgency()));
        agencyWholesalePriceResponseDTO.setVehicleTypeDetail(new VehicleTypeDetailDTO(price.getVehicleTypeDetail()));
        return agencyWholesalePriceResponseDTO;
    }

    @Transactional
     public AgencyWholesalePriceResponseDTO updateAgencyWholesalePrice(Integer agencyWholesalePriceId, AgencyWholesalePriceRequestDTO agencyWholesalePrice) {
        AgencyWholesalePrice updatePrice = agencyWholesalePriceRepository.findById(agencyWholesalePriceId)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy AgencyWholesalePrice với ID: " + agencyWholesalePriceId));
        updatePrice.setWholesalePrice(agencyWholesalePrice.getWholesalePrice());
        updatePrice.setMinimumQuantity(agencyWholesalePrice.getMinimumQuantity());
        updatePrice.setStartDate(agencyWholesalePrice.getStartDate());
        updatePrice.setEndDate(agencyWholesalePrice.getEndDate());
        updatePrice.setStatus(agencyWholesalePrice.getStatus());


        Agency agency = agencyRepository.findById(agencyWholesalePrice.getAgencyId())
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Agency với ID: " + agencyWholesalePrice.getAgencyId()));
        updatePrice.setAgency(agency);    
        VehicleTypeDetail vehicleTypeDetail = vehicleTypeDetailRepository.findById(agencyWholesalePrice.getVehicleTypeDetailId())
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy VehicleTypeDetail với ID: " + agencyWholesalePrice.getVehicleTypeDetailId()));
        updatePrice.setVehicleTypeDetail(vehicleTypeDetail);
        
        agencyWholesalePriceRepository.save(updatePrice);
        AgencyWholesalePriceResponseDTO agencyWholesalePriceResponseDTO = new AgencyWholesalePriceResponseDTO(updatePrice);
        agencyWholesalePriceResponseDTO.setAgency(new AgencyDTO(agency));
        agencyWholesalePriceResponseDTO.setVehicleTypeDetail(new VehicleTypeDetailDTO(vehicleTypeDetail));
        return agencyWholesalePriceResponseDTO;
        
    }


    @Transactional
    public AgencyWholesalePriceResponseDTO deleteAgencyWholesalePrice(Integer agencyWholesalePriceId) {
        AgencyWholesalePrice price = agencyWholesalePriceRepository.findById(agencyWholesalePriceId)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy AgencyWholesalePrice với ID: " + agencyWholesalePriceId));
        
        AgencyWholesalePriceResponseDTO agencyWholesalePriceResponseDTO = new AgencyWholesalePriceResponseDTO(price);
        agencyWholesalePriceResponseDTO.setAgency(new AgencyDTO(price.getAgency()));
        agencyWholesalePriceResponseDTO.setVehicleTypeDetail(new VehicleTypeDetailDTO(price.getVehicleTypeDetail()));
        agencyWholesalePriceRepository.deleteById(agencyWholesalePriceId);
        return agencyWholesalePriceResponseDTO;
    }
}