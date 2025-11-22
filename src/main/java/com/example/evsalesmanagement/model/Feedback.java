package com.example.evsalesmanagement.model;

import com.example.evsalesmanagement.enums.FeedbackStatusEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Feedback")
public class Feedback extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FeedbackId")
    private Integer feedbackId;

    @Column(name = "FeedbackTitle")
    private String feedbackTitle;

    @Column(name = "FeedbackContent")
    private String feedbackContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private FeedbackStatusEnum feedbackStatus;

    @ManyToOne
    @JoinColumn(name = "CustomerId")
    private Customer customer;

    @OneToOne(mappedBy = "feedback", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private FeedbackHandling feedbackHandling;

    // Getters and Setters
    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public FeedbackStatusEnum getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(FeedbackStatusEnum feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public FeedbackHandling getFeedbackHandling() {
        return feedbackHandling;
    }

    public void setFeedbackHandling(FeedbackHandling feedbackHandling) {
        this.feedbackHandling = feedbackHandling;
    }
}