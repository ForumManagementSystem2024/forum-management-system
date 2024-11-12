package com.forum.forummanagementsystem.helpers;

import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.models.dto.RegisterDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromDto(RegisterDto registerDto) {
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        return user;
    }
}
