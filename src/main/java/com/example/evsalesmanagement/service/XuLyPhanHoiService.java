package com.example.evsalesmanagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evsalesmanagement.dto.ChiTietPhanHoiResponse;
import com.example.evsalesmanagement.dto.XuLyPhanHoiRequest;
import com.example.evsalesmanagement.dto.XuLyPhanHoiResponse;
import com.example.evsalesmanagement.enums.HinhThucGiaiQuyet;
import com.example.evsalesmanagement.enums.TrangThaiPhanHoi;
import com.example.evsalesmanagement.enums.TrangThaiXuLy;
import com.example.evsalesmanagement.exception.BadRequestException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.NhanVien;
import com.example.evsalesmanagement.model.PhanHoi;
import com.example.evsalesmanagement.model.XuLyPhanHoi;
import com.example.evsalesmanagement.repository.NhanVienRepository;
import com.example.evsalesmanagement.repository.PhanHoiRepository;
import com.example.evsalesmanagement.repository.XuLyPhanHoiRepository;
import com.example.evsalesmanagement.utils.MessageFormat;

@Service
public class XuLyPhanHoiService {
    
    private static final Logger log = LoggerFactory.getLogger(XuLyPhanHoiService.class);
    
    private final XuLyPhanHoiRepository xuLyPhanHoiRepository;
    private final PhanHoiRepository phanHoiRepository;
    private final NhanVienRepository nhanVienRepository;
    private final PhanHoiService phanHoiService;
    private final EmailService emailService;
    
    public XuLyPhanHoiService(
            XuLyPhanHoiRepository xuLyPhanHoiRepository,
            PhanHoiRepository phanHoiRepository,
            NhanVienRepository nhanVienRepository,
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
        
        PhanHoi phanHoi = validateVaLayPhanHoi(maPhanHoi);
        NhanVien nhanVien = validateVaLayNhanVien(maNhanVien);
        HinhThucGiaiQuyet hinhThucEnum = validateHinhThucGiaiQuyet(request.getHinhThucGiaiQuyet()); 
        
        phanHoiService.capNhatTrangThai(maPhanHoi, TrangThaiPhanHoi.DANG_XU_LY);
        
        XuLyPhanHoi xuLyPhanHoi = new XuLyPhanHoi();
        xuLyPhanHoi.setPhanHoi(phanHoi);
        xuLyPhanHoi.setNhanVien(nhanVien);
        xuLyPhanHoi.setNoiDungXuLy(request.getNoiDungXuLy());
        xuLyPhanHoi.setHinhThucGiaiQuyet(hinhThucEnum); 
        xuLyPhanHoi.setTrangThai(TrangThaiXuLy.HOAN_THANH); 
        
        xuLyPhanHoiRepository.save(xuLyPhanHoi);
        log.info("Đã lưu xử lý phản hồi");
        
        phanHoiService.capNhatTrangThai(maPhanHoi, TrangThaiPhanHoi.DA_XU_LY);
        
        String mailtoLink = taoMailtoLinkNeuCanThiet(phanHoi, request, hinhThucEnum);
        
        ChiTietPhanHoiResponse chiTiet = phanHoiService.layChiTietPhanHoi(maPhanHoi);
        
        log.info("Hoàn thành xử lý phản hồi {}", maPhanHoi);
        return new XuLyPhanHoiResponse(chiTiet, mailtoLink);
    }
    
    // ==================== VALIDATION ====================
    
    private PhanHoi validateVaLayPhanHoi(Integer maPhanHoi) {
        PhanHoi phanHoi = phanHoiRepository.findByIdWithKhachHang(maPhanHoi)
                .orElseThrow(() -> new ResourceNotFoundException(
                    // "Không tìm thấy phản hồi: " + maPhanHoi
                    String.format(MessageFormat.PHAN_HOI_NOT_FOUND, maPhanHoi)
                    ));
        
        TrangThaiPhanHoi trangThai = phanHoi.getTrangThai();
        if (trangThai == TrangThaiPhanHoi.DA_XU_LY) {
            // throw new BadRequestException("Phản hồi đã được xử lý");
            throw new BadRequestException(MessageFormat.PHAN_HOI_DA_XU_LY);
        }
        
        return phanHoi;
    }
    
    private NhanVien validateVaLayNhanVien(Integer maNhanVien) {
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
            PhanHoi phanHoi, 
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