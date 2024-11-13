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

    @ModelAttribute("currentLoggedInUser")
    public User getCurrentUserFromSession(HttpSession session) {
        return authenticationHelper.tryGetCurrentUser(session);
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
    public String showSinglePost(@PathVariable int id, Model model, HttpSession session) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        try {
            Post post = postService.getPostById(id);
            model.addAttribute("post", post);
            model.addAttribute("hasUserLikedPost", postService.hasUserLikedPost(id, user));
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

    @GetMapping("/{id}/update")
    public String showEditPostPage(@PathVariable int id, Model model, HttpSession session) {
        try {
            authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        try {
            Post post = postService.getPostById(id);
            Set<String> tagSetStrings = modelMapper.fromSetTagToSetStrings(post.getTags());
            PostDto postDto = modelMapper.fromPostToPostDto(post);
            postDto.setTags(tagSetStrings);
            model.addAttribute("postId", id);
            model.addAttribute("post", postDto);

            return "post-update";
        } catch (UnsupportedOperationException e) {
            return "error";
        }
    }

    @PostMapping("/{id}/update")
    public String updatePost(@PathVariable int id,
                             @Valid @ModelAttribute("post") PostDto postDto,
                             BindingResult bindingResult,
                             Model model,
                             HttpSession session,
                             @RequestParam(name = "tags", required = false) String tagsInput) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("postId", id);
            return "post-update";
        }

        try {
            // Setting the tags from a string in the request params
            Set<String> tagNames = modelMapper.fromStringToSetStrings(tagsInput);
            postDto.setTags(tagNames);
            Set<Tag> tags = tagService.findTagsByName(tagNames);

            Post post = modelMapper.fromPostDto(id, postDto);
            post.setTags(tags);

            postService.update(post, user);
            return "redirect:/posts/" + id;
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "error";
        } catch (EntityDuplicateException e) {
            bindingResult.rejectValue("name", "duplicate_post", e.getMessage());
            return "post-update";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable int id, Model model, HttpSession session) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        try {
            postService.delete(id, user);
            return "redirect:/posts";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "error";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{postId}/reply")
    public String showCreateNewReplyView(@PathVariable int postId, Model model) {
        model.addAttribute("reply", new ReplyDto());
        model.addAttribute("postId", postId);
        return "reply-create";
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
            return "reply-create";
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

    @GetMapping("/{id}/reply/{replyId}/update")
    public String showEditReplyPage(@PathVariable int id,
                                    @PathVariable int replyId,
                                    Model model,
                                    HttpSession session) {
        try {
            authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        try {
            Reply reply = replyService.getReplyById(replyId);
            model.addAttribute("reply", reply);
            model.addAttribute("postId", id);

            return "reply-update";
        } catch (UnsupportedOperationException e) {
            return "error";
        }
    }

    @PostMapping("/{id}/reply/{replyId}/update")
    public String updateReply(@PathVariable int id,
                              @PathVariable int replyId,
                              @Valid @ModelAttribute("reply") ReplyDto replyDto,
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
            model.addAttribute("postId", id);
            model.addAttribute("replyId", replyId);
            return "reply-update";
        }

        try {
            Reply reply = modelMapper.fromReplyDto(replyId, replyDto);

            replyService.updateReply(user, reply);
            return "redirect:/posts/{id}";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "error";
        } catch (EntityDuplicateException e) {
            bindingResult.rejectValue("name", "duplicate_post", e.getMessage());
            return "reply-update";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/{id}/reply/{replyId}/delete")
    public String deleteReply(@PathVariable int id,
                              @PathVariable int replyId,
                              Model model,
                              HttpSession session) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        try {
            replyService.deleteReply(replyId, user);
            return "redirect:/posts/{id}";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "error";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{id}/like")
    public String likePost(@PathVariable int id, Model model, HttpSession session) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        try {
            postService.likePost(id, user);
            return "redirect:/posts/{id}";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "error";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
