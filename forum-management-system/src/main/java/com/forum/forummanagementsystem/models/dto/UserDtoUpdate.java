package com.forum.forummanagementsystem.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDtoUpdate {
    @NotNull(message = "Cannot be empty!")
    @Size(min = 4, max = 15, message = "Should be between 4 and 15 symbols!")
    private String password;

    @NotEmpty(message = "Cannot be empty!")
    @Size(min = 4, max = 32, message = "Should be between 4 and 32 symbols!")
    private String firstName;

    @NotEmpty(message = "Cannot be empty!")
    @Size(min = 4, max = 32, message = "Should be between 4 and 32 symbols!")
    private String lastName;

    @NotEmpty(message = "Cannot be empty!")
    @Size(min = 8, max = 50, message = "Should be between 8 and 50 symbols!")
    private String email;

    public UserDtoUpdate() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
