package com.example.evsalesmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.exception.InternalServerException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.email.from:noreply@evsales.com}")
    private String fromEmail;

    @Value("${app.company.name:EV Sales Management}")
    private String companyName;

    // gửi email phản hồi cho customer
    public void sendFeedbackResponseEmail(
            String toEmail,
            String customerName,
            String feedbackTitle,
            String feedbackContent,
            String responseContent) {

        validateEmailParameters(toEmail, customerName, feedbackTitle, responseContent);

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setSubject(buildEmailSubject(feedbackTitle));
            message.setText(buildEmailBody(customerName, feedbackTitle, feedbackContent, responseContent));

            mailSender.send(message);

        } catch (Exception e) {
            throw new InternalServerException("Failed to send email", e);
        }
    }

    // tiêu đề email
    private String buildEmailSubject(String feedbackTitle) {
        return String.format("Response to your feedback: %s", feedbackTitle);
    }

    // nội dung email
    private String buildEmailBody(
            String customerName,
            String feedbackTitle,
            String feedbackContent,
            String responseContent) {

        return String.format(
                "Dear %s,\n\n" +
                        "Thank you for contacting %s. We have carefully reviewed your feedback.\n\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                        "YOUR FEEDBACK\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                        "Title: %s\n" +
                        "Content: %s\n\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                        "OUR RESPONSE\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                        "%s\n\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n" +
                        "If you have any further questions, please don't hesitate to contact us\n\n" +
                        "Best regards,\n" +
                        "%s Team\n\n" +
                        "---\n" +
                        "This is an automated message. Please do not reply directly to this email ok !",
                customerName,
                companyName,
                feedbackTitle,
                feedbackContent,
                responseContent,
                companyName);
    }

    // check các tham số email
    private void validateEmailParameters(
            String toEmail,
            String customerName,
            String feedbackTitle,
            String responseContent) {

        if (toEmail == null || toEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Recipient email cannot be empty");
        }

        if (!isValidEmail(toEmail)) {
            throw new IllegalArgumentException("Invalid email format: " + toEmail);
        }

        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }

        if (feedbackTitle == null || feedbackTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Feedback title cannot be empty");
        }

        if (responseContent == null || responseContent.trim().isEmpty()) {
            throw new IllegalArgumentException("Response content cannot be empty");
        }
    }

    // validate email
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
}