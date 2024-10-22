package com.forum.forummanagementsystem.controllers;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.helpers.AuthenticationHelper;
import com.forum.forummanagementsystem.helpers.ModelMapper;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.Reply;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.models.dto.ReplyDto;
import com.forum.forummanagementsystem.services.interfaces.PostService;
import com.forum.forummanagementsystem.services.interfaces.ReplyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/posts/{id}/replies")
public class ReplyRestController {

    private final ReplyService replyService;
    private final PostService postService;
    private final ModelMapper modelMapper;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public ReplyRestController(ReplyService replyService,
                               PostService postService,
                               AuthenticationHelper authenticationHelper,
                               ModelMapper modelMapper) {
        this.replyService = replyService;
        this.postService = postService;
        this.modelMapper = modelMapper;
        this.authenticationHelper = authenticationHelper;
    }

    @PostMapping("/create")
    public Reply createReplyToPost(@RequestHeader HttpHeaders httpHeaders,
                                   @PathVariable int id,
                                   @Valid @RequestBody ReplyDto replyDto) {

        try {
            User user = authenticationHelper.tryGetUser(httpHeaders);
            Post post = postService.getPostById(id);
            Reply reply = modelMapper.fromReplyDto(replyDto);

            replyService.createReply(post, user, reply);

            return reply;

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }

    }


}
