package com.forum.forummanagementsystem.controllers.mvc;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.helpers.AuthenticationHelper;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.models.dto.ReplyDto;
import com.forum.forummanagementsystem.services.interfaces.ReplyService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    //TODO Once AuthenticationMvcController is created - finish the below method
    @PostMapping("/new")
    public String createReply(@Valid @ModelAttribute("reply") ReplyDto replyDto,
                              BindingResult bindingResult,
                              Model model,
                              HttpSession session) {
//        User user;
//        try {
//            user = authenticationHelper.tryGetCurrentUser(session);
//        } catch (AuthorizationException e) {
//            return "redirect:/auth/login";
//        }
        //TODO Display the proper view
        return "redirect:/posts/";
    }
}
