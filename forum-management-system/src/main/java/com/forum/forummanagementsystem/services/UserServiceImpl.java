package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityDuplicateException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.exceptions.InvalidSearchInputException;
import com.forum.forummanagementsystem.models.Admin;
import com.forum.forummanagementsystem.models.FilterOptionsUser;
import com.forum.forummanagementsystem.models.ProfilePhoto;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.AdminRepository;
import com.forum.forummanagementsystem.repositories.interfaces.ProfilePhotoRepository;
import com.forum.forummanagementsystem.repositories.interfaces.UserRepository;
import com.forum.forummanagementsystem.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final String MODIFY_PROFILE_ERROR_MESSAGE = "Only profile owner can make changes to the profile.";
    public static final String NON_ADMIN_ERROR = "Only admins can %s.";

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final ProfilePhotoRepository profilePhotoRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           AdminRepository adminRepository,
                           ProfilePhotoRepository profilePhotoRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.profilePhotoRepository = profilePhotoRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public void register(User user) {
        boolean existsDuplicate = true;

        try {
            userRepository.getByUsername(user.getUsername());
        } catch (EntityNotFoundException e) {
            existsDuplicate = false;
        }

        if (existsDuplicate) {
            throw new EntityDuplicateException("User", "username", user.getUsername());
        } else {
            user.setBlocked(false);
            userRepository.register(user);
        }
    }

    @Override
    public void updateProfile(User userAuthenticated, User userMapped) {
        checkModifyPermissions(userAuthenticated, userMapped);
        userRepository.updateProfile(userMapped);

    }

    @Override
    public List<User> search(FilterOptionsUser filterOptionsUser) {
        return userRepository.search(filterOptionsUser);
    }

    @Override
    public void deleteUser(User user, User userAuthenticated) {
        if (!userAuthenticated.isAdmin()) {
            throw new AuthorizationException(String.format(NON_ADMIN_ERROR, "delete users"));
        }
        userRepository.deleteUser(user);
    }

    @Override
    public void blockUser(User userToBlock, User userAuthenticated) {
        if (!userAuthenticated.isAdmin()) {
            throw new AuthorizationException(String.format(NON_ADMIN_ERROR, "block users"));
        }
        userRepository.blockUser(userToBlock);
    }

    @Override
    public void unblockUser(User userToUnblock, User userAuthenticated) {
        if (!userAuthenticated.isAdmin()) {
            throw new AuthorizationException(String.format(NON_ADMIN_ERROR, "unblock users"));
        }
        userRepository.unblockUser(userToUnblock);
    }

    @Override
    public void makeAdmin(User userToMakeAdmin, User userAuthenticated) {
        if (!userAuthenticated.isAdmin()) {
            throw new AuthorizationException(String.format(NON_ADMIN_ERROR, "make user an admin"));
        }
        userToMakeAdmin.setAdmin(true);
        userRepository.updateProfile(userToMakeAdmin);
        adminRepository.makeAdmin(userToMakeAdmin);
    }

    @Override
    public void updatePhoneOfAdmin(Admin admin) {
        adminRepository.updatePhoneOfAdmin(admin);
    }

    @Override
    public void uploadProfilePhotoToUser(User userAuthenticated, User userToUploadPhoto, CloudinaryImage cloudinaryImage) {
        checkModifyPermissions(userAuthenticated, userToUploadPhoto);

        profilePhotoRepository.uploadProfilePhoto(cloudinaryImage);

        ProfilePhoto profilePhoto = profilePhotoRepository.findByUrl(cloudinaryImage.getUrl());

        userRepository.uploadProfilePhotoToUser(profilePhoto, userToUploadPhoto);
    }

    @Override
    public FilterOptionsUser generateFilterOptionsUser(String type, String value) {
        switch (type) {
            case "username":
                return new FilterOptionsUser(value, null, null);

            case "email":
                return new FilterOptionsUser(null, value, null);

            case "firstName":
                return new FilterOptionsUser(null, null, value);
            default:
                throw new InvalidSearchInputException("You can only search user by username, email or first name.");
        }
    }

    private void checkModifyPermissions(User userAuthenticated, User userMapped) {
        if (!(userAuthenticated.equals(userMapped))) {
            throw new AuthorizationException(MODIFY_PROFILE_ERROR_MESSAGE);
        }
    }
}
