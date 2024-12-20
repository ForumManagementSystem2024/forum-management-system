package com.forum.forummanagementsystem.controllers.mvc;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.helpers.AuthenticationHelper;
import com.forum.forummanagementsystem.helpers.ModelMapper;
import com.forum.forummanagementsystem.models.FilterOptionsUser;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.User;

import com.forum.forummanagementsystem.models.dto.UserDtoOut;
import com.forum.forummanagementsystem.services.interfaces.PostService;
import com.forum.forummanagementsystem.services.interfaces.ReplyService;
import com.forum.forummanagementsystem.models.dto.SearchDtoUser;
import com.forum.forummanagementsystem.services.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeMvcController {

    private final AuthenticationHelper authenticationHelper;
    private final UserService userService;
    private final ReplyService replyService;
    private final PostService postService;

    public HomeMvcController(AuthenticationHelper authenticationHelper,
                             UserService userService, ReplyService replyService, PostService postService) {
        this.authenticationHelper = authenticationHelper;
        this.userService = userService;
        this.replyService = replyService;
        this.postService = postService;
    }

    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session) {
        return session.getAttribute("currentUser") != null;
    }

    @GetMapping
    public String showHomePage(Model model) {
        List<Post> mostCommentedPosts = replyService.getTopTenMostCommentedPosts();
        List<Post> mostRecentPosts = postService.getTopTenMostRecentPosts();

        model.addAttribute("mostCommentedPosts", mostCommentedPosts);
        model.addAttribute("mostRecentPosts", mostRecentPosts);

        return "index";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }

    @GetMapping("/admin")
    public String showAdminPortal(HttpSession session, Model model) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(session);

            if (user == null) {
                model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
                return "error";
            }

            if (user.isAdmin()) {
                List<User> users = userService.getAllUsers();
                model.addAttribute("users", users);
                model.addAttribute("search", new SearchDtoUser());
                return "admin-portal-view";
            } else {
                model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
                return "error";
            }
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
    }

    @GetMapping("admin/search")
    public String search(@ModelAttribute("search") SearchDtoUser searchDtoUser,
                         HttpSession session,
                         Model model) {
        FilterOptionsUser filterOptionsUser =
                userService.generateFilterOptionsUser(searchDtoUser.getType(), searchDtoUser.getValue());

        if(populateIsAuthenticated(session)) {
            String currentUsername = (String) session.getAttribute("currentUser");
            model.addAttribute("currentUser", userService.getByUsername(currentUsername));
        }
        model.addAttribute("search", searchDtoUser);
        model.addAttribute("users", userService.search(filterOptionsUser));
        return "search-admin-view";
    }

    @PostMapping("admin/block/{userId}")
    public String blockUser(@PathVariable int userId,
                            Model model, HttpSession session) {
        User user;

        try {
             user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        try {
            User userToBlock = userService.getUserById(userId);
            userService.blockUser(userToBlock, user);

            model.addAttribute("users", userService.getAllUsers());

            return "redirect:/admin";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "error";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return "error";
        }
    }

    @PostMapping("admin/unblock/{userId}")
    public String unblockUser(@PathVariable int userId,
                            Model model, HttpSession session) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        try {
            User userToBlock = userService.getUserById(userId);
            userService.unblockUser(userToBlock, user);

            model.addAttribute("users", userService.getAllUsers());

            return "redirect:/admin";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "error";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return "error";
        }
    }

    @PostMapping("admin/makeAdmin/{id}")
    public String makeAdmin(@PathVariable int id, Model model, HttpSession session) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
        try {
            User userToMakeAdmin = userService.getUserById(id);

            userService.makeAdmin(userToMakeAdmin, user);

            model.addAttribute("users", userService.getAllUsers());

            return "redirect:/admin";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "error";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return "error";
        }
    }

    @PostMapping("admin/delete/{id}")
    public String deleteUser(@PathVariable int id, Model model, HttpSession session) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        try {
            User userToDelete = userService.getUserById(id);

            userService.deleteUser(userToDelete,user);

            model.addAttribute("users", userService.getAllUsers());

            return "redirect:/admin";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "error";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return "error";
        }
    }
}
