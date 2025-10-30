package com.example.evsalesmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.FeedbackDetailDTO;
import com.example.evsalesmanagement.dto.FeedbackListDTO;
import com.example.evsalesmanagement.dto.HandleFeedbackRequestDTO;
import com.example.evsalesmanagement.service.FeedbackHandlingService;
import com.example.evsalesmanagement.service.FeedbackService;
import com.example.evsalesmanagement.utils.ApiResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/feedback")
@Validated
public class FeedbackController {

    private static final Logger log = LoggerFactory.getLogger(FeedbackController.class);

    private final FeedbackService feedbackService;
    private final FeedbackHandlingService feedbackHandlingService;

    public FeedbackController(FeedbackService feedbackService, FeedbackHandlingService feedbackHandlingService) {
        this.feedbackService = feedbackService;
        this.feedbackHandlingService = feedbackHandlingService;
    }


    // lấy tất cả phản hồi hoặc theo trạng thái - phân trang
    @GetMapping
    public ResponseEntity<ApiResponse<Page<FeedbackListDTO>>> getAllFeedback(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        // log.info("GET /api/feedback - status: {}, page: {}, size: {}", status, page, size);

        Pageable pageable = PageRequest.of(page, size);
        Page<FeedbackListDTO> result;

        if (status != null && !status.trim().isEmpty()) {
            result = feedbackService.getFeedbackByStatus(status, pageable);
        } else {
            result = feedbackService.getAllFeedback(pageable);
        }

        return ResponseEntity.ok(
            new ApiResponse<>(true, "Get feedback list successfully", result)
        );
    }


    // lấy chi tiết phản hồi theo ID
    @GetMapping("/{feedbackId}")
    public ResponseEntity<ApiResponse<FeedbackDetailDTO>> getFeedbackDetail(
            @PathVariable @Positive(message = "Id feedback must be positive") Integer feedbackId) {

        // log.info("GET /api/feedback/{}", feedbackId);

        FeedbackDetailDTO detail = feedbackService.getFeedbackDetail(feedbackId);

        return ResponseEntity.ok(
            new ApiResponse<>(true, "Get feedback detail successfully", detail)
        );
    }


    // xử lý phản hồi
    @PostMapping("/{feedbackId}/handle")
    public ResponseEntity<ApiResponse<FeedbackDetailDTO>> handleFeedback(
            @PathVariable @Positive(message = "Id feedback must be positive") Integer feedbackId,
            @Valid @RequestBody HandleFeedbackRequestDTO request) {

        // log.info("POST /api/feedback/{}/handle", feedbackId);
        // hardcode pause        
        Integer employeeId = 1; 

        FeedbackDetailDTO result = feedbackHandlingService.handleFeedback(feedbackId, request, employeeId);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            new ApiResponse<>(true, "Handle feedback successfully", result)
        );
    }

    // đếm số lượng phản hồi theo trạng thái
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>> countFeedbackByStatus(
            @RequestParam String status) {

        // log.info("GET /api/feedback/count - status: {}", status);

        Long count = feedbackService.countByStatus(status);

        return ResponseEntity.ok(
            new ApiResponse<>(true, "Count feedback by status successfully", count)
        );
    }
}