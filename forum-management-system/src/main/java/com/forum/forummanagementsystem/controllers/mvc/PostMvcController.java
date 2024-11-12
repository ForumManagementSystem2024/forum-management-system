package com.forum.forummanagementsystem.controllers.mvc;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityDuplicateException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.helpers.AuthenticationHelper;
import com.forum.forummanagementsystem.helpers.ModelMapper;
import com.forum.forummanagementsystem.models.FilterOptions;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.models.dto.FilterDto;
import com.forum.forummanagementsystem.models.dto.PostDto;
import com.forum.forummanagementsystem.services.interfaces.PostService;
import com.forum.forummanagementsystem.services.interfaces.ReplyService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostMvcController {

    private final PostService postService;
    private final ReplyService replyService;
    private final AuthenticationHelper authenticationHelper;
    private final ModelMapper modelMapper;

    @Autowired
    public PostMvcController(PostService postService, ReplyService replyService, AuthenticationHelper authenticationHelper, ModelMapper modelMapper) {
        this.postService = postService;
        this.replyService = replyService;
        this.authenticationHelper = authenticationHelper;
        this.modelMapper = modelMapper;
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
            return "blog-single";
        } catch (EntityNotFoundException e) {
            //TODO Needs to create an error page!
            return "blog-single";
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
            Post post = modelMapper.fromPostDto(postDto);
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
}
