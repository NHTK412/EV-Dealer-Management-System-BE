package com.example.evsalesmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.quote.QuoteRequestDTO;
import com.example.evsalesmanagement.dto.quote.QuoteResponseDTO;
import com.example.evsalesmanagement.service.QuoteService;
import com.example.evsalesmanagement.utils.ApiResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("quote")
public class QuoteController {

    @Autowired
    QuoteService quoteService;

    @GetMapping("/{quoteId}")
    public ResponseEntity<ApiResponse<QuoteResponseDTO>> getQuoteById(@RequestParam Integer quoteId) {
        QuoteResponseDTO quoteResponseDTO = quoteService.getQuoteById(quoteId);
        return ResponseEntity.ok(new ApiResponse<>(true, null, quoteResponseDTO));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<QuoteResponseDTO>> createQuote(@RequestBody QuoteRequestDTO quoteRequestDTO) {
        QuoteResponseDTO quoteResponseDTO = quoteService.createQuote(quoteRequestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, null, quoteResponseDTO));
    }

    @DeleteMapping("/{quoteId}")
    public ResponseEntity<ApiResponse<QuoteResponseDTO>> deleteQuote(@RequestParam Integer quoteId) {
        QuoteResponseDTO quoteResponseDTO = quoteService.deleteQuote(quoteId);
        return ResponseEntity.ok(new ApiResponse<>(true, null, quoteResponseDTO));
    }

    @PutMapping("/{quoteId}")
    public ResponseEntity<ApiResponse<QuoteResponseDTO>> updateQuote(@PathVariable Integer quoteId,
            @RequestBody QuoteRequestDTO quoteRequestDTO) {
        QuoteResponseDTO quoteResponseDTO = quoteService.updateQuote(quoteId, quoteRequestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, null, quoteResponseDTO));
    }

    @PatchMapping("/{quoteId}/{status}")
    public ResponseEntity<ApiResponse<QuoteResponseDTO>> updateStatusQuote(@PathVariable Integer quoteId,
            @PathVariable String status) {
        QuoteResponseDTO quoteResponseDTO = quoteService.updateStatusQuote(quoteId, status);
        return ResponseEntity.ok(new ApiResponse<>(true, null, quoteResponseDTO));
    }

}
