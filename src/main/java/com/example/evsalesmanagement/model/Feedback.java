package com.example.evsalesmanagement.model;


import com.example.evsalesmanagement.enums.FeedbackStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity

//PhanHoi = Feedback
@Table(name = "Feedback")
public class Feedback extends TimeStampRecord{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FeedbackId")
    private Integer feedbackId;

    //TieuDePhanHoi = feedbackTitle
    @Column(name = "FeedbackTitle")
    private String feedbackTitle;

    //NoiDungPhanHoi = feedbackContent
    @Column(name = "FeedbackContent")
    private String feedbackContent;

    // @Column(name = "ThoiGian")
    // private LocalDateTime thoiGian;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable=false)
    private FeedbackStatus feedbackStatus;

    @ManyToOne
    @JoinColumn(name = "CustomerId")
    private Customer customer;

    @OneToOne(mappedBy = "feedback")
    private FeedbackHandling feedbackHandling;

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



    // public LocalDateTime getThoiGian() {
    //     return thoiGian;
    // }

    // public void setThoiGian(LocalDateTime thoiGian) {
    //     this.thoiGian = thoiGian;
    // }

   
    
    public FeedbackStatus geFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(FeedbackStatus feedbackStatus) {
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