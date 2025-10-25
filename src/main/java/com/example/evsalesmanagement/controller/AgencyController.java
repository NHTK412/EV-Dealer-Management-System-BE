package com.example.evsalesmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.DaiLyRequest;
import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.service.AgencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.evsalesmanagement.utils.ApiResponse;

@RestController
@RequestMapping("/agency")
public class AgencyController {
    @Autowired
    private AgencyService agencyService;

    @GetMapping
    ResponseEntity<ApiResponse<List<Agency>>> getAllDaiLy() {
        return ResponseEntity.ok(new ApiResponse<List<Agency>>(
                true, null, agencyService.getAllDaiLy()));
    }

    @GetMapping("/{maDaiLy}")
    ResponseEntity<ApiResponse<Agency>> getDaiLyById(@PathVariable Integer maDaiLy) {
        return ResponseEntity.ok(new ApiResponse<Agency>(
                true, null, agencyService.getDaiLyById(maDaiLy)));
    }

    @PutMapping("/{maDaiLy}")
    ResponseEntity<ApiResponse<Agency>> updateDaiLy(@PathVariable Integer maDaiLy, @RequestBody DaiLyRequest request) {
        return ResponseEntity.ok(new ApiResponse<Agency>(
                true, null, agencyService.getDaiLyById(maDaiLy)));
    }

}
