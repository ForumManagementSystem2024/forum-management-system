package com.forum.forummanagementsystem.controllers.mvc;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.helpers.AuthenticationHelper;
import com.forum.forummanagementsystem.helpers.ModelMapper;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.models.dto.ReplyDto;
import com.forum.forummanagementsystem.services.interfaces.ReplyService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/replies")
public class ReplyMvcController {

    private final AuthenticationHelper authenticationHelper;
    private final ReplyService replyService;
    private final ModelMapper modelMapper;

    public ReplyMvcController(AuthenticationHelper authenticationHelper, ReplyService replyService, ModelMapper modelMapper) {
        this.authenticationHelper = authenticationHelper;
        this.replyService = replyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/new")
    public String showCreateNewReplyView(Model model, HttpSession session) {
        model.addAttribute("reply", new ReplyDto());
        return "reply-create";
    }

    @PostMapping("/new")
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

        return "reply-create";
    }
}
