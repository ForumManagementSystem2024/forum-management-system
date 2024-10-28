package com.forum.forummanagementsystem.helpers;

import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.Reply;
import com.forum.forummanagementsystem.models.Tag;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.models.dto.*;
import com.forum.forummanagementsystem.services.interfaces.PostService;
import com.forum.forummanagementsystem.services.interfaces.ReplyService;
import com.forum.forummanagementsystem.services.interfaces.TagService;
import com.forum.forummanagementsystem.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ModelMapper {

    private final PostService postService;
    private final ReplyService replyService;
    private final UserService userService;
    private final TagService tagService;

    @Autowired
    public ModelMapper(PostService postService,
                       ReplyService replyService,
                       UserService userService,
                       TagService tagService) {
        this.postService = postService;
        this.replyService = replyService;
        this.userService = userService;
        this.tagService = tagService;
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

    public User fromUpdateUserDto(int id, UserDtoUpdate userDtoUpdate) {
        User user = new User();
        user.setId(id);
        user.setUsername(userService.getUserById(id).getUsername());
        user.setFirstName(userDtoUpdate.getFirstName());
        user.setLastName(userDtoUpdate.getLastName());
        user.setPassword(userDtoUpdate.getPassword());
        user.setEmail(userDtoUpdate.getEmail());

        return user;
    }


    public UserDtoOut fromUserToUserDtoOut(User user) {
        UserDtoOut userDtoOut = new UserDtoOut();
        userDtoOut.setUsername(user.getUsername());
        userDtoOut.setFirstName(user.getFirstName());
        userDtoOut.setLastName(user.getLastName());

        return userDtoOut;
    }

    public List<UserDtoOut> fromListUserToListUserDtoOut(List<User> users) {
        if (users == null) {
            return new ArrayList<>();
        }

        return users.stream()
                .map(user -> {
                    UserDtoOut userDtoOut = new UserDtoOut();
                    userDtoOut.setUsername(user.getUsername());
                    userDtoOut.setFirstName(user.getFirstName());
                    userDtoOut.setLastName(user.getLastName());

                    return userDtoOut;
                })
                .collect(Collectors.toList());
    }

    public List<PostDto> fromListPostToListPostDto(List<Post> postsList) {
        if (postsList == null) {
            return new ArrayList<>();
        }

        return postsList.stream()
                .map(post -> {
                    PostDto postDto = new PostDto();
                    postDto.setTitle(post.getTitle());
                    postDto.setContent(post.getContent());

                    return postDto;
                })
                .collect(Collectors.toList());
    }

    public Post fromPostDto(int id, PostDto postDto) {
        Post post = fromPostDto(postDto);
        post.setId(id);

        Post repositoryPost = postService.getPostById(id);
        post.setCreatedBy(repositoryPost.getCreatedBy());
        post.setReplies(repositoryPost.getReplies());
        post.setCreatedAt(repositoryPost.getCreatedAt());
        post.setLikes(repositoryPost.getLikes());

        Set<Tag> tags = tagService.findTagsByName(postDto.getTags());
        post.setTags(tags);

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
