package com.example.evsalesmanagement.service;

import java.beans.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.quote.QuoteResponseDTO;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Quote;
import com.example.evsalesmanagement.repository.QuoteRepository;

import jakarta.transaction.Transactional;

@Service
public class QuoteService {

    @Autowired
    QuoteRepository quoteRepository;

    @Transactional
    public QuoteResponseDTO getQuoteById(Integer quoteId) {
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new ResourceNotFoundException("Mã báo giá không tồn tại"));
        return new QuoteResponseDTO(quote);
    }
}
