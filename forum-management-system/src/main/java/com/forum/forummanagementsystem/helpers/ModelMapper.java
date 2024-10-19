package com.forum.forummanagementsystem.helpers;

import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.models.dto.UserDto;
import com.forum.forummanagementsystem.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    private final UserService userService;

    @Autowired
    public ModelMapper(UserService userService) {
        this.userService = userService;
    }

    

    public User fromUserDto(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        return user;
    }
}
