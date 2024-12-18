package com.forum.forummanagementsystem.repositories.interfaces;

import com.forum.forummanagementsystem.models.FilterOptionsUser;
import com.forum.forummanagementsystem.models.ProfilePhoto;
import com.forum.forummanagementsystem.models.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();

    User getByUsername(String username);

    User getById(int id);

    void register(User user);

    void updateProfile(User user);

    void blockUser(User user);

    void unblockUser(User user);

    List<User> search(FilterOptionsUser filterOptionsUser);

    void deleteUser(User user);

    void uploadProfilePhotoToUser(ProfilePhoto profilePhoto, User userToUploadPicture);
}
