package com.example.evsalesmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evsalesmanagement.dto.FeedbackDetailResponse;
import com.example.evsalesmanagement.dto.FeedbackResponseDTO;
import com.example.evsalesmanagement.enums.FeedbackStatus;
import com.example.evsalesmanagement.exception.BadRequestException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Feedback;
import com.example.evsalesmanagement.model.FeedbackHandling;
import com.example.evsalesmanagement.repository.FeedbackRepository;
import com.example.evsalesmanagement.utils.MessageFormat;

@Service
public class FeedbackService {
    
    private static final Logger log = LoggerFactory.getLogger(FeedbackService.class);
    
    private final FeedbackRepository feedbackRepository;
    
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }
    
    // @Transactional(readOnly = true)
    // public List<FeedbackResponseDTO> GetAllFeedback() {
    //     log.info("Lấy danh sách tất cả phản hồi");
        
    //     List<Feedback> danhSach = feedbackRepository.findAllWithKhachHang();
    //     log.debug("Tìm thấy {} phản hồi", danhSach.size());
        
    //     return danhSach.stream()
    //             .map(this::chuyenSangPhanHoiResponse)
    //             .collect(Collectors.toList());
    // }
    
    // @Transactional(readOnly = true)
    // public FeedbackDetailResponse layChiTietPhanHoi(Integer feedbackId) {
    //     log.info("Lấy chi tiết phản hồi: {}", feedbackId);
        
    //     Feedback feedback = feedbackRepository.findByIdWithDetails(feedbackId)
    //             .orElseThrow(() -> new ResourceNotFoundException(
    //                 //"Không tìm thấy phản hồi với mã: " + maPhanHoi
    //                 String.format(MessageFormat.PHAN_HOI_NOT_FOUND, feedbackId)
    //             ));

    //     return chuyenSangChiTietResponse(feedback);
    
    // // ✅ SỬA: Validate và convert String → Enum
    // @Transactional(readOnly = true)
    // public List<FeedbackResponseDTO> layPhanHoiTheoTrangThai(String trangThaiStr) {
    //     log.info("Lấy phản hồi theo trạng thái: {}", trangThaiStr);
        
    //     // Validate và convert
    //     if (!FeedbackStatus.enumIsValid(trangThaiStr)) {
    //         // throw new BadRequestException("Trạng thái không hợp lệ: " + trangThaiStr);
    //         throw new BadRequestException(String.format(MessageFormat.TRANG_THAI_INVALID, trangThaiStr));
    //     }
        
    //     FeedbackStatus trangThai = FeedbackStatus.fromStringToEnum(trangThaiStr);
        
    //     List<Feedback> danhSach = feedbackRepository.findByTrangThai(trangThai);
    //     log.debug("Tìm thấy {} phản hồi có trạng thái '{}'", danhSach.size(), trangThai);
        
    //     return danhSach.stream()
    //             .map(this::chuyenSangPhanHoiResponse)
    //             .collect(Collectors.toList());
    // }

    @Transactional
    public void capNhatTrangThai(Integer feeckbackId, FeedbackStatus newStatus) {
        log.info("Cập nhật trạng thái phản hồi {} sang '{}'", feeckbackId, newStatus);
        
        Feedback feedback = feedbackRepository.findById(feeckbackId)
                .orElseThrow(() -> new ResourceNotFoundException(
                    // "Không tìm thấy phản hồi: " + maPhanHoi
                    String.format(MessageFormat.PHAN_HOI_NOT_FOUND, feeckbackId)
                ));

        FeedbackStatus currentStatus = feedback.getFeedbackStatus(); 
        
        if (!currentStatus.canTransitionToNewStatus(newStatus)) {
            throw new BadRequestException(
                // String.format("Không thể chuyển từ '%s' sang '%s'",
                //     trangThaiHienTai.getDisplayName(),
                //     trangThaiMoi.getDisplayName()
                String.format(MessageFormat.TRANG_THAI_TRANSITION_INVALID,
                    currentStatus.getDisplayName(),
                    newStatus.getDisplayName())
                );
        }
        
        feedback.setFeedbackStatus(newStatus); // ← Set enum trực tiếp
        feedbackRepository.save(feedback);
        
        log.info("Đã cập nhật trạng thái phản hồi {}: {} -> {}",
            feeckbackId, currentStatus, newStatus);
    
    }
    
    
    // private FeedbackResponseDTO chuyenSangPhanHoiResponse(Feedback phanHoi) {
    //     return FeedbackResponseDTO.builder()
    //             // .FeedbackId(phanHoi.getFeedbackId())
    //             // .FeedbackTitle(phanHoi.getFeedbackTitle())
    //             // .FeedbackContent(phanHoi.getFeedbackContent())
    //             // .Status(phanHoi.getFeedbackStatus().getDisplayName()) // Chuyển enum sang String hiển thị
    //             // // .CustomerName(phanHoi.getCustomer().getCustomerName())
    //             // // .CreateAt(phanHoi.getCreateAt())
    //             // // .UpdateAt(phanHoi.getUpdateAt())
    //             // // .build();
    // }
    
//     private FeedbackDetailResponse chuyenSangChiTietResponse(Feedback phanHoi) {
//         FeedbackDetailResponse.Builder builder = FeedbackDetailResponse.builder()
//                 .FeedbackTitle(phanHoi.getFeedbackTitle())
//                 .feedbackContent(phanHoi.getFeedbackContent())
//                 .status(phanHoi.getFeedbackStatus().getDisplayName()) // Chuyển enum sang String hiển thị
//                 .createAt(phanHoi.getCreateAt())
//                 .updateAt(phanHoi.getUpdateAt())
//                 .customerName(phanHoi.getCustomer().getCustomerName())
//                 .email(phanHoi.getCustomer().getEmail())
//                 .phoneNumber(phanHoi.getCustomer().getPhoneNumber());    
//         FeedbackHandling xuLy = phanHoi.getXuLyPhanHoi();
//         if (xuLy != null) {
//             FeedbackDetailResponse.XuLyInfo xuLyInfo = new FeedbackDetailResponse.XuLyInfo();
//             xuLyInfo.setNoiDungXuLy(xuLy.getNoiDungXuLy());
//             xuLyInfo.setHinhThucGiaiQuyet(xuLy.getHinhThucGiaiQuyet().getDisplayName()); 
//             xuLyInfo.setNgayXuLy(xuLy.getNgayTao());
            
//             if (xuLy.getNhanVien() != null) {
//                 xuLyInfo.setMaNhanVien(xuLy.getNhanVien().getMaNhanVien());
//                 xuLyInfo.setTenNhanVien(xuLy.getNhanVien().getTenNhanVien());
//                 xuLyInfo.setEmailNhanVien(xuLy.getNhanVien().getEmail());
//             }
            
//             builder.xuLyInfo(xuLyInfo);
//         }
        
//         return builder.build();
//     }

//     @Transactional(readOnly = true)
//     public Long demPhanHoiTheoTrangThai(String trangThaiStr) {
//         log.info("Đếm phản hồi theo trạng thái: {}", trangThaiStr);

//         if (!FeedbackStatus.enumIsValid(trangThaiStr)) {
//             // throw new BadRequestException("Trạng thái không hợp lệ: " + trangThaiStr);
//             throw new BadRequestException(String.format(MessageFormat.TRANG_THAI_INVALID, trangThaiStr));
//         }

//         FeedbackStatus trangThai = FeedbackStatus.fromStringToEnum(trangThaiStr);
//         return phanHoiRepository.countByTrangThai(trangThai);
//     }
}