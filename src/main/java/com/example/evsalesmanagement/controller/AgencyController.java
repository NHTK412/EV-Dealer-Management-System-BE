package com.example.evsalesmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.AgencyRequest;

import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.service.AgencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.evsalesmanagement.utils.ApiResponse;

//daily=agency
@RestController
@RequestMapping("/agency")
public class AgencyController {
    @Autowired
    private AgencyService agencyService;

    @GetMapping
    ResponseEntity<ApiResponse<List<Agency>>> getAllAgency() {
        return ResponseEntity.ok(new ApiResponse<List<Agency>>(
                true, null, agencyService.getAllAgency()));
    }

    @GetMapping("/{agencyId}")
    ResponseEntity<ApiResponse<Agency>> getAgencyById(@PathVariable Integer agencyId) {
        return ResponseEntity.ok(new ApiResponse<Agency>(
                true, null, agencyService.getAgencyById(agencyId)));
    }

    @PutMapping("/{agencyId}")
    ResponseEntity<ApiResponse<Agency>> updateAgency(@PathVariable Integer agencyId,
            @RequestBody AgencyRequest request) {
        return ResponseEntity.ok(new ApiResponse<Agency>(
                true, null, agencyService.getAgencyById(agencyId)));

    }

}
