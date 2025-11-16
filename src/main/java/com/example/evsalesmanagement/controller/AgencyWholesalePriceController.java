package com.example.evsalesmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.evsalesmanagement.dto.agencywholesaleprice.AgencyWholesalePriceRequestDTO;
import com.example.evsalesmanagement.dto.agencywholesaleprice.AgencyWholesalePriceResponseDTO;
import com.example.evsalesmanagement.dto.agencywholesaleprice.AgencyWholesalePriceSummaryDTO;
import com.example.evsalesmanagement.service.AgencyWholesalePricesService;
import com.example.evsalesmanagement.utils.ApiResponse;

import jakarta.validation.constraints.Positive;


@RestController
@RequestMapping("/agencyWholesalePrices")
public class AgencyWholesalePriceController {

    @Autowired
    private AgencyWholesalePricesService agencyWholesalePriceService;

    @PostMapping()
    public ResponseEntity<ApiResponse<AgencyWholesalePriceResponseDTO>> createAgencyWholesalePrice(
            @RequestBody AgencyWholesalePriceRequestDTO agencyWholesalePriceDTO) {

        AgencyWholesalePriceResponseDTO agencyWholesalePriceResponseDTO = agencyWholesalePriceService.createAgencyWholesalePrice(agencyWholesalePriceDTO);

        return ResponseEntity.ok(new ApiResponse<AgencyWholesalePriceResponseDTO>(true, null,agencyWholesalePriceResponseDTO));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AgencyWholesalePriceSummaryDTO>>> getAllAgencyWholesalePrices(
            @RequestParam int page,
            @RequestParam @Positive int size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        List<AgencyWholesalePriceSummaryDTO> agencyWholesalePricesSummaryDTOs = agencyWholesalePriceService.getAllAgencyWholesalePrices(pageable);

        return ResponseEntity.ok(new ApiResponse<>(true, null, agencyWholesalePricesSummaryDTOs));
    }

    @GetMapping("/{agencyWholesalePriceId}")
    public ResponseEntity<ApiResponse<AgencyWholesalePriceResponseDTO>> getByIdAgencyWholesalePrice(@PathVariable Integer agencyWholesalePriceId) {

        AgencyWholesalePriceResponseDTO agencyWholesalePriceResponseDTO = agencyWholesalePriceService.getByIdAgencyWholesalePrice(agencyWholesalePriceId);
        
        return ResponseEntity.ok(new ApiResponse<AgencyWholesalePriceResponseDTO>(true, null,agencyWholesalePriceResponseDTO));
    }

    @PutMapping("/{agencyWholesalePriceId}")
        public ResponseEntity<ApiResponse<AgencyWholesalePriceResponseDTO>> putAgencyWholesalePrice(@PathVariable Integer agencyWholesalePriceId,
                        @RequestBody AgencyWholesalePriceRequestDTO agencyWholesalePrice) {

            AgencyWholesalePriceResponseDTO agencyWholesalePriceResponseDTO = agencyWholesalePriceService.updateAgencyWholesalePrice(agencyWholesalePriceId, agencyWholesalePrice);

            return ResponseEntity.ok(new ApiResponse<AgencyWholesalePriceResponseDTO>(true, null,agencyWholesalePriceResponseDTO));
        }

    @DeleteMapping("/{agencyWholesalePriceId}")
        public ResponseEntity<ApiResponse<AgencyWholesalePriceResponseDTO>> deleteAgencyWholesalePrice(@PathVariable Integer agencyWholesalePriceId) {

            AgencyWholesalePriceResponseDTO agencyWholesalePriceResponseDTO = agencyWholesalePriceService.deleteAgencyWholesalePrice(agencyWholesalePriceId);
            return ResponseEntity.ok(new ApiResponse<AgencyWholesalePriceResponseDTO>(true, null,agencyWholesalePriceResponseDTO));
        }
}