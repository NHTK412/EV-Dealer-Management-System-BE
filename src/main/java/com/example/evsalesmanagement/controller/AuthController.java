package com.example.evsalesmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.auth.AuthRequestDTO;
import com.example.evsalesmanagement.dto.auth.AuthResponseDTO;
import com.example.evsalesmanagement.service.AuthService;
import com.example.evsalesmanagement.utils.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDTO>> postMethodName(@RequestBody AuthRequestDTO authRequestDTO) {
        AuthResponseDTO authResponseDTO = authService.login(authRequestDTO.getUsername(), authRequestDTO.getPassword());
        return ResponseEntity.ok(new ApiResponse<AuthResponseDTO>(true, null, authResponseDTO));
    }

}
