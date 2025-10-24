package com.example.evsalesmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.YeuCauNhapHangDTO;
import com.example.evsalesmanagement.dto.YeuCauNhapHangRequestDTO;
import com.example.evsalesmanagement.service.YeuCauNhapHangService;

import jakarta.validation.constraints.Positive;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/yeuCauNhapHang")
public class YeuCauNhapHangController {

    @Autowired
    YeuCauNhapHangService yeuCauNhapHangService;

    // Chưa tạo logic gửi thông báo đến đại lý
    @PostMapping()
    public ResponseEntity<YeuCauNhapHangDTO> taoYeuCauNhapHang(
            @RequestBody YeuCauNhapHangRequestDTO yeuCauNhapHangRequestDTO) {
        return ResponseEntity.ok(yeuCauNhapHangService.taoYeuCauNhapHang(yeuCauNhapHangRequestDTO));
    }

    @DeleteMapping("/{maYeuCauNhapHang}")
    public ResponseEntity<YeuCauNhapHangDTO> xoaYeuCauNhapHang(@PathVariable String maYeuCauNhapHang) {

        return ResponseEntity.ok(yeuCauNhapHangService.xoaYeuCauNhapHang(Integer.parseInt(maYeuCauNhapHang)));
    }

    // @PutMapping("/{maYeuCauNhapHang}")
    // @Operation(summary = "Cập nhật yêu cầu nhập hàng")
    // @ApiResponses(value = {
    // @ApiResponse(responseCode = "200", description = "Thành công"),
    // @ApiResponse(responseCode = "400", description = "Dữ liệu không hợp lệ"),
    // @ApiResponse(responseCode = "404", description = "Không tìm thấy yêu cầu nhập
    // hàng"),
    // @ApiResponse(responseCode = "409", description = "Xung đột dữ liệu")
    // })
    // public ResponseEntity<YeuCauNhapHangDTO> chinhSuaYeuCauNhapHang(@PathVariable
    // String maYeuCauNhapHang,
    // @RequestBody YeuCauNhapHangRequestDTO yeuCauNhapHangRequestDTO) {
    // return
    // ResponseEntity.ok(yeuCauNhapHangService.chinhSuaYeuCauNhapHang(Integer.parseInt(maYeuCauNhapHang),
    // yeuCauNhapHangRequestDTO));
    // }

    @PutMapping("/{maYeuCauNhapHang}")
    public ResponseEntity<YeuCauNhapHangDTO> chinhSuaYeuCauNhapHang(
            @PathVariable String maYeuCauNhapHang,
            @RequestBody YeuCauNhapHangRequestDTO yeuCauNhapHangRequestDTO) {
        return ResponseEntity.ok(yeuCauNhapHangService.chinhSuaYeuCauNhapHang(Integer.parseInt(maYeuCauNhapHang),
                yeuCauNhapHangRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<YeuCauNhapHangDTO>> layTatCaYeuCauNhapHang(@RequestParam Integer page,
            @RequestParam @Positive Integer size,
            @RequestParam(required = false) Integer maNhanVien) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(yeuCauNhapHangService.layTatCaYeuCauNhapHang(pageable, maNhanVien));
    }


    @GetMapping("/{maYeuCauNhapHang}")
    public ResponseEntity<YeuCauNhapHangDTO> layChiTietYeuCauNhapHang(@PathVariable Integer maYeuCauNhapHang) {
        return ResponseEntity.ok(yeuCauNhapHangService.layChiTietYeuCauNhapHang(maYeuCauNhapHang));
    }

    // @GetMapping("/nhanVien/{maNhanVien}")
    // public ResponseEntity<List<YeuCauNhapHangDTO>>
    // layTatCaYeuCauNhapHangTheoNhanVien(
    // @PathVariable Integer maYeuCauNhapHang) {
    // return
    // ResponseEntity.ok(yeuCauNhapHangService.layChiTietKhuyenMai(maYeuCauNhapHang));
    // }

}
