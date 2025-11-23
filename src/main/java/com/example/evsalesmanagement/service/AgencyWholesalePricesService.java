package com.example.evsalesmanagement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.evsalesmanagement.dto.agency.AgencyResponseDTO;
import com.example.evsalesmanagement.dto.agencywholesaleprice.AgencyWholesalePriceRequestDTO;
import com.example.evsalesmanagement.dto.agencywholesaleprice.AgencyWholesalePriceResponseDTO;
import com.example.evsalesmanagement.dto.agencywholesaleprice.AgencyWholesalePriceSummaryDTO;
import com.example.evsalesmanagement.dto.vehicletypedetail.VehicleTypeDetailResponseDTO;
import com.example.evsalesmanagement.enums.AgencyWholesalePriceStatusEnum;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.model.AgencyWholesalePrice;
import com.example.evsalesmanagement.model.VehicleTypeDetail;
import com.example.evsalesmanagement.repository.AgencyRepository;
import com.example.evsalesmanagement.repository.AgencyWholesalePriceRepository;
import com.example.evsalesmanagement.repository.VehicleTypeDetailRepository;

@Service
public class AgencyWholesalePricesService {

        // private final AgencyWholesalePriceController agencyWholesalePriceController;

        @Autowired
        private AgencyWholesalePriceRepository agencyWholesalePriceRepository;

        @Autowired
        private AgencyRepository agencyRepository;

        @Autowired
        private VehicleTypeDetailRepository vehicleTypeDetailRepository;

        // AgencyWholesalePricesService(AgencyWholesalePriceController
        // agencyWholesalePriceController) {
        // this.agencyWholesalePriceController = agencyWholesalePriceController;
        // }

