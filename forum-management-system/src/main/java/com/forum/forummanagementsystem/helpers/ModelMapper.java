package com.forum.forummanagementsystem.helpers;

import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.Reply;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.models.dto.PostDto;
import com.forum.forummanagementsystem.models.dto.ReplyDto;
import com.forum.forummanagementsystem.models.dto.UserDto;
import com.forum.forummanagementsystem.services.interfaces.PostService;
import com.forum.forummanagementsystem.services.interfaces.ReplyService;
import com.forum.forummanagementsystem.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    private final PostService postService;
    private final ReplyService replyService;

    @Autowired
    public ModelMapper(PostService postService, ReplyService replyService) {
        this.postService = postService;
        this.replyService = replyService;
    }

    public User fromUserDto(int id, UserDto userDto) {
        User user = fromUserDto(userDto);
        user.setId(id);
        return user;
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

    public Post fromPostDto(int id, PostDto postDto) {
        Post post = fromPostDto(postDto);
        post.setId(id);
        Post repositoryPost = postService.getPostById(id);
        post.setCreatedBy(repositoryPost.getCreatedBy());

        return post;
    }

    public Post fromPostDto(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        return post;
    }

    public Reply fromReplyDto(int replyId, ReplyDto replyDto) {
        Reply reply = fromReplyDto(replyDto);
        reply.setId(replyId);
        Reply repositoryReply = replyService.getReplyById(replyId);
        reply.setCreatedBy(repositoryReply.getCreatedBy());
        reply.setPostId(repositoryReply.getPostId());

        return reply;
    }

    public Reply fromReplyDto(ReplyDto replyDto) {
        Reply reply = new Reply();
        reply.setContent(replyDto.getContent());

        return reply;
    }


}
