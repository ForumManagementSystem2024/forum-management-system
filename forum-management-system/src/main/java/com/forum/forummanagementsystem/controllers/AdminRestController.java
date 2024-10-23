package com.forum.forummanagementsystem.controllers;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityDuplicateException;
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

    @GetMapping("/{id}")
    public User getUserById(@RequestHeader HttpHeaders httpHeaders, @PathVariable int id) {
        try {
            User userAuthenticated = authenticationHelper.tryGetUser(httpHeaders);
            adminService.getAdminById(userAuthenticated.getId());

            return userService.getUserById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PutMapping("/block/{id}")
    public void blockUser(@RequestHeader HttpHeaders httpHeaders, @PathVariable int id) {
        try {
            User userAuthenticated = authenticationHelper.tryGetUser(httpHeaders);
            adminService.getAdminByUserId(userAuthenticated.getId());

            User userToBlock = userService.getUserById(id);

            adminService.blockUser(userToBlock);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PutMapping("/unblock/{id}")
    public void unblockUser(@RequestHeader HttpHeaders httpHeaders, @PathVariable int id) {
        try {
            User userAuthenticated = authenticationHelper.tryGetUser(httpHeaders);
            adminService.getAdminByUserId(userAuthenticated.getId());

            User userToUnblock = userService.getUserById(id);

            adminService.unblockUser(userToUnblock);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public void makeAdmin(@RequestHeader HttpHeaders httpHeaders, @PathVariable int id) {
        try {
            User userAuthenticated = authenticationHelper.tryGetUser(httpHeaders);
            adminService.getAdminByUserId(userAuthenticated.getId());

            User userToMakeAdmin = userService.getUserById(id);

            adminService.makeAdmin(userToMakeAdmin);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
