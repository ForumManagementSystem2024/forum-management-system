package com.forum.forummanagementsystem.controllers;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityDuplicateException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.helpers.AuthenticationHelper;
import com.forum.forummanagementsystem.helpers.ModelMapper;
import com.forum.forummanagementsystem.models.FilterOptionsUser;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.models.dto.UpdateUserDto;
import com.forum.forummanagementsystem.models.dto.UserDto;
import com.forum.forummanagementsystem.models.dto.UserDtoOut;
import com.forum.forummanagementsystem.services.interfaces.AdminService;
import com.forum.forummanagementsystem.services.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AdminService adminService;
    private final AuthenticationHelper authenticationHelper;

    public UserRestController(UserService userService,
                              ModelMapper modelMapper,
                              AdminService adminService,
                              AuthenticationHelper authenticationHelper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.adminService = adminService;
        this.authenticationHelper = authenticationHelper;
    }

    @GetMapping
    public List<UserDtoOut> search(@RequestHeader HttpHeaders headers,
                                   @RequestParam(required = false) String username,
                                   @RequestParam(required = false) String email,
                                   @RequestParam(required = false) String firstName) {
        try {
            authenticationHelper.tryGetUser(headers);
            FilterOptionsUser filterOptionsUser = new FilterOptionsUser(username, email, firstName);

            List<User> userList = userService.search(filterOptionsUser);
            return modelMapper.fromListUserToListUserDtoOut(userList);
        } catch (EntityNotFoundException e) {
            //TODO: Not needed, just return empty list
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public UserDtoOut getUserById(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            authenticationHelper.tryGetUser(headers);
            User user = userService.getUserById(id);

            return modelMapper.fromUserToUserDtoOut(user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping()
    public User registerUser(@Valid @RequestBody UserDto userDto) {
        try {
            User user = modelMapper.fromUserDto(userDto);
            userService.register(user);

            return user;

        } catch (EntityNotFoundException e) {
            //TODO: Which scenario causes this exception?
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public User updateProfile(@RequestHeader HttpHeaders headers, @PathVariable int id, @Valid @RequestBody UpdateUserDto updateUserDto) {
        try {
            User userAuthenticated = authenticationHelper.tryGetUser(headers);
            User userMapped = modelMapper.fromUpdateUserDto(id, updateUserDto);
            userService.updateProfile(userAuthenticated, userMapped);
            return userMapped;
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            User userAuthenticated = authenticationHelper.tryGetUser(headers);
            //TODO: result is not used
            adminService.getAdminByUserId(userAuthenticated.getId());

            User userToDelete = userService.getUserById(id);

            //TODO: check for admin in service
            userService.deleteUser(userToDelete);

        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }
}
