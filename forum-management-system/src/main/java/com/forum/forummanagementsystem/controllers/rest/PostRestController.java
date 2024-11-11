package com.forum.forummanagementsystem.controllers.rest;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityDuplicateException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.helpers.AuthenticationHelper;
import com.forum.forummanagementsystem.helpers.ModelMapper;
import com.forum.forummanagementsystem.models.FilterOptions;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.models.dto.PostDto;
import com.forum.forummanagementsystem.models.dto.PostDtoOut;
import com.forum.forummanagementsystem.services.interfaces.PostService;
import com.forum.forummanagementsystem.services.interfaces.ReplyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {

    private final PostService postService;
    private final AuthenticationHelper authenticationHelper;
    private final ModelMapper modelMapper;
    private final ReplyService replyService;

    @Autowired
    public PostRestController(PostService postService, AuthenticationHelper authenticationHelper, ModelMapper modelMapper, ReplyService replyService) {
        this.postService = postService;
        this.authenticationHelper = authenticationHelper;
        this.modelMapper = modelMapper;
        this.replyService = replyService;
    }

    @GetMapping("/most-commented")
    public List<PostDtoOut> getTopTenMostCommentedPosts() {
        List<Post> postsList = replyService.getTopTenMostCommentedPosts();

        return modelMapper.fromListPostToListPostDtoOut(postsList);
    }

    @GetMapping("/most-recent")
    public List<PostDtoOut> getTopTenMostRecentPosts() {
        List<Post> posts = postService.getTopTenMostRecentPosts();

        return modelMapper.fromListPostToListPostDtoOut(posts);
    }

    @GetMapping
    public List<PostDtoOut> getAllPosts(@RequestHeader HttpHeaders httpHeaders,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String createdByUsername,
            @RequestParam(required = false) String tagName,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder) {
        try{
            authenticationHelper.tryGetUser(httpHeaders);
            FilterOptions filterOptions = new FilterOptions(title, createdByUsername, tagName, sortBy, sortOrder);
            List<Post> postList = postService.getAllPosts(filterOptions);

            return modelMapper.fromListPostToListPostDtoOut(postList);
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public PostDtoOut getPostById(@RequestHeader HttpHeaders httpHeaders, @PathVariable int id) {
        try {
            authenticationHelper.tryGetUser(httpHeaders);

            return modelMapper.fromPostToPostDtoOut(postService.getPostById(id));

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping()
    public PostDtoOut createPost(@RequestHeader HttpHeaders headers, @Valid @RequestBody PostDto postDto) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            Post post = modelMapper.fromPostDto(postDto);
            postService.create(post, user);

            return modelMapper.fromPostToPostDtoOut(post);
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public PostDtoOut updatePost(@RequestHeader HttpHeaders headers, @PathVariable int id, @Valid @RequestBody PostDto postDto) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            Post post = modelMapper.fromPostDto(id, postDto);
            postService.update(post, user);

            return modelMapper.fromPostToPostDtoOut(post);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PutMapping("/{id}/like")
    public PostDtoOut likePost(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            postService.likePost(id, user);

            return modelMapper.fromPostToPostDtoOut(postService.getPostById(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            postService.delete(id, user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
