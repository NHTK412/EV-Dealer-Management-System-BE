package com.example.evsalesmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.VehicleTypeDetailDTO;
import com.example.evsalesmanagement.dto.AgencyDTO;
import com.example.evsalesmanagement.dto.PromotionDetailDTO;
import com.example.evsalesmanagement.dto.PromotionSummaryDTO;
import com.example.evsalesmanagement.dto.PromotionRequestDTO;

import com.example.evsalesmanagement.model.Promotion;
import com.example.evsalesmanagement.service.PromotionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.evsalesmanagement.utils.ApiResponse;

@RestController
@RequestMapping("/promotion")
public class PromotionController {

        @Autowired
        private PromotionService promotionService;

        @GetMapping()
        public ResponseEntity<ApiResponse<List<PromotionSummaryDTO>>> getAllPromotions() {
                List<PromotionSummaryDTO> PromotionDTOs = promotionService.getAllPromotions().stream()
                                .map(km -> new PromotionSummaryDTO(km))
                                .toList();
                // return ResponseEntity.ok(khuyenMaiDTOs);

                return ResponseEntity.ok(new ApiResponse<List<PromotionSummaryDTO>>(true, null, PromotionDTOs));
        }

        @GetMapping("/{promotionId}")
        public ResponseEntity<ApiResponse<PromotionDetailDTO>> getByIdPromotion(@PathVariable String promotionId) {
                // return new String();

                Promotion promotion = promotionService.getByIdPromotion(Integer.parseInt(promotionId));

                PromotionDetailDTO promotionDetailDTO = new PromotionDetailDTO(promotion);
                promotionDetailDTO.setVehicleTypeDetails(
                                promotion.getVehicleDetails()
                                                .stream()
                                                .map(vehicleTypeDetail -> new VehicleTypeDetailDTO(vehicleTypeDetail))
                                                .toList());
                promotionDetailDTO.setAgencies(
                                promotion.getAgencies()
                                                .stream()
                                                .map(agency -> new AgencyDTO(agency))
                                                .toList());
                // return ResponseEntity.ok(khuyenMaiChiTietDTO);

                return ResponseEntity.ok(new ApiResponse<PromotionDetailDTO>(true, null, promotionDetailDTO));

        }

        @PostMapping()
        public ResponseEntity<ApiResponse<PromotionDetailDTO>> createPromotion(
                        @RequestBody PromotionRequestDTO promotion) {

                Promotion newPromotion = promotionService.createPromotion(promotion);

                PromotionDetailDTO promotionDetailDTO = new PromotionDetailDTO(newPromotion);
                promotionDetailDTO.setVehicleTypeDetails(
                                newPromotion.getVehicleDetails()
                                                .stream()
                                                .map(vehicleTypeDetail -> new VehicleTypeDetailDTO(vehicleTypeDetail))
                                                .toList());
                promotionDetailDTO.setAgencies(
                                newPromotion.getAgencies()
                                                .stream()
                                                .map(agency -> new AgencyDTO(agency))
                                                .toList());
                // return ResponseEntity.ok(khuyenMaiChiTietDTO);

                return ResponseEntity.ok(new ApiResponse<PromotionDetailDTO>(true, null, promotionDetailDTO));

        }

        @DeleteMapping("/{promotionId}")
        public ResponseEntity<ApiResponse<PromotionDetailDTO>> DeletePromotion(@PathVariable String promotionId) {
                Promotion promotion = promotionService.DeletePromotion(Integer.parseInt(promotionId));

                PromotionDetailDTO promotionDetailDTO = new PromotionDetailDTO(promotion);
                promotionDetailDTO.setVehicleTypeDetails(
                                promotion.getVehicleDetails()
                                                .stream()
                                                .map(vehicleTypeDetail -> new VehicleTypeDetailDTO(vehicleTypeDetail))
                                                .toList());
                promotionDetailDTO.setAgencies(
                                promotion.getAgencies()
                                                .stream()
                                                .map(agency -> new AgencyDTO(agency))
                                                .toList());
                // return ResponseEntity.ok(khuyenMaiChiTietDTO);
                return ResponseEntity.ok(new ApiResponse<PromotionDetailDTO>(true, null,promotionDetailDTO));

        }

        @PutMapping("/{promotionId}")
        public ResponseEntity<ApiResponse<PromotionDetailDTO>> putPromotion(@PathVariable String promotionId,
                        @RequestBody PromotionRequestDTO promotion) {
                Promotion promotionUpdate = promotionService.UpdatePromotion(Integer.parseInt(promotionId), promotion);
                PromotionDetailDTO promotionDetailDTO = new PromotionDetailDTO(promotionUpdate);
                promotionDetailDTO.setVehicleTypeDetails(
                                promotionUpdate.getVehicleDetails()
                                                .stream()
                                                .map(vehicleTypeDetails-> new VehicleTypeDetailDTO(vehicleTypeDetails))
                                                .toList());
                promotionDetailDTO.setAgencies(
                                promotionUpdate.getAgencies()
                                                .stream()
                                                .map(agency -> new AgencyDTO(agency))
                                                .toList());

                // return ResponseEntity.ok(khuyenMaiChiTietDTO);
                return ResponseEntity.ok(new ApiResponse<PromotionDetailDTO>(true, null, promotionDetailDTO));

        }
}
