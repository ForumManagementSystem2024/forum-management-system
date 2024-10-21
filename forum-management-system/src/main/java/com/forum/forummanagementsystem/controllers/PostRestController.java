package com.forum.forummanagementsystem.controllers;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.helpers.AuthenticationHelper;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.services.interfaces.PostService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {

    private final PostService postService;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public PostRestController(PostService postService, AuthenticationHelper authenticationHelper) {
        this.postService = postService;
        this.authenticationHelper = authenticationHelper;
    }

    @GetMapping("/{id}")
    public Post getPostById(@RequestHeader HttpHeaders httpHeaders, @PathVariable int id) {
        try {
            User user = authenticationHelper.tryGetUser(httpHeaders);

            return postService.getPostById(id);

        } catch (EntityNotFoundException e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e){

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
