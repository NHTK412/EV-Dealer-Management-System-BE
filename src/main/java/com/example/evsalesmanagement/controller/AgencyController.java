package com.example.evsalesmanagement.controller;

import com.example.evsalesmanagement.dto.agency.AgencyRequestDTO;
import com.example.evsalesmanagement.dto.agency.AgencyResponseDTO;
import com.example.evsalesmanagement.dto.agency.AgencySummaryDTO;
import com.example.evsalesmanagement.service.AgencyService;
import com.example.evsalesmanagement.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.Positive;
import java.util.List;

//daily=agency
@RestController
@RequestMapping("/agency")
public class AgencyController {
    @Autowired
    private AgencyService agencyService;

    @GetMapping()
    public ResponseEntity<ApiResponse<ApiResponse<List<AgencySummaryDTO>>>> getAllAgencies(
        @RequestParam Integer page,
        @RequestParam @Positive Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        ApiResponse<List<AgencySummaryDTO>> response = agencyService.getAllAgencies(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

    @GetMapping("/{agencyId}")
    public ResponseEntity<ApiResponse<ApiResponse<AgencyResponseDTO>>> getByIdAgency(@PathVariable Integer agencyId) {
        ApiResponse<AgencyResponseDTO> response = agencyService.getByIdAgency(agencyId);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<ApiResponse<AgencyResponseDTO>>> createAgency(@RequestBody AgencyRequestDTO agencyRequestDTO) {
        ApiResponse<AgencyResponseDTO> response = agencyService.createAgency(agencyRequestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

    @PutMapping("/{agencyId}")
    public ResponseEntity<ApiResponse<ApiResponse<AgencyResponseDTO>>> updateAgency(@PathVariable Integer agencyId,
                                                                      @RequestBody AgencyRequestDTO agencyRequestDTO) {
        ApiResponse<AgencyResponseDTO> response = agencyService.updateAgency(agencyId, agencyRequestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

    @DeleteMapping("/{agencyId}")
    public ResponseEntity<ApiResponse<ApiResponse<AgencyResponseDTO>>> deleteAgency(@PathVariable Integer agencyId) {
        ApiResponse<AgencyResponseDTO> response = agencyService.deleteAgency(agencyId);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

}
