package com.example.evsalesmanagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendFeedbackResponseEmail(String toEmail, String customerName,
            String feedbackTitle, String feedbackContent,
            String responseContent) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Feedback from EV Sales Management: " + feedbackTitle);

            String emailBody = String.format(
                    "Dear %s,\n\n" +
                            "We have received your feedback:\n" +
                            "Title: %s\n" +
                            "Content: %s\n\n" +
                            "Feedback from us:\n%s\n\n" +
                            "Best regards,\n" +
                            "EV Sales Management Team",
                    customerName,
                    feedbackTitle,
                    feedbackContent,
                    responseContent);

            message.setText(emailBody);
            mailSender.send(message);

            // log.info("Email sent successfully to: {}", toEmail);

        } catch (Exception e) {
            // log.error("Failed to send email to {}: {}", toEmail, e.getMessage());
            throw new RuntimeException("Cannot send email: " + e.getMessage());
        }
    }
}