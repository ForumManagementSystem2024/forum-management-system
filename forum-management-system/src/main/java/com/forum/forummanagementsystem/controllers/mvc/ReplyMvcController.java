package com.forum.forummanagementsystem.controllers.mvc;

import com.forum.forummanagementsystem.helpers.AuthenticationHelper;
import com.forum.forummanagementsystem.models.dto.ReplyDto;
import com.forum.forummanagementsystem.services.interfaces.ReplyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/replies")
public class ReplyMvcController {

    private final AuthenticationHelper authenticationHelper;
    private final ReplyService replyService;

    public ReplyMvcController(AuthenticationHelper authenticationHelper, ReplyService replyService) {
        this.authenticationHelper = authenticationHelper;
        this.replyService = replyService;
    }

    @GetMapping("/new")
    public String showCreateNewReplyView(Model model, HttpSession session) {
//        try {
//            authenticationHelper.tryGetCurrentUser(session);
//        } catch (AuthorizationException e) {
//            return "redirect:/auth/login";
//        }

        model.addAttribute("reply", new ReplyDto());
        return "reply-create";
    }
}
