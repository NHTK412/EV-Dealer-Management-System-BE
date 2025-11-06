
package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.dto.policy.*;
import com.example.evsalesmanagement.dto.quantitydiscountlevel.QuantityDiscountLevelResponseDTO;
import com.example.evsalesmanagement.dto.salesdiscountlevel.SalesDiscountLevelResponseDTO;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Policy;
import com.example.evsalesmanagement.model.QuantityDiscountLevel;
import com.example.evsalesmanagement.model.SalesDiscountLevel;
import com.example.evsalesmanagement.repository.PolicyRepository;
import com.example.evsalesmanagement.repository.QuantityDiscountLevelRepository;
import com.example.evsalesmanagement.repository.SalesDiscountLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PolicyService {
    @Autowired
    private PolicyRepository policyRepository;
    @Autowired
    private QuantityDiscountLevelRepository quantityDiscountLevelRepository;
    @Autowired
    private SalesDiscountLevelRepository salesDiscountLevelRepository;
    @Autowired
    private com.example.evsalesmanagement.repository.AgencyRepository agencyRepository;

    @Transactional
    public PolicyResponseDTO createPolicy(PolicyRequestDTO policyRequestDTO) {
        Policy policy = new Policy();
        policy.setPolicyType(policyRequestDTO.getPolicyType());
        policy.setPolicyValue(policyRequestDTO.getPolicyValue());
        policy.setPolicyCondition(policyRequestDTO.getPolicyCondition());
        policy.setStartDate(policyRequestDTO.getStartDate());
        policy.setEndDate(policyRequestDTO.getEndDate());
        policy.setStatus(policyRequestDTO.getStatus());
        if (policyRequestDTO.getAgencyId() != null) {
            agencyRepository.findById(policyRequestDTO.getAgencyId()).ifPresent(policy::setAgency);
        } else {
            policy.setAgency(null);
        }
        Policy savedPolicy = policyRepository.save(policy);

        if ("quantity".equalsIgnoreCase(policyRequestDTO.getPolicyType()) && policyRequestDTO.getQuantityDiscountLevels() != null) {
            policyRequestDTO.getQuantityDiscountLevels().forEach(quantityDiscountLevelRequestDTO -> {
                QuantityDiscountLevel quantityDiscountLevel = new QuantityDiscountLevel();
                quantityDiscountLevel.setQuantityFrom(quantityDiscountLevelRequestDTO.getQuantityFrom());
                quantityDiscountLevel.setQuantityTo(quantityDiscountLevelRequestDTO.getQuantityTo());
                quantityDiscountLevel.setDiscountPercentage(quantityDiscountLevelRequestDTO.getDiscountPercentage());
                quantityDiscountLevel.setPolicy(savedPolicy);
                quantityDiscountLevelRepository.save(quantityDiscountLevel);
            });
        }
        if ("sales".equalsIgnoreCase(policyRequestDTO.getPolicyType()) && policyRequestDTO.getSalesDiscountLevels() != null) {
            policyRequestDTO.getSalesDiscountLevels().forEach(salesDiscountLevelRequestDTO -> {
                SalesDiscountLevel salesDiscountLevel = new SalesDiscountLevel();
                salesDiscountLevel.setSalesFrom(salesDiscountLevelRequestDTO.getSalesFrom());
                salesDiscountLevel.setSalesTo(salesDiscountLevelRequestDTO.getSalesTo());
                salesDiscountLevel.setDiscountPercentage(salesDiscountLevelRequestDTO.getDiscountPercentage());
                salesDiscountLevel.setPolicy(savedPolicy);
                salesDiscountLevelRepository.save(salesDiscountLevel);
            });
        }

        return getByIdPolicy(savedPolicy.getPolicyId());
    }

    @Transactional()
    public List<PolicySummaryDTO> getAllPolicies(Pageable pageable) {
        Page<Policy> policies = policyRepository.findAll(pageable);
        return policies.stream().map(policy -> {
            PolicySummaryDTO policySummaryDTO = new PolicySummaryDTO();
            policySummaryDTO.setPolicyId(policy.getPolicyId());
            policySummaryDTO.setPolicyType(policy.getPolicyType());
            policySummaryDTO.setPolicyValue(policy.getPolicyValue());
            policySummaryDTO.setPolicyCondition(policy.getPolicyCondition());
            policySummaryDTO.setStartDate(policy.getStartDate());
            policySummaryDTO.setEndDate(policy.getEndDate());
            policySummaryDTO.setStatus(policy.getStatus());
            return policySummaryDTO;
        }).collect(Collectors.toList());
    }

    @Transactional()
    public PolicyResponseDTO getByIdPolicy(Integer policyId) {
        Policy policy = policyRepository.findById(policyId).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Policy"));
        PolicyResponseDTO policyResponseDTO = new PolicyResponseDTO();
        policyResponseDTO.setPolicyId(policy.getPolicyId());
        policyResponseDTO.setPolicyType(policy.getPolicyType());
        policyResponseDTO.setPolicyValue(policy.getPolicyValue());
        policyResponseDTO.setPolicyCondition(policy.getPolicyCondition());
        policyResponseDTO.setStartDate(policy.getStartDate());
        policyResponseDTO.setEndDate(policy.getEndDate());
        policyResponseDTO.setStatus(policy.getStatus());
        policyResponseDTO.setAgencyId(policy.getAgency() != null ? policy.getAgency().getAgencyId() : null);
        List<QuantityDiscountLevelResponseDTO> quantityDiscountLevelResponseDTOs = quantityDiscountLevelRepository.findByPolicy(policy).stream().map(quantityDiscountLevel -> {
            QuantityDiscountLevelResponseDTO quantityDiscountLevelResponseDTO = new QuantityDiscountLevelResponseDTO();
            quantityDiscountLevelResponseDTO.setQuantityDiscountLevelId(quantityDiscountLevel.getQuantityDiscountLevelId());
            quantityDiscountLevelResponseDTO.setQuantityFrom(quantityDiscountLevel.getQuantityFrom());
            quantityDiscountLevelResponseDTO.setQuantityTo(quantityDiscountLevel.getQuantityTo());
            quantityDiscountLevelResponseDTO.setDiscountPercentage(quantityDiscountLevel.getDiscountPercentage());
            return quantityDiscountLevelResponseDTO;
        }).collect(Collectors.toList());
        policyResponseDTO.setQuantityDiscountLevels(quantityDiscountLevelResponseDTOs);
        List<SalesDiscountLevelResponseDTO> salesDiscountLevelResponseDTOs = salesDiscountLevelRepository.findByPolicy(policy).stream().map(salesDiscountLevel -> {
            SalesDiscountLevelResponseDTO salesDiscountLevelResponseDTO = new SalesDiscountLevelResponseDTO();
            salesDiscountLevelResponseDTO.setSalesDiscountLevelId(salesDiscountLevel.getSalesDiscountLevelId());
            salesDiscountLevelResponseDTO.setSalesFrom(salesDiscountLevel.getSalesFrom());
            salesDiscountLevelResponseDTO.setSalesTo(salesDiscountLevel.getSalesTo());
            salesDiscountLevelResponseDTO.setDiscountPercentage(salesDiscountLevel.getDiscountPercentage());
            return salesDiscountLevelResponseDTO;
        }).collect(Collectors.toList());
        policyResponseDTO.setSalesDiscountLevels(salesDiscountLevelResponseDTOs);
        return policyResponseDTO;
    }

    @Transactional
    public PolicyResponseDTO updatePolicy(Integer policyId, PolicyRequestDTO policyRequestDTO) {
        Policy policy = policyRepository.findById(policyId).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Policy"));
        policy.setPolicyType(policyRequestDTO.getPolicyType());
        policy.setPolicyValue(policyRequestDTO.getPolicyValue());
        policy.setPolicyCondition(policyRequestDTO.getPolicyCondition());
        policy.setStartDate(policyRequestDTO.getStartDate());
        policy.setEndDate(policyRequestDTO.getEndDate());
        policy.setStatus(policyRequestDTO.getStatus());
        Policy savedPolicy = policyRepository.save(policy);
        return getByIdPolicy(savedPolicy.getPolicyId());
    }

    @Transactional
    public PolicyResponseDTO deletePolicy(Integer policyId) {
        Policy policy = policyRepository.findById(policyId).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Policy"));
        List<QuantityDiscountLevel> quantityDiscountLevels = quantityDiscountLevelRepository.findByPolicy(policy);
        quantityDiscountLevelRepository.deleteAll(quantityDiscountLevels);
        List<SalesDiscountLevel> salesDiscountLevels = salesDiscountLevelRepository.findByPolicy(policy);
        salesDiscountLevelRepository.deleteAll(salesDiscountLevels);
        policyRepository.delete(policy);
        PolicyResponseDTO policyResponseDTO = new PolicyResponseDTO();
        policyResponseDTO.setPolicyId(policy.getPolicyId());
        policyResponseDTO.setPolicyType(policy.getPolicyType());
        policyResponseDTO.setPolicyValue(policy.getPolicyValue());
        policyResponseDTO.setPolicyCondition(policy.getPolicyCondition());
        policyResponseDTO.setStartDate(policy.getStartDate());
        policyResponseDTO.setEndDate(policy.getEndDate());
        policyResponseDTO.setStatus(policy.getStatus());
        policyResponseDTO.setAgencyId(policy.getAgency() != null ? policy.getAgency().getAgencyId() : null);
        policyResponseDTO.setQuantityDiscountLevels(quantityDiscountLevels.stream().map(quantityDiscountLevel -> {
            QuantityDiscountLevelResponseDTO quantityDiscountLevelResponseDTO = new QuantityDiscountLevelResponseDTO();
            quantityDiscountLevelResponseDTO.setQuantityDiscountLevelId(quantityDiscountLevel.getQuantityDiscountLevelId());
            quantityDiscountLevelResponseDTO.setQuantityFrom(quantityDiscountLevel.getQuantityFrom());
            quantityDiscountLevelResponseDTO.setQuantityTo(quantityDiscountLevel.getQuantityTo());
            quantityDiscountLevelResponseDTO.setDiscountPercentage(quantityDiscountLevel.getDiscountPercentage());
            return quantityDiscountLevelResponseDTO;
        }).collect(Collectors.toList()));
        policyResponseDTO.setSalesDiscountLevels(salesDiscountLevels.stream().map(salesDiscountLevel -> {
            SalesDiscountLevelResponseDTO salesDiscountLevelResponseDTO = new SalesDiscountLevelResponseDTO();
            salesDiscountLevelResponseDTO.setSalesDiscountLevelId(salesDiscountLevel.getSalesDiscountLevelId());
            salesDiscountLevelResponseDTO.setSalesFrom(salesDiscountLevel.getSalesFrom());
            salesDiscountLevelResponseDTO.setSalesTo(salesDiscountLevel.getSalesTo());
            salesDiscountLevelResponseDTO.setDiscountPercentage(salesDiscountLevel.getDiscountPercentage());
            return salesDiscountLevelResponseDTO;
        }).collect(Collectors.toList()));
        return policyResponseDTO;
    }
}
