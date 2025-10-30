package com.example.evsalesmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.FeedbackHandling;

@Repository
public interface FeedbackHandlingRepository extends JpaRepository<FeedbackHandling, Integer> {
    boolean existsByFeedback_FeedbackId(Integer feedbackId);
}