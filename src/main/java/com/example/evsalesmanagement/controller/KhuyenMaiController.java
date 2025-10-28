package com.example.evsalesmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.VehicleTypeDetailDTO;
import com.example.evsalesmanagement.dto.AgencyDTO;
import com.example.evsalesmanagement.dto.PromotionDetailDTO;
import com.example.evsalesmanagement.dto.PromotionSummaryDTO;
import com.example.evsalesmanagement.dto.PromotionRequestDTO;
import com.example.evsalesmanagement.model.Promotion;
import com.example.evsalesmanagement.service.PromotionService;

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
        private PromotionService khuyenMaiService;

        @GetMapping()
        public ResponseEntity<ApiResponse<List<PromotionSummaryDTO>>> layTatCaKhuyenMai() {
                List<PromotionSummaryDTO> khuyenMaiDTOs = khuyenMaiService.layTatCaKhuyenMai().stream()
                                .map(km -> new PromotionSummaryDTO(km))
                                .toList();
                // return ResponseEntity.ok(khuyenMaiDTOs);

                return ResponseEntity.ok(new ApiResponse<List<PromotionSummaryDTO>>(true, null, khuyenMaiDTOs));
        }

        @GetMapping("/{maKhuyenMai}")
        public ResponseEntity<ApiResponse<PromotionDetailDTO>> layKhuyenMaiTheoMa(@PathVariable String maKhuyenMai) {
                // return new String();

                Promotion khuyenMai = khuyenMaiService.layKhuyenMaiTheoMa(Integer.parseInt(maKhuyenMai));

                PromotionDetailDTO khuyenMaiChiTietDTO = new PromotionDetailDTO(khuyenMai);
                khuyenMaiChiTietDTO.setChiTietLoaiXes(
                                khuyenMai.getChiTietLoaiXes()
                                                .stream()
                                                .map(ctlx -> new VehicleTypeDetailDTO(ctlx))
                                                .toList());
                khuyenMaiChiTietDTO.setDaiLys(
                                khuyenMai.getDaiLys()
                                                .stream()
                                                .map(daiLy -> new AgencyDTO(daiLy))
                                                .toList());
                // return ResponseEntity.ok(khuyenMaiChiTietDTO);

                return ResponseEntity.ok(new ApiResponse<PromotionDetailDTO>(true, null, khuyenMaiChiTietDTO));

        }

        @PostMapping()
        public ResponseEntity<ApiResponse<PromotionDetailDTO>> taoKhuyenmai(
                        @RequestBody PromotionRequestDTO khuyenMai) {

                Promotion khuyenMaiMoi = khuyenMaiService.taoKhuyenMai(khuyenMai);

                PromotionDetailDTO khuyenMaiChiTietDTO = new PromotionDetailDTO(khuyenMaiMoi);
                khuyenMaiChiTietDTO.setChiTietLoaiXes(
                                khuyenMaiMoi.getChiTietLoaiXes()
                                                .stream()
                                                .map(ctlx -> new VehicleTypeDetailDTO(ctlx))
                                                .toList());
                khuyenMaiChiTietDTO.setDaiLys(
                                khuyenMaiMoi.getDaiLys()
                                                .stream()
                                                .map(daiLy -> new AgencyDTO(daiLy))
                                                .toList());
                // return ResponseEntity.ok(khuyenMaiChiTietDTO);

                return ResponseEntity.ok(new ApiResponse<PromotionDetailDTO>(true, null, khuyenMaiChiTietDTO));

        }

        @DeleteMapping("/{maKhuyenMai}")
        public ResponseEntity<ApiResponse<PromotionDetailDTO>> xoaKhuyenMai(@PathVariable String maKhuyenMai) {
                Promotion khuyenMai = khuyenMaiService.xoaKhuyenMai(Integer.parseInt(maKhuyenMai));

                PromotionDetailDTO khuyenMaiChiTietDTO = new PromotionDetailDTO(khuyenMai);
                khuyenMaiChiTietDTO.setChiTietLoaiXes(
                                khuyenMai.getChiTietLoaiXes()
                                                .stream()
                                                .map(ctlx -> new VehicleTypeDetailDTO(ctlx))
                                                .toList());
                khuyenMaiChiTietDTO.setDaiLys(
                                khuyenMai.getDaiLys()
                                                .stream()
                                                .map(daiLy -> new AgencyDTO(daiLy))
                                                .toList());
                // return ResponseEntity.ok(khuyenMaiChiTietDTO);
                return ResponseEntity.ok(new ApiResponse<PromotionDetailDTO>(true, null, khuyenMaiChiTietDTO));

        }

        @PutMapping("/{maKhuyenMai}")
        public ResponseEntity<ApiResponse<PromotionDetailDTO>> putMethodName(@PathVariable String maKhuyenMai,
                        @RequestBody PromotionRequestDTO khuyenMai) {
                Promotion khuyenMaiCapNhat = khuyenMaiService.capKhuyenMai(Integer.parseInt(maKhuyenMai), khuyenMai);
                PromotionDetailDTO khuyenMaiChiTietDTO = new PromotionDetailDTO(khuyenMaiCapNhat);
                khuyenMaiChiTietDTO.setChiTietLoaiXes(
                                khuyenMaiCapNhat.getChiTietLoaiXes()
                                                .stream()
                                                .map(ctlx -> new VehicleTypeDetailDTO(ctlx))
                                                .toList());
                khuyenMaiChiTietDTO.setDaiLys(
                                khuyenMaiCapNhat.getDaiLys()
                                                .stream()
                                                .map(daiLy -> new AgencyDTO(daiLy))
                                                .toList());

                // return ResponseEntity.ok(khuyenMaiChiTietDTO);
                return ResponseEntity.ok(new ApiResponse<PromotionDetailDTO>(true, null, khuyenMaiChiTietDTO));

        }
}
