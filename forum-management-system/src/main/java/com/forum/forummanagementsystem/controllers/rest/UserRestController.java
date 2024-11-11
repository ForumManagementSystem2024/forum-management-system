package com.forum.forummanagementsystem.controllers.rest;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityDuplicateException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.helpers.AuthenticationHelper;
import com.forum.forummanagementsystem.helpers.ModelMapper;
import com.forum.forummanagementsystem.models.FilterOptionsUser;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.models.dto.UserDtoUpdate;
import com.forum.forummanagementsystem.models.dto.UserDto;
import com.forum.forummanagementsystem.models.dto.UserDtoOut;
import com.forum.forummanagementsystem.services.CloudinaryImage;
import com.forum.forummanagementsystem.services.interfaces.CloudinaryService;
import com.forum.forummanagementsystem.services.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    public static final String UPLOAD_PROFILE_PHOTO_ERROR_MESSAGE = "Could not create profile photo!";

    private final UserService userService;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public UserRestController(UserService userService,

                              CloudinaryService cloudinaryService,
                              ModelMapper modelMapper,
                              AuthenticationHelper authenticationHelper) {
        this.userService = userService;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
        this.authenticationHelper = authenticationHelper;
    }

    @GetMapping
    public List<UserDtoOut> search(@RequestHeader HttpHeaders headers,
                                   @RequestParam(required = false) String username,
                                   @RequestParam(required = false) String email,
                                   @RequestParam(required = false) String firstName) {
        try {
            authenticationHelper.tryGetUser(headers);
            FilterOptionsUser filterOptionsUser = new FilterOptionsUser(username, email, firstName);

            List<User> userList = userService.search(filterOptionsUser);
            return modelMapper.fromListUserToListUserDtoOut(userList);
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public UserDtoOut getUserById(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            authenticationHelper.tryGetUser(headers);
            User user = userService.getUserById(id);

            return modelMapper.fromUserToUserDtoOut(user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping()
    public User registerUser(@Valid @RequestBody UserDto userDto) {
        try {
            User user = modelMapper.fromUserDto(userDto);
            userService.register(user);

            return user;
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public User updateProfile(@RequestHeader HttpHeaders headers,
                              @PathVariable int id,
                              @Valid @RequestBody UserDtoUpdate userDtoUpdate) {
        try {
            User userAuthenticated = authenticationHelper.tryGetUser(headers);
            User userMapped = modelMapper.fromUpdateUserDto(id, userDtoUpdate);
            userService.updateProfile(userAuthenticated, userMapped);
            return userMapped;
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/{id}/profile-picture")
    public void uploadProfilePhoto(@RequestHeader HttpHeaders headers,
                                   @PathVariable int id,
                                   @RequestPart("profilePhoto") MultipartFile profilePhoto) {
        try {
            User userAuthenticated = authenticationHelper.tryGetUser(headers);
            User userToUploadPhoto = userService.getUserById(id);
            CloudinaryImage cloudinaryImage = cloudinaryService.upload(profilePhoto);

            userService.uploadProfilePhotoToUser(userAuthenticated, userToUploadPhoto, cloudinaryImage);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, UPLOAD_PROFILE_PHOTO_ERROR_MESSAGE);
        }
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            User userAuthenticated = authenticationHelper.tryGetUser(headers);

            User userToDelete = userService.getUserById(id);

            userService.deleteUser(userToDelete, userAuthenticated);

        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
