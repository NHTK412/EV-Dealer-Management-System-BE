package com.example.evsalesmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.evsalesmanagement.repository.XeRepository;
import com.example.evsalesmanagement.dto.XeRequest;
import com.example.evsalesmanagement.model.Xe;

@Service
public class XeService {
    @Autowired
    private XeRepository xeRepository;

    public Xe createXe(XeRequest request) {

        Xe xe = new Xe();
        xe.setSoKhung(request.getSoKhung());
        xe.setSoMay(request.getSoMay());
        xe.setTrangThai(request.getTrangThai());
        xe.setTinhTrangXe(request.getTinhTrangXe());
        
        return xeRepository.save(xe);
    }

    public List<Xe> getAllXe() {
        return xeRepository.findAll();
    }

    public Xe getXeById(Integer xeId) {
        return xeRepository.findById(xeId).orElseThrow(() -> new RuntimeException("Không tìm thấy xe với ID: " + xeId));
    }

    public Xe updateXe(Integer xeId, XeRequest request) {
        Xe xe = getXeById(xeId);
        
        xe.setSoKhung(request.getSoKhung());
        xe.setSoMay(request.getSoMay());
        xe.setTrangThai(request.getTrangThai());
        xe.setTinhTrangXe(request.getTinhTrangXe());
        return xeRepository.save(xe);
    }

    public void deleteXe(Integer xeId) {
        xeRepository.deleteById(xeId);
    }
}
