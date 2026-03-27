package com.example.evsalesmanagement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.promotion.PromotionRequestDTO;
import com.example.evsalesmanagement.dto.promotion.PromotionResponseDTO;
import com.example.evsalesmanagement.dto.promotion.PromotionSummaryDTO;
import com.example.evsalesmanagement.dto.vehicletypedetail.VehicleTypeDetailResponseDTO;
import com.example.evsalesmanagement.enums.PromotionStatusEnum;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Promotion;
import com.example.evsalesmanagement.repository.VehicleTypeDetailRepository;

import jakarta.transaction.Transactional;

import com.example.evsalesmanagement.repository.AgencyRepository;
import com.example.evsalesmanagement.repository.PromotionRepository;

@Service
public class PromotionService {

        @Autowired
        PromotionRepository promotionRepository;

        @Autowired
        AgencyRepository agencyRepository;

        @Autowired
        VehicleTypeDetailRepository vehicleTypeDetailRepository;

        public List<PromotionSummaryDTO> getAllPromotions(Pageable pageable) {
                Page<Promotion> promotions = promotionRepository.findAll(pageable);
                return promotions.stream().map(km -> new PromotionSummaryDTO(km)).toList();
        }

        // sử dụng trasactional để duy trình session đến hết hàm
        // @Cacheable(value = "promotion", key = "#promotionId")
        @Transactional
        public PromotionResponseDTO getByIdPromotion(Integer promotionId) {

                Promotion promotion = promotionRepository.findById(promotionId)
                                .orElseThrow(() -> new ResourceNotFoundException("Promotion not found"));

                PromotionResponseDTO promotionResponseDTO = new PromotionResponseDTO(promotion);

                promotionResponseDTO.setVehicleTypeDetails(
                                promotion.getVehicleDetails()
                                                .stream()
                                                .map(vehicleTypeDetail -> new VehicleTypeDetailResponseDTO(
                                                                vehicleTypeDetail))
                                                .toList());

                return promotionResponseDTO;
        }

        @Transactional
        public PromotionResponseDTO createPromotion(Integer agencyId, PromotionRequestDTO promotionRequestDTO) {

                Promotion newPromotion = new Promotion();
                newPromotion.setPromotionName(promotionRequestDTO.getPromotionName());
                newPromotion.setPromotionType(promotionRequestDTO.getPromotionType());
                newPromotion.setPromotionValue(promotionRequestDTO.getPromotionValue());
                newPromotion.setCriteria(promotionRequestDTO.getCriteria());
                newPromotion.setDiscountAmount(promotionRequestDTO.getDiscountAmount());
                newPromotion.setDiscountPercent(promotionRequestDTO.getDiscountPercent());
                newPromotion.setStartDate(promotionRequestDTO.getStartDate());
                newPromotion.setEndDate(promotionRequestDTO.getEndDate());

                // Mặc định là đang hoạt động
                // newPromotion.setStatus("Hoạt động");
                // newPromotion.setStatus();
                LocalDateTime now = LocalDateTime.now();

                if (now.isBefore(promotionRequestDTO.getEndDate())
                                && now.isAfter(promotionRequestDTO.getStartDate())) {
                        newPromotion.setStatus(PromotionStatusEnum.ACTIVE);
                } else if (now.isBefore(promotionRequestDTO.getStartDate())) {
                        newPromotion.setStatus(PromotionStatusEnum.NOT_ACTIVE);
                } else {
                        newPromotion.setStatus(PromotionStatusEnum.INACTIVE);
                }

                newPromotion.setVehicleDetails(
                                vehicleTypeDetailRepository.findAllById(promotionRequestDTO.getVehicleTypeDetailsId()));

                newPromotion.setAgency(agencyRepository.findById(agencyId)
                                .orElseThrow(() -> new ResourceNotFoundException("Agency Not Found")));
                promotionRepository.save(newPromotion);
                PromotionResponseDTO promotionResponseDTO = new PromotionResponseDTO(newPromotion);

                promotionResponseDTO.setVehicleTypeDetails(
                                newPromotion.getVehicleDetails()
                                                .stream()
                                                .map(vehicleTypeDetail -> new VehicleTypeDetailResponseDTO(
                                                                vehicleTypeDetail))
                                                .toList());

                return promotionResponseDTO;
        }

        // @CacheEvict(value = "promotion", key = "#promotionId")
        @Transactional
        public PromotionResponseDTO deletePromotion(Integer promotionId) {

                Promotion promotion = promotionRepository.findById(promotionId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Promotion not found with ID: " + promotionId));

                PromotionResponseDTO promotionResponseDTO = new PromotionResponseDTO(promotion);

                promotionResponseDTO.setVehicleTypeDetails(
                                promotion.getVehicleDetails()
                                                .stream()
                                                .map(vehicleTypeDetail -> new VehicleTypeDetailResponseDTO(
                                                                vehicleTypeDetail))
                                                .toList());

                if (promotion.getStatus() != PromotionStatusEnum.INACTIVE) {
                        promotion.setStatus(PromotionStatusEnum.INACTIVE);
                        promotionRepository.save(promotion);
                }

                return promotionResponseDTO;
        }

        // @CachePut(value = "promotion", key = "#promotionId")
        @Transactional
        public PromotionResponseDTO updatePromotion(Integer agencyId, Integer promotionId,
                        PromotionRequestDTO promotion) {

                Promotion updatePromotion = promotionRepository.findById(promotionId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Promotion not found with ID: " + promotionId));

                updatePromotion.setPromotionName(promotion.getPromotionName());

                updatePromotion.setPromotionType(promotion.getPromotionType());

                updatePromotion.setPromotionValue(promotion.getPromotionValue());

                updatePromotion.setCriteria(promotion.getCriteria());

                updatePromotion.setDiscountAmount(promotion.getDiscountAmount());

                updatePromotion.setDiscountPercent(promotion.getDiscountPercent());

                updatePromotion.setStartDate(promotion.getStartDate());

                updatePromotion.setEndDate(promotion.getEndDate());

                // Mặc định là đang hoạt động
                updatePromotion.setStatus(promotion.getStatus());

                updatePromotion.setVehicleDetails(
                                vehicleTypeDetailRepository.findAllById(promotion.getVehicleTypeDetailsId()));
                updatePromotion.setAgency(agencyRepository.findById(agencyId)
                                .orElseThrow(() -> new ResourceNotFoundException("Agency Not Found")));

                PromotionResponseDTO promotionResponseDTO = new PromotionResponseDTO(updatePromotion);

                promotionResponseDTO.setVehicleTypeDetails(
                                updatePromotion.getVehicleDetails()
                                                .stream()
                                                .map(vehicleTypeDetails -> new VehicleTypeDetailResponseDTO(
                                                                vehicleTypeDetails))
                                                .toList());
                return promotionResponseDTO;
        }

}