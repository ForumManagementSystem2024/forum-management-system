package com.forum.forummanagementsystem.services.interfaces;

import com.forum.forummanagementsystem.models.User;

import java.util.List;

public interface UserService {

    User getByUsername(String username);
    User getById(int id);
    List<User> getAll();
    void register(User user);
    void updateProfile(User userAuthenticated, User userMapped);
    // To be discussed:
    // void blockUser(User user);
    // void unblockUser(User user);
    // List<User> getUsersBy(FilterOptions filterOptions);
}