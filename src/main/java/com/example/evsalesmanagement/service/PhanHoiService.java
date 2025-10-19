package com.example.evsalesmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evsalesmanagement.dto.ChiTietPhanHoiResponse;
import com.example.evsalesmanagement.dto.PhanHoiResponse;
import com.example.evsalesmanagement.enums.TrangThaiPhanHoi;
import com.example.evsalesmanagement.exception.BadRequestException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.PhanHoi;
import com.example.evsalesmanagement.model.XuLyPhanHoi;
import com.example.evsalesmanagement.repository.PhanHoiRepository;
import com.example.evsalesmanagement.utils.MessageFormat;

@Service
public class PhanHoiService {
    
    private static final Logger log = LoggerFactory.getLogger(PhanHoiService.class);
    
    private final PhanHoiRepository phanHoiRepository;
    
    public PhanHoiService(PhanHoiRepository phanHoiRepository) {
        this.phanHoiRepository = phanHoiRepository;
    }
    
    @Transactional(readOnly = true)
    public List<PhanHoiResponse> layDanhSachPhanHoi() {
        log.info("Lấy danh sách tất cả phản hồi");
        
        List<PhanHoi> danhSach = phanHoiRepository.findAllWithKhachHang();
        log.debug("Tìm thấy {} phản hồi", danhSach.size());
        
        return danhSach.stream()
                .map(this::chuyenSangPhanHoiResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public ChiTietPhanHoiResponse layChiTietPhanHoi(Integer maPhanHoi) {
        log.info("Lấy chi tiết phản hồi: {}", maPhanHoi);
        
        PhanHoi phanHoi = phanHoiRepository.findByIdWithDetails(maPhanHoi)
                .orElseThrow(() -> new ResourceNotFoundException(
                    //"Không tìm thấy phản hồi với mã: " + maPhanHoi
                    String.format(MessageFormat.PHAN_HOI_NOT_FOUND, maPhanHoi)
                ));

        return chuyenSangChiTietResponse(phanHoi);
    }
    
    // ✅ SỬA: Validate và convert String → Enum
    @Transactional(readOnly = true)
    public List<PhanHoiResponse> layPhanHoiTheoTrangThai(String trangThaiStr) {
        log.info("Lấy phản hồi theo trạng thái: {}", trangThaiStr);
        
        // Validate và convert
        if (!TrangThaiPhanHoi.enumIsValid(trangThaiStr)) {
            // throw new BadRequestException("Trạng thái không hợp lệ: " + trangThaiStr);
            throw new BadRequestException(String.format(MessageFormat.TRANG_THAI_INVALID, trangThaiStr));
        }
        
        TrangThaiPhanHoi trangThai = TrangThaiPhanHoi.fromStringToEnum(trangThaiStr);
        
        List<PhanHoi> danhSach = phanHoiRepository.findByTrangThai(trangThai);
        log.debug("Tìm thấy {} phản hồi có trạng thái '{}'", danhSach.size(), trangThai);
        
        return danhSach.stream()
                .map(this::chuyenSangPhanHoiResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public void capNhatTrangThai(Integer maPhanHoi, TrangThaiPhanHoi trangThaiMoi) {
        log.info("Cập nhật trạng thái phản hồi {} sang '{}'", maPhanHoi, trangThaiMoi);
        
        PhanHoi phanHoi = phanHoiRepository.findById(maPhanHoi)
                .orElseThrow(() -> new ResourceNotFoundException(
                    // "Không tìm thấy phản hồi: " + maPhanHoi
                    String.format(MessageFormat.PHAN_HOI_NOT_FOUND, maPhanHoi)
                ));

        TrangThaiPhanHoi trangThaiHienTai = phanHoi.getTrangThai(); 
        
        if (!trangThaiHienTai.canTransitionToNewStatus(trangThaiMoi)) {
            throw new BadRequestException(
                // String.format("Không thể chuyển từ '%s' sang '%s'",
                //     trangThaiHienTai.getDisplayName(),
                //     trangThaiMoi.getDisplayName()
                String.format(MessageFormat.TRANG_THAI_TRANSITION_INVALID,
                    trangThaiHienTai.getDisplayName(),
                    trangThaiMoi.getDisplayName())
                );
        }
        
        phanHoi.setTrangThai(trangThaiMoi); // ← Set enum trực tiếp
        phanHoiRepository.save(phanHoi);
        
        log.info("Đã cập nhật trạng thái phản hồi {}: {} -> {}",
            maPhanHoi, trangThaiHienTai, trangThaiMoi);
    }

    
    
    private PhanHoiResponse chuyenSangPhanHoiResponse(PhanHoi phanHoi) {
        return PhanHoiResponse.builder()
                .maPhanHoi(phanHoi.getMaPhanHoi())
                .tieuDePhanHoi(phanHoi.getTieuDePhanHoi())
                .noiDungPhanHoi(phanHoi.getNoiDungPhanHoi())
                .trangThai(phanHoi.getTrangThai().getDisplayName()) 
                .tenKhachHang(phanHoi.getKhachHang().getTenKhachHang())
                .build();
    }
    
    private ChiTietPhanHoiResponse chuyenSangChiTietResponse(PhanHoi phanHoi) {
        ChiTietPhanHoiResponse.Builder builder = ChiTietPhanHoiResponse.builder()
                .tieuDePhanHoi(phanHoi.getTieuDePhanHoi())
                .noiDungPhanHoi(phanHoi.getNoiDungPhanHoi())
                .trangThai(phanHoi.getTrangThai().getDisplayName()) 
                .ngayTao(phanHoi.getNgayTao())
                .ngayCapNhat(phanHoi.getNgayCapNhat())
                .tenKhachHang(phanHoi.getKhachHang().getTenKhachHang())
                .emailKhachHang(phanHoi.getKhachHang().getEmail())
                .soDienThoaiKhachHang(phanHoi.getKhachHang().getSoDienThoai());
        
        XuLyPhanHoi xuLy = phanHoi.getXuLyPhanHoi();
        if (xuLy != null) {
            ChiTietPhanHoiResponse.XuLyInfo xuLyInfo = new ChiTietPhanHoiResponse.XuLyInfo();
            xuLyInfo.setNoiDungXuLy(xuLy.getNoiDungXuLy());
            xuLyInfo.setHinhThucGiaiQuyet(xuLy.getHinhThucGiaiQuyet().getDisplayName()); 
            xuLyInfo.setNgayXuLy(xuLy.getNgayTao());
            
            if (xuLy.getNhanVien() != null) {
                xuLyInfo.setMaNhanVien(xuLy.getNhanVien().getMaNhanVien());
                xuLyInfo.setTenNhanVien(xuLy.getNhanVien().getTenNhanVien());
                xuLyInfo.setEmailNhanVien(xuLy.getNhanVien().getEmail());
            }
            
            builder.xuLyInfo(xuLyInfo);
        }
        
        return builder.build();
    }

    @Transactional(readOnly = true)
    public Long demPhanHoiTheoTrangThai(String trangThaiStr) {
        log.info("Đếm phản hồi theo trạng thái: {}", trangThaiStr);

        if (!TrangThaiPhanHoi.enumIsValid(trangThaiStr)) {
            // throw new BadRequestException("Trạng thái không hợp lệ: " + trangThaiStr);
            throw new BadRequestException(String.format(MessageFormat.TRANG_THAI_INVALID, trangThaiStr));
        }

        TrangThaiPhanHoi trangThai = TrangThaiPhanHoi.fromStringToEnum(trangThaiStr);
        return phanHoiRepository.countByTrangThai(trangThai);
    }
}