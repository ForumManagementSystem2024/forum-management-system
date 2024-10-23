package com.forum.forummanagementsystem.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDtoOut {

    @NotNull(message = "Username cannot be empty!")
    @Size(min = 4, max = 20, message = "Username should be between 4 and 20 symbols!")
    private String username;

    @NotNull(message = "First name cannot be empty!")
    @Size(min = 4, max = 32, message = "First name should be between 4 and 32 symbols!")
    private String firstName;

    @NotNull(message = "Last name cannot be empty!")
    @Size(min = 4, max = 32, message = "Last name should be between 4 and 32 symbols!")
    private String lastName;

    public UserDtoOut() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
