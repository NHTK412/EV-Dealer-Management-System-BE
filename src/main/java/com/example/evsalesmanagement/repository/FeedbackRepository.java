package com.example.evsalesmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.enums.FeedbackStatusEnum;
import com.example.evsalesmanagement.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    Page<Feedback> findAllByOrderByCreateAtDesc(Pageable pageable);
    Page<Feedback> findByFeedbackStatusOrderByCreateAtDesc(FeedbackStatusEnum status, Pageable pageable);
    Long countByFeedbackStatus(FeedbackStatusEnum status);
}