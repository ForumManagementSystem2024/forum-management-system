package com.forum.forummanagementsystem.models.dto;

import jakarta.validation.constraints.NotEmpty;

public class LoginDto {

    @NotEmpty(message = "Cannot be empty!")
    private String username;

    @NotEmpty(message = "Cannot be empty!")
    private String password;

    public LoginDto() {
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
