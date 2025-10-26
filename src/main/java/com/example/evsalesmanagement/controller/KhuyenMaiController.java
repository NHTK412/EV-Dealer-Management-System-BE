package com.example.evsalesmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.ChiTietLoaiXeDTO;
import com.example.evsalesmanagement.dto.AgencyDTO;
import com.example.evsalesmanagement.dto.KhuyenMaiChiTietDTO;
import com.example.evsalesmanagement.dto.KhuyenMaiDTO;
import com.example.evsalesmanagement.dto.KhuyenMaiRequestDTO;
import com.example.evsalesmanagement.model.Promotion;
import com.example.evsalesmanagement.service.KhuyenMaiService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.evsalesmanagement.utils.ApiResponse;

@RestController
@RequestMapping("/khuyenMai")
public class KhuyenMaiController {

        @Autowired
        private KhuyenMaiService khuyenMaiService;

        @GetMapping()
        public ResponseEntity<ApiResponse<List<KhuyenMaiDTO>>> layTatCaKhuyenMai() {
                List<KhuyenMaiDTO> khuyenMaiDTOs = khuyenMaiService.layTatCaKhuyenMai().stream()
                                .map(km -> new KhuyenMaiDTO(km))
                                .toList();
                // return ResponseEntity.ok(khuyenMaiDTOs);

                return ResponseEntity.ok(new ApiResponse<List<KhuyenMaiDTO>>(true, null, khuyenMaiDTOs));
        }

        @GetMapping("/{maKhuyenMai}")
        public ResponseEntity<ApiResponse<KhuyenMaiChiTietDTO>> layKhuyenMaiTheoMa(@PathVariable String maKhuyenMai) {
                // return new String();

                Promotion khuyenMai = khuyenMaiService.layKhuyenMaiTheoMa(Integer.parseInt(maKhuyenMai));

                KhuyenMaiChiTietDTO khuyenMaiChiTietDTO = new KhuyenMaiChiTietDTO(khuyenMai);
                khuyenMaiChiTietDTO.setChiTietLoaiXes(
                                khuyenMai.getChiTietLoaiXes()
                                                .stream()
                                                .map(ctlx -> new ChiTietLoaiXeDTO(ctlx))
                                                .toList());
                khuyenMaiChiTietDTO.setDaiLys(
                                khuyenMai.getDaiLys()
                                                .stream()
                                                .map(daiLy -> new AgencyDTO(daiLy))
                                                .toList());
                // return ResponseEntity.ok(khuyenMaiChiTietDTO);

                return ResponseEntity.ok(new ApiResponse<KhuyenMaiChiTietDTO>(true, null, khuyenMaiChiTietDTO));

        }

        @PostMapping()
        public ResponseEntity<ApiResponse<KhuyenMaiChiTietDTO>> taoKhuyenmai(
                        @RequestBody KhuyenMaiRequestDTO khuyenMai) {

                Promotion khuyenMaiMoi = khuyenMaiService.taoKhuyenMai(khuyenMai);

                KhuyenMaiChiTietDTO khuyenMaiChiTietDTO = new KhuyenMaiChiTietDTO(khuyenMaiMoi);
                khuyenMaiChiTietDTO.setChiTietLoaiXes(
                                khuyenMaiMoi.getChiTietLoaiXes()
                                                .stream()
                                                .map(ctlx -> new ChiTietLoaiXeDTO(ctlx))
                                                .toList());
                khuyenMaiChiTietDTO.setDaiLys(
                                khuyenMaiMoi.getDaiLys()
                                                .stream()
                                                .map(daiLy -> new AgencyDTO(daiLy))
                                                .toList());
                // return ResponseEntity.ok(khuyenMaiChiTietDTO);

                return ResponseEntity.ok(new ApiResponse<KhuyenMaiChiTietDTO>(true, null, khuyenMaiChiTietDTO));

        }

        @DeleteMapping("/{maKhuyenMai}")
        public ResponseEntity<ApiResponse<KhuyenMaiChiTietDTO>> xoaKhuyenMai(@PathVariable String maKhuyenMai) {
                Promotion khuyenMai = khuyenMaiService.xoaKhuyenMai(Integer.parseInt(maKhuyenMai));

                KhuyenMaiChiTietDTO khuyenMaiChiTietDTO = new KhuyenMaiChiTietDTO(khuyenMai);
                khuyenMaiChiTietDTO.setChiTietLoaiXes(
                                khuyenMai.getChiTietLoaiXes()
                                                .stream()
                                                .map(ctlx -> new ChiTietLoaiXeDTO(ctlx))
                                                .toList());
                khuyenMaiChiTietDTO.setDaiLys(
                                khuyenMai.getDaiLys()
                                                .stream()
                                                .map(daiLy -> new AgencyDTO(daiLy))
                                                .toList());
                // return ResponseEntity.ok(khuyenMaiChiTietDTO);
                return ResponseEntity.ok(new ApiResponse<KhuyenMaiChiTietDTO>(true, null, khuyenMaiChiTietDTO));

        }

        @PutMapping("/{maKhuyenMai}")
        public ResponseEntity<ApiResponse<KhuyenMaiChiTietDTO>> putMethodName(@PathVariable String maKhuyenMai,
                        @RequestBody KhuyenMaiRequestDTO khuyenMai) {
                Promotion khuyenMaiCapNhat = khuyenMaiService.capKhuyenMai(Integer.parseInt(maKhuyenMai), khuyenMai);
                KhuyenMaiChiTietDTO khuyenMaiChiTietDTO = new KhuyenMaiChiTietDTO(khuyenMaiCapNhat);
                khuyenMaiChiTietDTO.setChiTietLoaiXes(
                                khuyenMaiCapNhat.getChiTietLoaiXes()
                                                .stream()
                                                .map(ctlx -> new ChiTietLoaiXeDTO(ctlx))
                                                .toList());
                khuyenMaiChiTietDTO.setDaiLys(
                                khuyenMaiCapNhat.getDaiLys()
                                                .stream()
                                                .map(daiLy -> new AgencyDTO(daiLy))
                                                .toList());

                // return ResponseEntity.ok(khuyenMaiChiTietDTO);
                return ResponseEntity.ok(new ApiResponse<KhuyenMaiChiTietDTO>(true, null, khuyenMaiChiTietDTO));

        }
}
