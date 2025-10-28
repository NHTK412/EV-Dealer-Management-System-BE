package com.example.evsalesmanagement.model;

import com.example.evsalesmanagement.enums.FeedbackHandlingMethod;
import com.example.evsalesmanagement.enums.FeedbackHandlingStatus;

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


//XuLyPhanHoi = FeedbackHandling
@Entity
@Table(name = "FeedbackHandling")
public class FeedbackHandling extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FeedbackHandlingId")
    private Integer feedbackHandlingId;

    @Column(name = "FeedbackHandlingContent")
    private String feedbackHandlingContent; ;

    @Enumerated(EnumType.STRING)
    @Column(name = "FeedbackHandlingMethod", nullable = false)
    private FeedbackHandlingMethod feedbackHandlingMethod;

    // @Column(name = "ThoiGian")
    // private LocalDateTime thoiGian;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private FeedbackHandlingStatus status;

    @OneToOne
    @JoinColumn(name = "FeedbackId", unique = true)
    private Feedback feedback;

    @ManyToOne
    @JoinColumn(name = "EmployeeId")
    private Employee employee;

    public Integer getFeedbackHandlingId() {
        return feedbackHandlingId;
    }

    public void setFeedbackHandlingId(Integer feedbackHandlingId) {
        this.feedbackHandlingId = feedbackHandlingId;
    }

    public String getFeedbackHandlingContent() {
        return feedbackHandlingContent;
    }

    public void setFeedbackHandlingContent(String feedbackHandlingContent) {
        this.feedbackHandlingContent = feedbackHandlingContent;
    }

    public FeedbackHandlingMethod getFeedbackHandlingMethod() {
        return feedbackHandlingMethod;
    }

    public void setFeedbackHandlingMethod(FeedbackHandlingMethod feedbackHandlingMethod) {
        this.feedbackHandlingMethod = feedbackHandlingMethod;
    }

    public FeedbackHandlingStatus getStatus() {
        return status;
    }

    public void setStatus(FeedbackHandlingStatus status) {
        status = status;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    

    // public LocalDateTime getThoiGian() {
    // return thoiGian;
    // }

    // public void setThoiGian(LocalDateTime thoiGian) {
    // this.thoiGian = thoiGian;
    // }

 
   

}