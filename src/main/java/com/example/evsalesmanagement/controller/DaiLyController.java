package com.example.evsalesmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.DaiLyRequest;
import com.example.evsalesmanagement.model.DaiLy;
import com.example.evsalesmanagement.service.DaiLyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;






@RestController
@RequestMapping("/daiLy")
public class DaiLyController {
    @Autowired
    private DaiLyService daiLyService;

    @GetMapping
    List<DaiLy> getAllDaiLy() {
        return daiLyService.getAllDaiLy();
    }

    @GetMapping("/{DaiLyId}")
    DaiLy getDaiLyById(@PathVariable Integer DaiLyId) {
        return daiLyService.getDaiLyById(DaiLyId);
    }

    @PutMapping("/{DaiLyId}")
    DaiLy updateDaiLy(@PathVariable Integer DaiLyId, @RequestBody DaiLyRequest request) {
        return daiLyService.updateDaiLy(DaiLyId, request);
    }
    
    
    
    
}
