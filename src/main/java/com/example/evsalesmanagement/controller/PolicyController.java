package com.example.evsalesmanagement.controller;

import com.example.evsalesmanagement.dto.policy.PolicyRequestDTO;
import com.example.evsalesmanagement.dto.policy.PolicyResponseDTO;
import com.example.evsalesmanagement.dto.policy.PolicySummaryDTO;
import com.example.evsalesmanagement.service.PolicyService;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.example.evsalesmanagement.utils.ApiResponse;

import jakarta.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<PolicyResponseDTO>> createPolicy(@RequestBody PolicyRequestDTO policyRequestDTO) {
        PolicyResponseDTO responseDTO = policyService.createPolicy(policyRequestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, null, responseDTO));
    }

    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @GetMapping()
    public ResponseEntity<ApiResponse<List<PolicySummaryDTO>>> getAllPolicies(
            @RequestParam Integer page,
            @RequestParam @Positive Integer size) {
        Pageable pageable = org.springframework.data.domain.PageRequest.of(page - 1, size);
        List<PolicySummaryDTO> policySummaryDTOs = policyService.getAllPolicies(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, null, policySummaryDTOs));
    }

    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @GetMapping("/{policyId}")
    public ResponseEntity<ApiResponse<PolicyResponseDTO>> getByIdPolicy(@PathVariable Integer policyId) {
        PolicyResponseDTO policyResponseDTO = policyService.getByIdPolicy(policyId);
        return ResponseEntity.ok(new ApiResponse<>(true, null, policyResponseDTO));
    }

    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @PutMapping("/{policyId}")
    public ResponseEntity<ApiResponse<PolicyResponseDTO>> updatePolicy(@PathVariable Integer policyId,
            @RequestBody PolicyRequestDTO policyRequestDTO) {
        PolicyResponseDTO policyResponseDTO = policyService.updatePolicy(policyId, policyRequestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, null, policyResponseDTO));
    }

    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @DeleteMapping("/{policyId}")
    public ResponseEntity<ApiResponse<PolicyResponseDTO>> deletePolicy(@PathVariable Integer policyId) {
        PolicyResponseDTO policyResponseDTO = policyService.deletePolicy(policyId);
        return ResponseEntity.ok(new ApiResponse<>(true, null, policyResponseDTO));
    }

}
