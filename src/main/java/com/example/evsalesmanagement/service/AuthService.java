package com.example.evsalesmanagement.service;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.auth.AuthResponseDTO;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.repository.EmployeeRepository;
import com.example.evsalesmanagement.exception.InvalidRefreshTokenException;
import com.example.evsalesmanagement.utils.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    JwtUtil jwtUtil;

    // private static long expiration = 1000 * 60 * 60 * 4; // 4h

    @Value("${jwt.expiration-time}")
    private long expiration;

    @Value("${refresh-token.expiration-time}")
    private long refreshTokenExpiration;

    final private SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public AuthResponseDTO login(String userName, String password) {
        Employee employee = employeeRepository.findByUsername(userName)
                .orElseThrow(() -> new InvalidRefreshTokenException("Invalid username or password"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(password, employee.getPassword())) {
            throw new InvalidRefreshTokenException("Invalid username or password");
        }

        String accessToken = jwtUtil.generateToken(employee.getUsername(), employee.getRole().name(),
                expiration);

        byte[] bytes = new byte[50];

        secureRandom.nextBytes(bytes);

        String refreshToken = Hex.encodeHexString(bytes);

        redisTemplate.opsForValue().set("refreshToken::" + refreshToken, employee.getUsername(), refreshTokenExpiration,
                TimeUnit.MILLISECONDS);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setUsername(employee.getUsername());
        authResponseDTO.setRole(employee.getRole().name());
        authResponseDTO.setAccessToken(accessToken);
        authResponseDTO.setRefreshToken(refreshToken);
        authResponseDTO.setExpiresIn(expiration);
        return authResponseDTO;
    }

    public AuthResponseDTO getAccessTokenWithRefreshToken(String refreshToken) {

        String userName = (String) redisTemplate.opsForValue().get("refreshToken::" + refreshToken);

        if (userName == null) {
            throw new InvalidRefreshTokenException("Invalid refresh token");
        }

        Employee employee = employeeRepository.findByUsername(userName)
                .orElseThrow(() -> new InvalidRefreshTokenException("Invalid refresh token"));

        String accessToken = jwtUtil.generateToken(employee.getUsername(), employee.getRole().name(),
                expiration);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setUsername(employee.getUsername());
        authResponseDTO.setRole(employee.getRole().name());
        authResponseDTO.setAccessToken(accessToken);
        authResponseDTO.setRefreshToken(refreshToken);
        authResponseDTO.setExpiresIn(expiration);
        return authResponseDTO;
    }

}
