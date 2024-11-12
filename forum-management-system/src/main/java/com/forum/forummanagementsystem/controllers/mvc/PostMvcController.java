package com.forum.forummanagementsystem.controllers.mvc;

import com.forum.forummanagementsystem.models.FilterOptions;
import com.forum.forummanagementsystem.models.dto.FilterDto;
import com.forum.forummanagementsystem.services.interfaces.PostService;
import com.forum.forummanagementsystem.services.interfaces.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostMvcController {

    private final PostService postService;
    private final ReplyService replyService;

    @Autowired
    public PostMvcController(PostService postService, ReplyService replyService) {
        this.postService = postService;
        this.replyService = replyService;
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

    @GetMapping("/most-commented")
    public String getTopTenMostCommentedPosts(Model model) {
        model.addAttribute("posts", replyService.getTopTenMostCommentedPosts());
        return "index";
    }
}
