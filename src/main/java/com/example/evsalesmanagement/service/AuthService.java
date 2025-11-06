package com.example.evsalesmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.auth.AuthResponseDTO;
import com.example.evsalesmanagement.model.Account;
import com.example.evsalesmanagement.repository.AccountRepository;
import com.example.evsalesmanagement.utils.JwtUtil;

@Service
public class AuthService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JwtUtil jwtUtil;

    private static long expiration = 1000 * 60 * 60; // 1h

    public AuthResponseDTO login(String userName, String password) {
        Account account = accountRepository.findByUsername(userName)
                .orElseThrow(() -> new RuntimeException("Tên đăng nhập không tồn tại"));

        if (!account.getPassword().equals(password)) {
            throw new RuntimeException("Mật khẩu không hợp lệ");
        }

        String accessToken = jwtUtil.generateToken(account.getUsername(), account.getRole().name(), expiration);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setAccessToken(accessToken);
        authResponseDTO.setExpiresIn(expiration);
        return authResponseDTO;
    }

}
