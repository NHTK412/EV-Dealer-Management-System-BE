package com.example.evsalesmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.DaiLyRequest;
import com.example.evsalesmanagement.model.DaiLy;
import com.example.evsalesmanagement.service.DaiLyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.evsalesmanagement.utils.ApiResponse;

@RestController
@RequestMapping("/daiLy")
public class DaiLyController {
    @Autowired
    private DaiLyService daiLyService;

    @GetMapping
    ResponseEntity<ApiResponse<List<DaiLy>>> getAllDaiLy() {
        return ResponseEntity.ok(new ApiResponse<List<DaiLy>>(
                true, null, daiLyService.getAllDaiLy()));
    }

    @GetMapping("/{maDaiLy}")
    ResponseEntity<ApiResponse<DaiLy>> getDaiLyById(@PathVariable Integer maDaiLy) {
        return ResponseEntity.ok(new ApiResponse<DaiLy>(
                true, null, daiLyService.getDaiLyById(maDaiLy)));
    }

    @PutMapping("/{maDaiLy}")
    ResponseEntity<ApiResponse<DaiLy>> updateDaiLy(@PathVariable Integer maDaiLy, @RequestBody DaiLyRequest request) {
        return ResponseEntity.ok(new ApiResponse<DaiLy>(
                true, null, daiLyService.getDaiLyById(maDaiLy)));
    }

}