        @Transactional
        public AgencyWholesalePriceResponseDTO createAgencyWholesalePrice(
                        AgencyWholesalePriceRequestDTO agencyWholesalePriceDTO) {
                AgencyWholesalePrice newPrice = new AgencyWholesalePrice();
                newPrice.setWholesalePrice(agencyWholesalePriceDTO.getWholesalePrice());
                newPrice.setMinimumQuantity(agencyWholesalePriceDTO.getMinimumQuantity());
                newPrice.setStartDate(agencyWholesalePriceDTO.getStartDate());
                newPrice.setEndDate(agencyWholesalePriceDTO.getEndDate());

                LocalDateTime now = LocalDateTime.now();

                if (now.isBefore(agencyWholesalePriceDTO.getEndDate())
                                && now.isAfter(agencyWholesalePriceDTO.getStartDate())) {
                        newPrice.setStatus(AgencyWholesalePriceStatusEnum.ACTIVE);
                } else if (now.isBefore(agencyWholesalePriceDTO.getStartDate())) {
                        newPrice.setStatus(AgencyWholesalePriceStatusEnum.NOT_ACTIVE);
                } else {
                        newPrice.setStatus(AgencyWholesalePriceStatusEnum.INACTIVE);
                }

                Agency agency = agencyRepository.findById(agencyWholesalePriceDTO.getAgencyId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy Agency với ID: "
                                                                + agencyWholesalePriceDTO.getAgencyId()));
                newPrice.setAgency(agency);
                VehicleTypeDetail vehicleTypeDetail = vehicleTypeDetailRepository
                                .findById(agencyWholesalePriceDTO.getVehicleTypeDetailId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy VehicleTypeDetail với ID: "
                                                                + agencyWholesalePriceDTO.getVehicleTypeDetailId()));
                newPrice.setVehicleTypeDetail(vehicleTypeDetail);

                agencyWholesalePriceRepository.save(newPrice);
                AgencyWholesalePriceResponseDTO response = new AgencyWholesalePriceResponseDTO(newPrice);
                response.setAgency(new AgencyResponseDTO(agency));
                response.setVehicleTypeDetail(new VehicleTypeDetailResponseDTO(vehicleTypeDetail));
                return response;

        }

        // @Cacheable(value = "agency-wholesale-price-all", key = "#pageable")
        @Transactional
        public List<AgencyWholesalePriceSummaryDTO> getAllAgencyWholesalePrices(Pageable pageable) {
                Page<AgencyWholesalePrice> agencyWholesalePrices = agencyWholesalePriceRepository.findAll(pageable);
                return agencyWholesalePrices.stream().map(gs -> new AgencyWholesalePriceSummaryDTO(gs)).toList();
        }

        @Cacheable(value = "agency-wholesale-price", key = "#agencyWholesalePriceId")
        public AgencyWholesalePriceResponseDTO getByIdAgencyWholesalePrice(Integer agencyWholesalePriceId) {
                AgencyWholesalePrice price = agencyWholesalePriceRepository.findById(agencyWholesalePriceId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy AgencyWholesalePrice với ID: "
                                                                + agencyWholesalePriceId));
                AgencyWholesalePriceResponseDTO agencyWholesalePriceResponseDTO = new AgencyWholesalePriceResponseDTO(
                                price);
                agencyWholesalePriceResponseDTO.setAgency(new AgencyResponseDTO(price.getAgency()));
                agencyWholesalePriceResponseDTO
                                .setVehicleTypeDetail(new VehicleTypeDetailResponseDTO(price.getVehicleTypeDetail()));
                return agencyWholesalePriceResponseDTO;
        }

        @CachePut(value = "agency-wholesale-price", key = "#agencyWholesalePriceId")
        @Transactional
        public AgencyWholesalePriceResponseDTO updateAgencyWholesalePrice(Integer agencyWholesalePriceId,
                        AgencyWholesalePriceRequestDTO agencyWholesalePrice) {
                AgencyWholesalePrice updatePrice = agencyWholesalePriceRepository.findById(agencyWholesalePriceId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy AgencyWholesalePrice với ID: "
                                                                + agencyWholesalePriceId));
                updatePrice.setWholesalePrice(agencyWholesalePrice.getWholesalePrice());
                updatePrice.setMinimumQuantity(agencyWholesalePrice.getMinimumQuantity());
                updatePrice.setStartDate(agencyWholesalePrice.getStartDate());
                updatePrice.setEndDate(agencyWholesalePrice.getEndDate());
                updatePrice.setStatus(agencyWholesalePrice.getStatus());

                Agency agency = agencyRepository.findById(agencyWholesalePrice.getAgencyId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy Agency với ID: " + agencyWholesalePrice.getAgencyId()));
                updatePrice.setAgency(agency);
                VehicleTypeDetail vehicleTypeDetail = vehicleTypeDetailRepository
                                .findById(agencyWholesalePrice.getVehicleTypeDetailId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy VehicleTypeDetail với ID: "
                                                                + agencyWholesalePrice.getVehicleTypeDetailId()));
                updatePrice.setVehicleTypeDetail(vehicleTypeDetail);

                agencyWholesalePriceRepository.save(updatePrice);
                AgencyWholesalePriceResponseDTO agencyWholesalePriceResponseDTO = new AgencyWholesalePriceResponseDTO(
                                updatePrice);
                agencyWholesalePriceResponseDTO.setAgency(new AgencyResponseDTO(agency));
                agencyWholesalePriceResponseDTO
                                .setVehicleTypeDetail(new VehicleTypeDetailResponseDTO(vehicleTypeDetail));
                return agencyWholesalePriceResponseDTO;

        }

        @CacheEvict(value = "agency-wholesale-price", key = "#agencyWholesalePriceId")
        @Transactional
        public AgencyWholesalePriceResponseDTO deleteAgencyWholesalePrice(Integer agencyWholesalePriceId) {
                AgencyWholesalePrice price = agencyWholesalePriceRepository.findById(agencyWholesalePriceId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy AgencyWholesalePrice với ID: "
                                                                + agencyWholesalePriceId));

                AgencyWholesalePriceResponseDTO agencyWholesalePriceResponseDTO = new AgencyWholesalePriceResponseDTO(
                                price);
                agencyWholesalePriceResponseDTO.setAgency(new AgencyResponseDTO(price.getAgency()));
                agencyWholesalePriceResponseDTO
                                .setVehicleTypeDetail(new VehicleTypeDetailResponseDTO(price.getVehicleTypeDetail()));
                agencyWholesalePriceRepository.deleteById(agencyWholesalePriceId);
                return agencyWholesalePriceResponseDTO;
        }
}