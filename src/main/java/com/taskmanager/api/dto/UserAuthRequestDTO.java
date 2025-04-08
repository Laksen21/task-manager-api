package com.taskmanager.api.dto;

import lombok.*;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class UserAuthRequestDTO {

    private String username;
    private String password;

    public UserAuthRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
