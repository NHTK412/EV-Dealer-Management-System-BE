package com.example.evsalesmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.evsalesmanagement.dto.AgencyWholesalePriceDTO;
import com.example.evsalesmanagement.model.AgencyWholesalePrice;
import com.example.evsalesmanagement.service.AgencyWholesalePricesService;


@RestController
@RequestMapping("/agencyWholesalePrices")
public class AgencyWholesalePriceController {

    @Autowired
    private AgencyWholesalePricesService agencyWholesalePriceService;

    @PostMapping
    public ResponseEntity<AgencyWholesalePrice> createAgencyWholesalePrice(@RequestBody AgencyWholesalePriceDTO request) {
        return ResponseEntity.ok(agencyWholesalePriceService.createAgencyWholesalePrice(request));
    }

    @GetMapping
    public ResponseEntity<Page<AgencyWholesalePrice>> getAllAgencyWholesalePrices(
            @RequestParam int page,
            @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(agencyWholesalePriceService.getAllAgencyWholesalePrices(pageable));
    }

    @GetMapping("/{agencyWholesalePricesId}")
    public ResponseEntity<AgencyWholesalePrice> getAgencyWholesalePriceById(@PathVariable Integer id) {
        return ResponseEntity.ok(agencyWholesalePriceService.getAgencyWholesalePriceById(id));
    }

    @PutMapping("/{agencyWholesalePricesId}")
    public ResponseEntity<AgencyWholesalePrice> updateAgencyWholesalePrice(
            @PathVariable Integer id,
            @RequestBody AgencyWholesalePriceDTO request) {
        return ResponseEntity.ok(agencyWholesalePriceService.updateAgencyWholesalePrice(id, request));
    }

    @DeleteMapping("/{agencyWholesalePricesId}")
    public ResponseEntity<Void> deleteAgencyWholesalePrice(@PathVariable Integer id) {
        agencyWholesalePriceService.deleteAgencyWholesalePrice(id);
        return ResponseEntity.ok().build();
    }
}