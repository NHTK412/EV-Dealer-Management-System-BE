package com.example.evsalesmanagement.dto.auth;

public class AuthResponseDTO {
    private String username;
    private String role;
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;

    public AuthResponseDTO() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

// <<<<<<< HEAD
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
// =======
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
// >>>>>>> feat/Khang/cauHinhRedis
    }

}
