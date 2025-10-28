package com.example.evsalesmanagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evsalesmanagement.dto.FeedbackDetailResponse;
import com.example.evsalesmanagement.dto.FeedbackHandlingRequest;
import com.example.evsalesmanagement.dto.FeedbackHandlingResponse;
import com.example.evsalesmanagement.enums.FeedbackHandlingMethod;
import com.example.evsalesmanagement.enums.FeedbackStatus;
import com.example.evsalesmanagement.enums.FeedbackHandlingStatus;
import com.example.evsalesmanagement.exception.BadRequestException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.model.Feedback;
import com.example.evsalesmanagement.model.FeedbackHandling;
import com.example.evsalesmanagement.repository.EmployeeRepository;
import com.example.evsalesmanagement.repository.FeedbackRepository;
import com.example.evsalesmanagement.repository.FeedbackHandlingRepository;
import com.example.evsalesmanagement.utils.MessageFormat;

@Service
public class FeedbackHandlingService {
    
    private static final Logger log = LoggerFactory.getLogger(FeedbackHandlingService.class);
    
    private final FeedbackHandlingRepository feedbackHandlingRepository;
    private final FeedbackRepository feedbackRepository;
    private final EmployeeRepository employeeRepository;
    private final FeedbackService feedbackService;
    private final EmailService emailService;
    
    public FeedbackHandlingService(
            FeedbackHandlingRepository feedbackHandlingRepository,
            FeedbackRepository feedbackRepository,
            EmployeeRepository employeeRepository,
            FeedbackService feedbackService,
            EmailService emailService) {
        this.feedbackHandlingRepository = feedbackHandlingRepository;
        this.feedbackRepository = feedbackRepository;
        this.employeeRepository = employeeRepository;
        this.feedbackService = feedbackService;
        this.emailService = emailService;
    }
    
    // @Transactional
    // public FeedbackHandlingResponse feedbackHandling(
    //         Integer feedbackId, 
    //         FeedbackHandlingRequest request, 
    //         Integer employeeId) {
        
    //     log.info("Bắt đầu xử lý phản hồi {} bởi nhân viên {}", feedbackId, employeeId);
        
    //     Feedback feedback = validateVaLayPhanHoi(feedbackId);
    //     Employee employee = validateVaLayNhanVien(employeeId);
    //     FeedbackHandlingMethod feedbackHandlingMethod = validateHinhThucGiaiQuyet(request.getFeedbackHandlingMethod()); 
        
    //    feedbackService.capNhatTrangThai(feedbackId, FeedbackStatus.IN_PROCESSED);
        
    //     FeedbackHandling feedbackHandling = new FeedbackHandling();
    //     feedbackHandling.setFeedback(feedback);
    //     feedbackHandling.setEmployee(employee);
    //     feedbackHandling.setHandlingContent(request.getHandlingContent());
    //     feedbackHandling.setFeedbackHandlingMethod(feedbackHandlingMethod);

    //     feedbackHandling.setTrangThai(FeedbackHandlingStatus.COMPLETED); 
        
    //     xuLyPhanHoiRepository.save(xuLyPhanHoi);
    //     log.info("Đã lưu xử lý phản hồi");
        
    //     phanHoiService.capNhatTrangThai(maPhanHoi, FeedbackStatus.DA_XU_LY);
        
    //     String mailtoLink = taoMailtoLinkNeuCanThiet(phanHoi, request, hinhThucEnum);
        
    //     FeedbackDetailResponse chiTiet = phanHoiService.layChiTietPhanHoi(maPhanHoi);
        
    //     log.info("Hoàn thành xử lý phản hồi {}", maPhanHoi);
    //     return new FeedbackHandlingResponse(chiTiet, mailtoLink);
    // }
    
    // // ==================== VALIDATION ====================
    
    // private Feedback validateVaLayPhanHoi(Integer maPhanHoi) {
    //     Feedback phanHoi = phanHoiRepository.findByIdWithKhachHang(maPhanHoi)
    //             .orElseThrow(() -> new ResourceNotFoundException(
    //                 // "Không tìm thấy phản hồi: " + maPhanHoi
    //                 String.format(MessageFormat.PHAN_HOI_NOT_FOUND, maPhanHoi)
    //                 ));
        
    //     FeedbackStatus trangThai = phanHoi.getTrangThai();
    //     if (trangThai == FeedbackStatus.DA_XU_LY) {
    //         // throw new BadRequestException("Phản hồi đã được xử lý");
    //         throw new BadRequestException(MessageFormat.PHAN_HOI_DA_XU_LY);
    //     }
        
    //     return phanHoi;
    // }
    
    // private Employee validateVaLayNhanVien(Integer maNhanVien) {
    //     return nhanVienRepository.findById(maNhanVien)
    //             .orElseThrow(() -> new ResourceNotFoundException(
    //                 // "Không tìm thấy nhân viên: " + maNhanVien
    //                 String.format(MessageFormat.NHAN_VIEN_NOT_FOUND, maNhanVien)
    //                 ));
    // }
    
    // private HinhThucGiaiQuyet validateHinhThucGiaiQuyet(String hinhThuc) {
    //     if (!HinhThucGiaiQuyet.enumIsValid(hinhThuc)) {
    //         // throw new BadRequestException("Hình thức giải quyết không hợp lệ: " + hinhThuc);
    //         throw new BadRequestException(String.format(MessageFormat.HINH_THUC_INVALID, hinhThuc));
    //     }
    //     return HinhThucGiaiQuyet.fromStringToEnum(hinhThuc);
    // }
    
    // // ==================== MAILTO LINK ====================
    // private String taoMailtoLinkNeuCanThiet(
    //         Feedback phanHoi, 
    //         FeedbackHandlingRequest request,
    //         HinhThucGiaiQuyet hinhThuc) {
        
    //     if (hinhThuc == HinhThucGiaiQuyet.EMAIL) {
    //         log.info("Tạo mailto link cho khách hàng: {}", 
    //             phanHoi.getKhachHang().getEmail());
    //         return emailService.taoMailtoLink(phanHoi, request.getNoiDungXuLy());
    //     } else {
    //         log.info("Hình thức điện thoại - không cần mailto link");
    //         return null;
    //     }
    // }
}