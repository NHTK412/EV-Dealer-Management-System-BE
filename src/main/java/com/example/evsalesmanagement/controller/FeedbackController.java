package com.example.evsalesmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.feedback.FeadbackRequestDTO;
import com.example.evsalesmanagement.dto.feedback.FeedBackSummaryDTO;
import com.example.evsalesmanagement.dto.feedback.FeedbackResponseDTO;
import com.example.evsalesmanagement.dto.handlefeedback.HandleFeedbackRequestDTO;
import com.example.evsalesmanagement.service.FeedbackHandlingService;
import com.example.evsalesmanagement.service.FeedbackService;
import com.example.evsalesmanagement.utils.ApiResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/feedback")
@Validated
public class FeedbackController {

        @Autowired
        private FeedbackService feedbackService;

        @Autowired
        private FeedbackHandlingService feedbackHandlingService;

        // tạo phản hồi mới từ customer
         @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'DEALER_STAFF','ADMIN')")
        @PostMapping
        public ResponseEntity<ApiResponse<FeedbackResponseDTO>> createFeedback(
                        @Valid @RequestBody FeadbackRequestDTO request) {
                // request.getCustomerId());

                FeedbackResponseDTO result = feedbackService.createFeedback(request);

                return ResponseEntity.status(HttpStatus.CREATED).body(
                                new ApiResponse<>(true, "Feedback created successfully", result));
        }

        // lấy tất cả phản hồi + phân trang + lọc
         @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'DEALER_STAFF','ADMIN')")
        @GetMapping
        public ResponseEntity<ApiResponse<Page<FeedBackSummaryDTO>>> getAllFeedback(
                        @RequestParam(required = false) String status,
                        @RequestParam(defaultValue = "0") @Min(0) int page,
                        @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size) {

                Pageable pageable = PageRequest.of(page, size);
                Page<FeedBackSummaryDTO> result = feedbackService.getFeedback(status, pageable);

                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Get feedback list successfully", result));
        }

        // lấy phản hồi theo id
      @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'DEALER_STAFF','ADMIN')")
        @GetMapping("/{feedbackId}")
        public ResponseEntity<ApiResponse<FeedbackResponseDTO>> getFeedbackDetail(
                        @PathVariable @Positive(message = "Id feedback must be positive") Integer feedbackId) {

                FeedbackResponseDTO detail = feedbackService.getFeedbackDetail(feedbackId);

                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Get feedback detail successfully", detail));
        }

        // xử lý phản hồi
        @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'DEALER_STAFF','ADMIN')")
        @PostMapping("/{feedbackId}/handle")
        public ResponseEntity<ApiResponse<FeedbackResponseDTO>> handleFeedback(
                        @PathVariable @Positive(message = "Id feedback must be positive") Integer feedbackId,
                        @Valid @RequestBody HandleFeedbackRequestDTO request) {

                Integer employeeId = 1;

                FeedbackResponseDTO result = feedbackHandlingService.handleFeedback(
                                feedbackId,
                                request,
                                employeeId);

                return ResponseEntity.status(HttpStatus.CREATED).body(
                                new ApiResponse<>(true, "Handle feedback successfully", result));
        }

        // đếm phản hồi theo trạng thái
         @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'DEALER_STAFF','ADMIN')")
        @GetMapping("/count")
        public ResponseEntity<ApiResponse<Long>> countFeedbackByStatus(
                        @RequestParam String status) {

                Long count = feedbackService.countByStatus(status);

                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Count feedback by status successfully", count));
        }
}