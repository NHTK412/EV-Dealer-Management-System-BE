package com.example.evsalesmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.example.evsalesmanagement.utils.ApiResponse;

@RestController
@RequestMapping("/xe")
public class XeController {
    @Autowired
    private XeService xeService;

    @PostMapping
    ResponseEntity<ApiResponse<Xe>> createXe(@RequestBody XeDTO request) {
        // return xeService.createXe(request);

        return ResponseEntity.ok(new ApiResponse<Xe>(true, null, xeService.createXe(request)));

    }

    @PostMapping("/themNhieu")
    public ResponseEntity<ApiResponse<List<Xe>>> createXeBatch(@RequestBody List<XeDTO> requests) {
        List<Xe> result = new ArrayList<>();
        for (XeDTO req : requests) {
            result.add(xeService.createXe(req));
        }
        // return result;

        return ResponseEntity.ok(new ApiResponse<List<Xe>>(true, null, result));

    }

    @GetMapping
    ResponseEntity<ApiResponse<List<Xe>>> getAllXe() {
        // return xeService.getAllXe();
        return ResponseEntity.ok(new ApiResponse<List<Xe>>(true, null, xeService.getAllXe()));

    }

    @GetMapping("/{maXe}")
    ResponseEntity<ApiResponse<Xe>> getXeById(@PathVariable Integer maXe) {
        // return xeService.getXeById(maXe);
        return ResponseEntity.ok(new ApiResponse<Xe>(true, null, xeService.getXeById(maXe)));

    }

    @PutMapping("/{maXe}")
    ResponseEntity<ApiResponse<Xe>> updateXe(@PathVariable Integer maXe, @RequestBody XeDTO request) {
        // return xeService.updateXe(maXe, request);
        return ResponseEntity.ok(new ApiResponse<Xe>(true, null, xeService.updateXe(maXe, request)));

    }

    @DeleteMapping("/{maXe}")
    String deleteXe(@PathVariable Integer maXe) {
        xeService.deleteXe(maXe);
        return "Deleted Successfully";
    }

}
