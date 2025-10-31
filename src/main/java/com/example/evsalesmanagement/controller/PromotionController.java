package com.example.evsalesmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.VehicleTypeDetailDTO;
import com.example.evsalesmanagement.dto.promotion.PromotionResponseDTO;
import com.example.evsalesmanagement.dto.promotion.PromotionRequestDTO;
import com.example.evsalesmanagement.dto.promotion.PromotionSummaryDTO;
import com.example.evsalesmanagement.dto.AgencyDTO;
import com.example.evsalesmanagement.model.Promotion;
import com.example.evsalesmanagement.service.PromotionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.evsalesmanagement.utils.ApiResponse;

import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/promotion")
public class PromotionController {

        @Autowired
        private PromotionService promotionService;

        @GetMapping()
        public ResponseEntity<ApiResponse<List<PromotionSummaryDTO>>> getAllPromotions(@RequestParam Integer page,
                        @RequestParam @Positive Integer size) {

                Pageable pageable = PageRequest.of(page - 1, size);

                List<PromotionSummaryDTO> promotionSummaryDTOs = promotionService.getAllPromotions(pageable);

                return ResponseEntity.ok(new ApiResponse<List<PromotionSummaryDTO>>(true, null, promotionSummaryDTOs));
        }

        @GetMapping("/{promotionId}")
        public ResponseEntity<ApiResponse<PromotionResponseDTO>> getByIdPromotion(@PathVariable Integer promotionId) {

                PromotionResponseDTO promotionResponseDTO = promotionService.getByIdPromotion(promotionId);

                return ResponseEntity.ok(new ApiResponse<PromotionResponseDTO>(true, null, promotionResponseDTO));

        }

        @PostMapping()
        public ResponseEntity<ApiResponse<PromotionResponseDTO>> createPromotion(
                        @RequestBody PromotionRequestDTO promotion) {

                PromotionResponseDTO promotionResponseDTO = promotionService.createPromotion(promotion);
                return ResponseEntity.ok(new ApiResponse<PromotionResponseDTO>(true, null, promotionResponseDTO));

        }

        @DeleteMapping("/{promotionId}")
        public ResponseEntity<ApiResponse<PromotionResponseDTO>> DeletePromotion(@PathVariable Integer promotionId) {

                PromotionResponseDTO promotionResponseDTO = promotionService.deletePromotion(promotionId);

                return ResponseEntity.ok(new ApiResponse<PromotionResponseDTO>(true, null, promotionResponseDTO));

        }

        @PutMapping("/{promotionId}")
        public ResponseEntity<ApiResponse<PromotionResponseDTO>> putPromotion(@PathVariable Integer promotionId,
                        @RequestBody PromotionRequestDTO promotion) {

                PromotionResponseDTO promotionResponseDTO = promotionService.updatePromotion(promotionId, promotion);
                return ResponseEntity.ok(new ApiResponse<PromotionResponseDTO>(true, null, promotionResponseDTO));

        }
}
