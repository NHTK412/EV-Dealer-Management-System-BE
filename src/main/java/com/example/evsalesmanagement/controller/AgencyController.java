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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.Positive;
import java.util.List;

//daily=agency
@RestController
@RequestMapping("/agency")
public class AgencyController {
    @Autowired
    private AgencyService agencyService;

    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @GetMapping()
    public ResponseEntity<ApiResponse<List<AgencySummaryDTO>>> getAllAgencies(
            @RequestParam Integer page,
            @RequestParam @Positive Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        List<AgencySummaryDTO> response = agencyService.getAllAgencies(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @GetMapping("/{agencyId}")
    public ResponseEntity<ApiResponse<AgencyResponseDTO>> getByIdAgency(@PathVariable Integer agencyId) {
        AgencyResponseDTO response = agencyService.getByIdAgency(agencyId);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<ApiResponse<AgencyResponseDTO>> createAgency(
            @RequestBody AgencyRequestDTO agencyRequestDTO) {
        AgencyResponseDTO response = agencyService.createAgency(agencyRequestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{agencyId}")
    public ResponseEntity<ApiResponse<AgencyResponseDTO>> updateAgency(@PathVariable Integer agencyId,
            @RequestBody AgencyRequestDTO agencyRequestDTO) {
        AgencyResponseDTO response = agencyService.updateAgency(agencyId, agencyRequestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{agencyId}")
    public ResponseEntity<ApiResponse<AgencyResponseDTO>> deleteAgency(@PathVariable Integer agencyId) {
        AgencyResponseDTO response = agencyService.deleteAgency(agencyId);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

}
