package com.example.evsalesmanagement.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.evsalesmanagement.model.DaiLy;
import com.example.evsalesmanagement.repository.DaiLyRepository;
import com.example.evsalesmanagement.dto.DaiLyRequest;

@Service
public class DaiLyService {
    @Autowired
    private DaiLyRepository daiLyRepository;

    public DaiLy createDaiLy(DaiLyRequest request) {
        DaiLy daiLy = new DaiLy();
        daiLy.setTenDaiLy(request.getTenDaiLy());
        daiLy.setDiaChi(request.getDiaChi());
        daiLy.setSoDienThoai(request.getSoDienThoai());
        daiLy.setEmail(request.getEmail());
        daiLy.setTrangThai(request.getTrangThai());
        return daiLyRepository.save(daiLy);
    }

    public List<DaiLy> getAllDaiLy() {
        return daiLyRepository.findAll();
    }

    public DaiLy getDaiLyById(Integer daiLyId) {
        return daiLyRepository.findById(daiLyId).orElseThrow(() -> new RuntimeException("Không tìm thấy đại lý"));
    }

    public DaiLy updateDaiLy(Integer daiLyId, DaiLyRequest request) {
        DaiLy daiLy = getDaiLyById(daiLyId);

        daiLy.setTenDaiLy(request.getTenDaiLy());
        daiLy.setDiaChi(request.getDiaChi());
        daiLy.setSoDienThoai(request.getSoDienThoai());
        daiLy.setEmail(request.getEmail());
        daiLy.setTrangThai(request.getTrangThai());
        return daiLyRepository.save(daiLy);
    }
}
