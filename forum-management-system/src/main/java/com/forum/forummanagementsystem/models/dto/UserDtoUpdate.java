package com.forum.forummanagementsystem.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDtoUpdate {
    @NotNull(message = "Password cannot be empty!")
    @Size(min = 4, max = 15, message = "Password should be between 4 and 15 symbols!")
    private String password;

    @NotNull(message = "First name cannot be empty!")
    @Size(min = 4, max = 32, message = "First name should be between 4 and 32 symbols!")
    private String firstName;

    @NotNull(message = "Last name cannot be empty!")
    @Size(min = 4, max = 32, message = "Last name should be between 4 and 32 symbols!")
    private String lastName;

    @NotNull(message = "Email cannot be empty!")
    @Size(min = 8, max = 50, message = "Email should be between 8 and 50 symbols!")
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
