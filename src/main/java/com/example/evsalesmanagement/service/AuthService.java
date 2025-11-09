package com.example.evsalesmanagement.service;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.auth.AuthResponseDTO;
import com.example.evsalesmanagement.exception.AuthenticationEntryPointException;
import com.example.evsalesmanagement.exception.InvalidRefreshTokenException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Account;
import com.example.evsalesmanagement.repository.AccountRepository;
import com.example.evsalesmanagement.utils.JwtUtil;

import ch.qos.logback.core.testUtil.RandomUtil;

@Service
public class AuthService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JwtUtil jwtUtil;

    private static long expiration = 1000 * 60 * 60; // 1h

    final private SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public AuthResponseDTO login(String userName, String password) {
        Account account = accountRepository.findByUsername(userName)
                .orElseThrow(() -> new RuntimeException("Tên đăng nhập không tồn tại"));

        if (!account.getPassword().equals(password)) {
            throw new RuntimeException("Mật khẩu không hợp lệ");
        }

        String accessToken = jwtUtil.generateToken(account.getUsername(), account.getRole().getDisplayName(),
                expiration);

        byte[] bytes = new byte[50];

        secureRandom.nextBytes(bytes);

        String refreshToken = Hex.encodeHexString(bytes);

        redisTemplate.opsForValue().set("refreshToken::" + refreshToken, account.getUsername(), 7, TimeUnit.DAYS);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setAccessToken(accessToken);
        authResponseDTO.setRefreshToken(refreshToken);
        authResponseDTO.setExpiresIn(expiration);
        return authResponseDTO;
    }

    public AuthResponseDTO getAccessTokenWithRefreshToken(String refreshToken) {

        String userName = (String) redisTemplate.opsForValue().get("refreshToken::" + refreshToken);

        if (userName == null) {
            throw new InvalidRefreshTokenException("refresh Token không hợp lệ");
        }

        Account account = accountRepository.findByUsername(userName)
                .orElseThrow(() -> new ResourceNotFoundException("Username không tồn tại"));

        String accessToken = jwtUtil.generateToken(userName, account.getRole().getDisplayName(), expiration);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setAccessToken(accessToken);
        authResponseDTO.setRefreshToken(refreshToken);
        authResponseDTO.setExpiresIn(expiration);
        return authResponseDTO;
    }

}
