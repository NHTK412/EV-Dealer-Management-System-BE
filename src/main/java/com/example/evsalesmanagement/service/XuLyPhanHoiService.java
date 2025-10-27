package com.example.evsalesmanagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evsalesmanagement.dto.ChiTietPhanHoiResponse;
import com.example.evsalesmanagement.dto.XuLyPhanHoiRequest;
import com.example.evsalesmanagement.dto.XuLyPhanHoiResponse;
import com.example.evsalesmanagement.enums.HinhThucGiaiQuyet;
import com.example.evsalesmanagement.enums.FeedbackStatus;
import com.example.evsalesmanagement.enums.FeedbackHandlingStatus;
import com.example.evsalesmanagement.exception.BadRequestException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.model.Feedback;
import com.example.evsalesmanagement.model.FeedbackHandling;
import com.example.evsalesmanagement.repository.EmployeeRepository;
import com.example.evsalesmanagement.repository.FeedbackRepository;
import com.example.evsalesmanagement.repository.FeedbackHanglingRepository;
import com.example.evsalesmanagement.utils.MessageFormat;

@Service
public class XuLyPhanHoiService {
    
    private static final Logger log = LoggerFactory.getLogger(XuLyPhanHoiService.class);
    
    private final FeedbackHanglingRepository xuLyPhanHoiRepository;
    private final FeedbackRepository phanHoiRepository;
    private final EmployeeRepository nhanVienRepository;
    private final PhanHoiService phanHoiService;
    private final EmailService emailService;
    
    public XuLyPhanHoiService(
            FeedbackHanglingRepository xuLyPhanHoiRepository,
            FeedbackRepository phanHoiRepository,
            EmployeeRepository nhanVienRepository,
            PhanHoiService phanHoiService,
            EmailService emailService) {
        this.xuLyPhanHoiRepository = xuLyPhanHoiRepository;
        this.phanHoiRepository = phanHoiRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.phanHoiService = phanHoiService;
        this.emailService = emailService;
    }
    
    @Transactional
    public XuLyPhanHoiResponse xuLyPhanHoi(
            Integer maPhanHoi, 
            XuLyPhanHoiRequest request, 
            Integer maNhanVien) {
        
        log.info("Bắt đầu xử lý phản hồi {} bởi nhân viên {}", maPhanHoi, maNhanVien);
        
        Feedback phanHoi = validateVaLayPhanHoi(maPhanHoi);
        Employee nhanVien = validateVaLayNhanVien(maNhanVien);
        HinhThucGiaiQuyet hinhThucEnum = validateHinhThucGiaiQuyet(request.getHinhThucGiaiQuyet()); 
        
        phanHoiService.capNhatTrangThai(maPhanHoi, FeedbackStatus.DANG_XU_LY);
        
        FeedbackHandling xuLyPhanHoi = new FeedbackHandling();
        xuLyPhanHoi.setPhanHoi(phanHoi);
        xuLyPhanHoi.setNhanVien(nhanVien);
        xuLyPhanHoi.setNoiDungXuLy(request.getNoiDungXuLy());
        xuLyPhanHoi.setHinhThucGiaiQuyet(hinhThucEnum); 
        xuLyPhanHoi.setTrangThai(FeedbackHandlingStatus.HOAN_THANH); 
        
        xuLyPhanHoiRepository.save(xuLyPhanHoi);
        log.info("Đã lưu xử lý phản hồi");
        
        phanHoiService.capNhatTrangThai(maPhanHoi, FeedbackStatus.DA_XU_LY);
        
        String mailtoLink = taoMailtoLinkNeuCanThiet(phanHoi, request, hinhThucEnum);
        
        ChiTietPhanHoiResponse chiTiet = phanHoiService.layChiTietPhanHoi(maPhanHoi);
        
        log.info("Hoàn thành xử lý phản hồi {}", maPhanHoi);
        return new XuLyPhanHoiResponse(chiTiet, mailtoLink);
    }
    
    // ==================== VALIDATION ====================
    
    private Feedback validateVaLayPhanHoi(Integer maPhanHoi) {
        Feedback phanHoi = phanHoiRepository.findByIdWithKhachHang(maPhanHoi)
                .orElseThrow(() -> new ResourceNotFoundException(
                    // "Không tìm thấy phản hồi: " + maPhanHoi
                    String.format(MessageFormat.PHAN_HOI_NOT_FOUND, maPhanHoi)
                    ));
        
        FeedbackStatus trangThai = phanHoi.getTrangThai();
        if (trangThai == FeedbackStatus.DA_XU_LY) {
            // throw new BadRequestException("Phản hồi đã được xử lý");
            throw new BadRequestException(MessageFormat.PHAN_HOI_DA_XU_LY);
        }
        
        return phanHoi;
    }
    
    private Employee validateVaLayNhanVien(Integer maNhanVien) {
        return nhanVienRepository.findById(maNhanVien)
                .orElseThrow(() -> new ResourceNotFoundException(
                    // "Không tìm thấy nhân viên: " + maNhanVien
                    String.format(MessageFormat.NHAN_VIEN_NOT_FOUND, maNhanVien)
                    ));
    }
    
    private HinhThucGiaiQuyet validateHinhThucGiaiQuyet(String hinhThuc) {
        if (!HinhThucGiaiQuyet.enumIsValid(hinhThuc)) {
            // throw new BadRequestException("Hình thức giải quyết không hợp lệ: " + hinhThuc);
            throw new BadRequestException(String.format(MessageFormat.HINH_THUC_INVALID, hinhThuc));
        }
        return HinhThucGiaiQuyet.fromStringToEnum(hinhThuc);
    }
    
    // ==================== MAILTO LINK ====================
    private String taoMailtoLinkNeuCanThiet(
            Feedback phanHoi, 
            XuLyPhanHoiRequest request,
            HinhThucGiaiQuyet hinhThuc) {
        
        if (hinhThuc == HinhThucGiaiQuyet.EMAIL) {
            log.info("Tạo mailto link cho khách hàng: {}", 
                phanHoi.getKhachHang().getEmail());
            return emailService.taoMailtoLink(phanHoi, request.getNoiDungXuLy());
        } else {
            log.info("Hình thức điện thoại - không cần mailto link");
            return null;
        }
    }
}