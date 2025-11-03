package com.example.evsalesmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.quote.QuoteRequestDTO;
import com.example.evsalesmanagement.dto.quote.QuoteResponseDTO;
import com.example.evsalesmanagement.service.QuoteService;
import com.example.evsalesmanagement.utils.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

}
