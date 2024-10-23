package com.forum.forummanagementsystem.services.interfaces;

import com.forum.forummanagementsystem.models.User;

import java.util.List;

public interface UserService {

    User getByUsername(String username);
    User getUserById(int id);
    List<User> getAll();
    void register(User user);
    void updateProfile(User userAuthenticated, User userMapped);
    void deleteUser(User user);
    // To be discussed:
    // List<User> getUsersBy(FilterOptions filterOptions);
}