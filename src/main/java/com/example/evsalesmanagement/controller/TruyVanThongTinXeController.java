// package com.example.evsalesmanagement.controller;

// import com.example.evsalesmanagement.dto.TruyVanThongTinXe;
// import com.example.evsalesmanagement.dto.TruyVanThongTinXeChiTiet;
// import com.example.evsalesmanagement.service.TruyVanThongTinXeService;
// import com.example.evsalesmanagement.service.TruyVanThongTinChiTietService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/xe")
// @CrossOrigin(origins = "*")
// public class TruyVanThongTinXeController {

//     @Autowired
//     private TruyVanThongTinXeService service;

//     @Autowired
//     private TruyVanThongTinChiTietService chiTietService;

//     /**
//      * API lấy thông tin cơ bản của tất cả các loại xe
//      * GET /xe/thong-tin
//      * @return Danh sách thông tin xe
//      */
//     @GetMapping("/thong-tin")
//     public ResponseEntity<List<TruyVanThongTinXe>> layThongTinXe() {
//         try {
//             List<TruyVanThongTinXe> danhSachXe = service.layThongTinXe();
            
//             if (danhSachXe.isEmpty()) {
//                 return ResponseEntity.noContent().build();
//             }
            
//             return ResponseEntity.ok(danhSachXe);
//         } catch (Exception e) {
//             e.printStackTrace();
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//         }
//     }

//     /**
//      * API lấy thông tin chi tiết của tất cả các loại xe
//      * GET /xe/thong-tin-chi-tiet
//      * @return Danh sách thông tin chi tiết xe
//      */
//     @GetMapping("/thong-tin-chi-tiet")
//     public ResponseEntity<List<TruyVanThongTinXeChiTiet>> layThongTinChiTietXe() {
//         try {
//             List<TruyVanThongTinXeChiTiet> danhSachXe = chiTietService.layThongTinChiTietXe();
            
//             if (danhSachXe.isEmpty()) {
//                 return ResponseEntity.noContent().build();
//             }
            
//             return ResponseEntity.ok(danhSachXe);
//         } catch (Exception e) {
//             e.printStackTrace();
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//         }
//     }
// }