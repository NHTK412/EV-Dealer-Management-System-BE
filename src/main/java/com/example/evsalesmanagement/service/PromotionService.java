package com.example.evsalesmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.AgencyDTO;
import com.example.evsalesmanagement.dto.promotion.PromotionRequestDTO;
import com.example.evsalesmanagement.dto.promotion.PromotionResponseDTO;
import com.example.evsalesmanagement.dto.promotion.PromotionSummaryDTO;
import com.example.evsalesmanagement.model.Promotion;
import com.example.evsalesmanagement.repository.VehicleTypeDetailRepository;

import jakarta.transaction.Transactional;

import com.example.evsalesmanagement.repository.AgencyRepository;
import com.example.evsalesmanagement.repository.PromotionRepository;
import com.example.evsalesmanagement.dto.vehicleTypeDetailDTO.VehicleTypeDetailResponseDTO;

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
        @Transactional
        public PromotionResponseDTO getByIdPromotion(Integer promotionId) {

                Promotion promotion = promotionRepository.findById(promotionId)
                                .orElseThrow(() -> new RuntimeException("Không tìm thấy KhuyenMai"));

                PromotionResponseDTO promotionResponseDTO = new PromotionResponseDTO(promotion);

                promotionResponseDTO.setVehicleTypeDetails(
                                promotion.getVehicleDetails()
                                                .stream()
                                                .map(vehicleTypeDetail -> new VehicleTypeDetailResponseDTO(vehicleTypeDetail))
                                                .toList());
                promotionResponseDTO.setAgencies(
                                promotion.getAgencies()
                                                .stream()
                                                .map(agency -> new AgencyDTO(agency))
                                                .toList());
                // KhuyenMaiChiTietDTO khuyenMaiChiTiet = new KhuyenMaiChiTietDTO(khuyenMai);
                // khuyenMaiChiTiet.setChiTietLoaiXes(
                // khuyenMai.getChiTietLoaiXes()
                // .stream()
                // .map(ctlx -> new ChiTietLoaiXeDTO(ctlx))
                // .toList());
                // khuyenMaiChiTiet.setDaiLys(
                // khuyenMai.getDaiLys()
                // .stream()
                // .map(daiLy -> new DaiLyDTO(daiLy))
                // .toList());

                return promotionResponseDTO;
        }

        @Transactional
        public PromotionResponseDTO createPromotion(PromotionRequestDTO promotion) {

                Promotion newPromotion = new Promotion();
                newPromotion.setPromotionName(promotion.getPromotionName());
                newPromotion.setPromotionType(promotion.getPromotionType());
                newPromotion.setPromotionValue(promotion.getPromotionValue());
                newPromotion.setCriteria(promotion.getCriteria());
                newPromotion.setDiscountAmount(promotion.getDiscountAmount());
                newPromotion.setDiscountPercent(promotion.getDiscountPercent());
                newPromotion.setStartDate(promotion.getStartDate());
                newPromotion.setEndDate(promotion.getEndDate());

                // Mặc định là đang hoạt động
                newPromotion.setStatus("Hoạt động");

                newPromotion.setVehicleDetails(
                                vehicleTypeDetailRepository.findAllById(promotion.getVehicleTypeDetailsId()));
                newPromotion.setAgencies(agencyRepository.findAllById(promotion.getAgencysId()));
                promotionRepository.save(newPromotion);
                PromotionResponseDTO promotionResponseDTO = new PromotionResponseDTO(newPromotion);

                promotionResponseDTO.setVehicleTypeDetails(
                                newPromotion.getVehicleDetails()
                                                .stream()
                                                .map(vehicleTypeDetail -> new VehicleTypeDetailResponseDTO(vehicleTypeDetail))
                                                .toList());
                promotionResponseDTO.setAgencies(
                                newPromotion.getAgencies()
                                                .stream()
                                                .map(agency -> new AgencyDTO(agency))
                                                .toList());

                return promotionResponseDTO;

                // return
        }

        @Transactional
        public PromotionResponseDTO deletePromotion(Integer promotionId) {

                Promotion promotion = promotionRepository.findById(promotionId)
                                .orElseThrow(() -> new RuntimeException("Không tìm thấy KhuyenMai"));

                PromotionResponseDTO promotionResponseDTO = new PromotionResponseDTO(promotion);

                promotionResponseDTO.setVehicleTypeDetails(
                                promotion.getVehicleDetails()
                                                .stream()
                                                .map(vehicleTypeDetail -> new VehicleTypeDetailResponseDTO(vehicleTypeDetail))
                                                .toList());

                promotionResponseDTO.setAgencies(
                                promotion.getAgencies()
                                                .stream()
                                                .map(agency -> new AgencyDTO(agency))
                                                .toList());

                promotionRepository.deleteById(promotionId);

                return promotionResponseDTO;
        }

        @Transactional
        public PromotionResponseDTO updatePromotion(Integer promotionId, PromotionRequestDTO promotion) {

                Promotion updatePromotion = promotionRepository.findById(promotionId)
                                .orElseThrow(() -> new RuntimeException("Không tìm thấy KhuyenMai"));

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
                updatePromotion.setAgencies(agencyRepository.findAllById(promotion.getAgencysId()));

                PromotionResponseDTO promotionResponseDTO = new PromotionResponseDTO(updatePromotion);
                promotionResponseDTO.setVehicleTypeDetails(
                                updatePromotion.getVehicleDetails()
                                                .stream()
                                                .map(vehicleTypeDetails -> new VehicleTypeDetailResponseDTO(vehicleTypeDetails))
                                                .toList());

                promotionResponseDTO.setAgencies(
                                updatePromotion.getAgencies()
                                                .stream()
                                                .map(agency -> new AgencyDTO(agency))
                                                .toList());

                // return promotionRepository.save(updatePromotion);
                return promotionResponseDTO;
        }

}