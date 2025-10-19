package com.example.evsalesmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.XeDTO;
import com.example.evsalesmanagement.model.Xe;
import com.example.evsalesmanagement.service.XeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;







@RestController
@RequestMapping("/xe")
public class XeController {
    @Autowired
    private XeService xeService;

    @PostMapping
    Xe createXe(@RequestBody XeDTO request) {
       return xeService.createXe(request);
    }

    @PostMapping("/batch")
    public List<Xe> createXeBatch(@RequestBody List<XeDTO> requests) {
        List<Xe> result = new ArrayList<>();
        for (XeDTO req : requests) {
            result.add(xeService.createXe(req));
        }
        return result;
    }

    @GetMapping
    List<Xe> getAllXe() {
        return xeService.getAllXe();
    }

    @GetMapping("/{xeId}")
    Xe getXeById(@PathVariable Integer xeId) {
        return xeService.getXeById(xeId); 
    }
    
    @PutMapping("/{xeId}")
    Xe updateXe(@PathVariable("xeId") Integer xeId, @RequestBody XeDTO request) {
        return xeService.updateXe(xeId, request);
    }
    
    @DeleteMapping("/{xeId}")
    String deleteXe(@PathVariable Integer xeId) {
        xeService.deleteXe(xeId);
        return "Deleted Successfully";
    }
    
}
