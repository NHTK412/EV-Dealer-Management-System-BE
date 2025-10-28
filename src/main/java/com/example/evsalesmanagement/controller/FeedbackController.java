// package com.example.evsalesmanagement.controller;

// import java.util.List;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.evsalesmanagement.dto.FeedbackDetailResponse;
// import com.example.evsalesmanagement.dto.FeedbackResponseDTO;
// import com.example.evsalesmanagement.dto.FeedbackHandlingRequest;
// import com.example.evsalesmanagement.dto.FeedbackHandlingResponse;
// import com.example.evsalesmanagement.service.FeedbackService;
// import com.example.evsalesmanagement.service.FeedbackHandlingService;
// import com.example.evsalesmanagement.utils.ApiResponse;
// import com.example.evsalesmanagement.utils.MessageFormat;

// import jakarta.validation.Valid;
// import jakarta.validation.constraints.Positive;

// /**
//  * Controller cho chức năng "Ghi nhận và xử lý phản hồi"
//  */
// @RestController
// @RequestMapping("/phanHoi")
// @Validated
// public class FeedbackController {

//         private static final Logger log = LoggerFactory.getLogger(FeedbackController.class);

//         private final FeedbackService phanHoiService;
//         private final FeedbackHandlingService xuLyPhanHoiService;

//         public FeedbackController(
//                         FeedbackService phanHoiService,
//                         FeedbackHandlingService xuLyPhanHoiService) {
//                 this.phanHoiService = phanHoiService;
//                 this.xuLyPhanHoiService = xuLyPhanHoiService;
//         }

//         /**
//          * API 1: Xem danh sách phản hồi
//          * GET /api/phan-hoi
//          * GET /api/phan-hoi?trangThai=Chưa xử lý
//          * GET /api/phan-hoi?trangThai=Đang xử lý
//          * GET /api/phan-hoi?trangThai=Đã xử lý
//          */
//         @GetMapping
//         public ResponseEntity<ApiResponse<List<FeedbackResponseDTO>>> layDanhSachPhanHoi(
//                         @RequestParam(required = false) String trangThai) {

//                 log.info("API: Lấy danh sách phản hồi - Trạng thái: {}", trangThai);

//                 List<FeedbackResponseDTO> danhSach = trangThai != null
//                                 ? phanHoiService.layPhanHoiTheoTrangThai(trangThai)
//                                 : phanHoiService.layDanhSachPhanHoi();

//                 // return ResponseEntity.ok(
//                 // new ApiResponse<>(true, "Lấy danh sách phản hồi thành công", danhSach)
//                 // );

//                 return ResponseEntity.ok(
//                                 new ApiResponse<>(
//                                                 true,
//                                                 MessageFormat.PHAN_HOI_LIST_SUCCESS,
//                                                 danhSach));
//         }

//         /**
//          * API 2: Xem chi tiết phản hồi
//          * GET /api/phan-hoi/{id}
//          */
//         @GetMapping("/{maPhanHoi}")
//         public ResponseEntity<ApiResponse<FeedbackDetailResponse>> layChiTietPhanHoi(
//                         @PathVariable @Positive Integer maPhanHoi) {

//                 log.info("API: Lấy chi tiết phản hồi {}", maPhanHoi);

//                 FeedbackDetailResponse response = phanHoiService.layChiTietPhanHoi(maPhanHoi);

//                 // return ResponseEntity.ok(
//                 // new ApiResponse<>(true, "Lấy chi tiết phản hồi thành công", response)
//                 // );

//                 return ResponseEntity.ok(
//                                 new ApiResponse<>(
//                                                 true,
//                                                 MessageFormat.PHAN_HOI_DETAIL_SUCCESS,
//                                                 response));
//         }

//         /**
//          * API 3: Xử lý phản hồi
//          * POST /api/phan-hoi/{id}/xu-ly
//          */
//         @PostMapping("/{maPhanHoi}/xuLy")
//         public ResponseEntity<ApiResponse<FeedbackHandlingResponse>> xuLyPhanHoi(
//                         @PathVariable @Positive Integer maPhanHoi,
//                         @Valid @RequestBody FeedbackHandlingRequest request) {

//                 log.info("API: Xử lý phản hồi {}", maPhanHoi);

//                 // Lấy maNhanVien từ JWT token
//                 Integer maNhanVien = 3; // Hard-code tạm

//                 FeedbackHandlingResponse response = xuLyPhanHoiService.xuLyPhanHoi(
//                                 maPhanHoi, request, maNhanVien);

//                 // return ResponseEntity.status(HttpStatus.CREATED).body(
//                 // new ApiResponse<>(true, "Xử lý phản hồi thành công", response)
//                 // );
//                 return ResponseEntity.status(HttpStatus.CREATED).body(
//                                 new ApiResponse<>(
//                                                 true,
//                                                 MessageFormat.XU_LY_PHAN_HOI_SUCCESS,
//                                                 response));
//         }

//         /**
//          * API 4: Đếm phản hồi theo trạng thái
//          * GET /phan-hoi/thongKe?trangThai=Đã xử lý
//          * GET /phan-hoi/thongKe?trangThai=Đang xử lý
//          * GET /phan-hoi/thongKe?trangThai=Chưa xử lý
//          */
//         @GetMapping("/thongKe")
//         public ResponseEntity<ApiResponse<Long>> demPhanHoiTheoTrangThai(
//                         @RequestParam String trangThai) {

//                 log.info("API: Đếm phản hồi theo trạng thái {}", trangThai);

//                 Long soLuong = phanHoiService.demPhanHoiTheoTrangThai(trangThai);

//                 // return ResponseEntity.ok(
//                 // new ApiResponse<>(true, "Đếm phản hồi thành công", soLuong)
//                 // );
//                 return ResponseEntity.ok(
//                                 new ApiResponse<>(
//                                                 true,
//                                                 MessageFormat.PHAN_HOI_COUNT_SUCCESS,
//                                                 soLuong));
//         }

// }