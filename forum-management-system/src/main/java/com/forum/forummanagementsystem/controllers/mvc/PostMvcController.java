package com.forum.forummanagementsystem.controllers.mvc;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityDuplicateException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.helpers.AuthenticationHelper;
import com.forum.forummanagementsystem.helpers.ModelMapper;
import com.forum.forummanagementsystem.models.*;
import com.forum.forummanagementsystem.models.dto.FilterDto;
import com.forum.forummanagementsystem.models.dto.PostDto;
import com.forum.forummanagementsystem.models.dto.ReplyDto;
import com.forum.forummanagementsystem.services.interfaces.PostService;
import com.forum.forummanagementsystem.services.interfaces.ReplyService;
import com.forum.forummanagementsystem.services.interfaces.TagService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/posts")
public class PostMvcController {

    private final PostService postService;
    private final ReplyService replyService;
    private final AuthenticationHelper authenticationHelper;
    private final ModelMapper modelMapper;
    private final TagService tagService;

    @Autowired
    public PostMvcController(PostService postService, ReplyService replyService, AuthenticationHelper authenticationHelper, ModelMapper modelMapper, TagService tagService) {
        this.postService = postService;
        this.replyService = replyService;
        this.authenticationHelper = authenticationHelper;
        this.modelMapper = modelMapper;
        this.tagService = tagService;
    }

    @GetMapping()
    public String getAllPosts(@ModelAttribute FilterDto filterDto, Model model) {
        FilterOptions filterOptions = new FilterOptions(
                filterDto.getTitle(),
                filterDto.getCreatedByUsername(),
                filterDto.getTagName(),
                filterDto.getSortBy(),
                filterDto.getSortOrder());
        model.addAttribute("posts", postService.getAllPosts(filterOptions));
        model.addAttribute("filterDto", filterDto);
        return "posts-view";
    }

    @GetMapping("/{id}")
    public String showSinglePost(@PathVariable int id, Model model) {
        try {
            Post post = postService.getPostById(id);
            model.addAttribute("post", post);
            return "post-view";
        } catch (EntityNotFoundException e) {
            //TODO Needs to create an error page!
            return "post-view";
        }
    }

    @GetMapping("/most-commented")
    public String getTopTenMostCommentedPosts(Model model) {
        model.addAttribute("posts", replyService.getTopTenMostCommentedPosts());
        return "index";
    }

    @GetMapping("/new")
    public String showCreateNewPostView(Model model, HttpSession session) {
        model.addAttribute("post", new PostDto());
        return "post-create";
    }

    @PostMapping("/new")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto,
                             BindingResult bindingResult,
                             @RequestParam(name = "tags", required = false) String tagsInput,
                             Model model,
                             HttpSession session) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        if (bindingResult.hasErrors()) {
            return "post-create";
        }

        try {
            Set<String> tagNames = modelMapper.fromStringToSetStrings(tagsInput);
            postDto.setTags(tagNames);
            Set<Tag> tags = tagService.findTagsByName(tagNames);

            Post post = modelMapper.fromPostDto(postDto);
            post.setTags(tags);

            postService.create(post, user);
            return "redirect:/posts";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            //TODO Needs to create an error page!
            return "error";
        } catch (EntityDuplicateException e) {
            //TODO Needs to create an error page!
            //TODO Is this the correct binding result?
            bindingResult.rejectValue("name", "duplicate_post", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{postId}/reply")
    public String showCreateNewReplyView(@PathVariable int postId, Model model) {
        model.addAttribute("reply", new ReplyDto());
        model.addAttribute("postId", postId);
        return "reply-create-new";
    }

    @PostMapping("/{postId}/reply")
    public String createReply(@Valid @ModelAttribute("reply") ReplyDto replyDto,
                              BindingResult bindingResult,
                              Model model,
                              HttpSession session,
                              @PathVariable("postId") int postId) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        if (bindingResult.hasErrors()) {
            return "reply-create-new";
        }

        try {
            Post post = postService.getPostById(postId);
            Reply reply = modelMapper.fromReplyDto(replyDto);
//            model.addAttribute("reply", reply);

            replyService.createReply(post, user, reply);
            return "redirect:/posts/{postId}";

        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            //TODO Needs to create an error page!
            return "error";
        }

    }

}
