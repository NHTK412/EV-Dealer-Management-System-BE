package com.example.evsalesmanagement.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.model.Customer;
import com.example.evsalesmanagement.model.Feedback;
import com.example.evsalesmanagement.utils.MessageFormat;

/**
 * Service tạo mailto link để mở ứng dụng email trên thiết bị
 * Dealer sẽ gửi email thủ công từ ứng dụng email của mình
 */
@Service
public class EmailService {
    
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    
    /**
     * Tạo mailto link với nội dung đã điền sẵn
     */
    public String taoMailtoLink(Feedback phanHoi, String noiDungXuLy) {
        try {
            Customer kh = phanHoi.getKhachHang();
            
            String to = kh.getEmail();
            String subject = "Phản hồi: " + phanHoi.getTieuDePhanHoi();
            String body = taoNoiDungEmail(kh, phanHoi, noiDungXuLy);
            
            // Encode URL
            String mailtoLink = String.format("mailto:%s?subject=%s&body=%s",
                to,
                encodeValue(subject),
                encodeValue(body)
            );
            
            log.info("Đã tạo mailto link cho khách hàng: {}", kh.getEmail());
            return mailtoLink;
            
        } catch (Exception e) {
            log.error("Lỗi tạo mailto link: {}", e.getMessage());
            // throw new RuntimeException("Không thể tạo mailto link");
            throw new RuntimeException(MessageFormat.EMAIL_LINK_ERROR);
        }
    }
    
    /**
     * Tạo nội dung email dạng text 
     */
    private String taoNoiDungEmail(Customer kh, Feedback ph, String noiDungXuLy) {
        return String.format("""
            Kính gửi %s,
            
            Cảm ơn bạn đã liên hệ với EV Sales Management. Chúng tôi đã xem xét phản hồi của bạn và xin gửi lại thông tin như sau:
            
            ───────────────────────────────────
            ===== PHẢN HỒI CỦA BẠN =====
            ───────────────────────────────────
            Tiêu đề: %s
            Nội dung: %s
            
            ───────────────────────────────────
            ===== PHẢN HỒI TỪ CHÚNG TÔI =====
            ───────────────────────────────────
            %s

            ───────────────────────────────────
            
            Nếu bạn có bất kỳ câu hỏi nào khác, vui lòng liên hệ lại với chúng tôi.
            
            Trân trọng,
            EV Sales Management Team
            
            ---
            Email này được soạn từ hệ thống EV Sales Management
            """,
            kh.getTenKhachHang(),
            ph.getTieuDePhanHoi(),
            ph.getNoiDungPhanHoi(),
            noiDungXuLy
        );
    }
    
    /**
     * Encode giá trị cho URL
     */
    private String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString())
                    .replace("+", "%20"); 
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding error", e);
        }
    }
}