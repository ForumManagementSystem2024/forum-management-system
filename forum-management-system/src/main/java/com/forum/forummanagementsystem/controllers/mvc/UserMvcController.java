package com.forum.forummanagementsystem.controllers.mvc;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.helpers.AuthenticationHelper;
import com.forum.forummanagementsystem.helpers.ModelMapper;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.models.dto.UserDtoUpdate;
import com.forum.forummanagementsystem.services.CloudinaryImage;
import com.forum.forummanagementsystem.services.interfaces.CloudinaryService;
import com.forum.forummanagementsystem.services.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/users")
public class UserMvcController {

    private final UserService userService;
    private final CloudinaryService cloudinaryService;
    private final AuthenticationHelper authenticationHelper;
    private final ModelMapper modelMapper;

    public UserMvcController(UserService userService,
                             ModelMapper modelMapper,
                             CloudinaryService cloudinaryService,
                             AuthenticationHelper authenticationHelper) {

        this.userService = userService;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.authenticationHelper = authenticationHelper;
    }

    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session) {
        return session.getAttribute("currentUser") != null;
    }

    @GetMapping("/profile")
    public String getUserProfileInfo(Model model, HttpSession session) {
        User user;

        try {
            user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        model.addAttribute("user", user);

        return "profile-view-2";
    }

    @GetMapping("/update")
    public String showUpdateProfileForm(Model model, HttpSession session) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        model.addAttribute("user", user);
        return "profile-update";
    }

    @PostMapping("/update")
    public String updateUserProfile(@Valid @ModelAttribute("user") UserDtoUpdate userDtoUpdate,
                                    @RequestParam("profilePhoto") MultipartFile profilePhoto,
                                    Model model,
                                    BindingResult bindingResult,
                                    HttpSession session) {
        User userAuthenticated;

        try {
            userAuthenticated = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        if (bindingResult.hasErrors()) {
            return "profile-update";
        }

        try {
            User updatedUser = modelMapper.fromUpdateUserDto(userAuthenticated.getId(), userDtoUpdate);

            if (!profilePhoto.isEmpty()) {
                CloudinaryImage cloudinaryImage = cloudinaryService.upload(profilePhoto);

                userService.uploadProfilePhotoToUser(userAuthenticated, updatedUser, cloudinaryImage);
            } else {
                userService.updateProfile(userAuthenticated, updatedUser);
            }


        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());

             return "error";
        } catch (IOException e) {
            model.addAttribute("statusCode", HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase());
            model.addAttribute("error", e.getMessage());

             return "error";
        }

        model.addAttribute("user", userService.getUserById(userAuthenticated.getId()));
        return "profile-view";
    }

}
