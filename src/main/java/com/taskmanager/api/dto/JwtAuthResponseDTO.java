package com.taskmanager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class JwtAuthResponseDTO {
    private String token;
    private String username;

    public JwtAuthResponseDTO(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public JwtAuthResponseDTO(String message) {
        this.token = message;
        this.username = null;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

