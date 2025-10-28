package com.example.evsalesmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.PromotionRequestDTO;
import com.example.evsalesmanagement.model.Promotion;
import com.example.evsalesmanagement.repository.VehicleTypeDetailRepository;
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

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public Promotion getPromotionId(Integer promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy KhuyenMai"));

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

        return promotion;
    }

    public Promotion createPromotion(PromotionRequestDTO promotion) {

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

       newPromotion.setVehicleDetails(vehicleTypeDetailRepository.findAllById(promotion.getVehicleTypeDetailsId()));
       newPromotion.setAgencies(agencyRepository.findAllById(promotion.getAgencysId()));

        return promotionRepository.save(newPromotion);

        // return
    }

    public Promotion DeletePromotion(Integer promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy KhuyenMai"));

        promotion.getVehicleDetails().size();
        promotion.getAgencies().size();
        promotionRepository.deleteById(promotionId);
        return promotion;
    }

    public Promotion updatePromotion(Integer promotionId, PromotionRequestDTO promotion) {
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
        updatePromotion.setVehicleDetails(vehicleTypeDetailRepository.findAllById(promotion.getVehicleTypeDetailsId()));
        updatePromotion.setAgencies(agencyRepository.findAllById(promotion.getAgencysId()));
        

        return promotionRepository.save(updatePromotion);   
        }

}
