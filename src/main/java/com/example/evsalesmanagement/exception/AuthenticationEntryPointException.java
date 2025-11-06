package com.example.evsalesmanagement.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEntryPointException implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
        // response.setContentType("application/json;charset=UTF-8");
        // response.getWriter().write("{\"error\": \"Bạn chưa được xác thực hoặc token không hợp lệ!\"}");
    }
}