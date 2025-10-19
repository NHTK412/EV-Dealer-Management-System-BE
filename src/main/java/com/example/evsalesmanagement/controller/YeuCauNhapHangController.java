package com.example.evsalesmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.YeuCauNhapHangDTO;
import com.example.evsalesmanagement.dto.YeuCauNhapHangRequestDTO;
import com.example.evsalesmanagement.service.YeuCauNhapHangService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/YeuCauNhapHang")
public class YeuCauNhapHangController {

    @Autowired
    YeuCauNhapHangService yeuCauNhapHangService;

    @PostMapping()
    public ResponseEntity<YeuCauNhapHangDTO> taoYeuCauNhapHang(
            @RequestBody YeuCauNhapHangRequestDTO yeuCauNhapHangRequestDTO) {
        return ResponseEntity.ok(yeuCauNhapHangService.taoYeuCauNhapHang(yeuCauNhapHangRequestDTO));
    }

    @DeleteMapping("/{maYeuCauNhapHang}")
    public ResponseEntity<YeuCauNhapHangDTO> xoaYeuCauNhapHang(@PathVariable String maYeuCauNhapHang) {

        return ResponseEntity.ok(yeuCauNhapHangService.xoaYeuCauNhapHang(Integer.parseInt(maYeuCauNhapHang)));

    }
}
