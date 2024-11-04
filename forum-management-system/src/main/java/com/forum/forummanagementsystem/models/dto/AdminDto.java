package com.forum.forummanagementsystem.models.dto;

import jakarta.validation.constraints.Size;

public class AdminDto {

    @Size(max = 15)
    private String phoneNumber;

    public AdminDto() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
