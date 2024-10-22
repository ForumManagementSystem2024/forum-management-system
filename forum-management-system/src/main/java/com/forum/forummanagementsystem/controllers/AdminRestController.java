package com.forum.forummanagementsystem.controllers;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.helpers.AuthenticationHelper;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.services.interfaces.AdminService;
import com.forum.forummanagementsystem.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/admins")
public class AdminRestController {

    private final AdminService adminService;
    private final UserService userService;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public AdminRestController(
            AdminService adminService,
            UserService userService,
            AuthenticationHelper authenticationHelper) {

        this.adminService = adminService;
        this.userService = userService;
        this.authenticationHelper = authenticationHelper;
    }

    @PutMapping("/block/{id}")
    public void blockUser(@RequestHeader HttpHeaders httpHeaders, @PathVariable int id) {
        try {
            User userAuthenticated = authenticationHelper.tryGetUser(httpHeaders);
            adminService.getAdminById(userAuthenticated.getId());

            User userToBlock = userService.getUserById(id);

            adminService.blockUser(userToBlock);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
