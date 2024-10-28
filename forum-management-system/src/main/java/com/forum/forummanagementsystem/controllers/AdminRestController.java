package com.forum.forummanagementsystem.controllers;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.helpers.AuthenticationHelper;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/admins")
public class AdminRestController {


    private final UserService userService;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public AdminRestController(
            UserService userService,
            AuthenticationHelper authenticationHelper) {

        this.userService = userService;
        this.authenticationHelper = authenticationHelper;
    }

    @GetMapping("/{id}")
    public User getUserById(@RequestHeader HttpHeaders httpHeaders, @PathVariable int id) {
        try {
            authenticationHelper.tryGetUser(httpHeaders);

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

            User userToBlock = userService.getUserById(id);

            userService.blockUser(userToBlock, userAuthenticated);

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

            User userToUnblock = userService.getUserById(id);

            userService.unblockUser(userToUnblock, userAuthenticated);

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

            User userToMakeAdmin = userService.getUserById(id);

            userService.makeAdmin(userToMakeAdmin, userAuthenticated);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
