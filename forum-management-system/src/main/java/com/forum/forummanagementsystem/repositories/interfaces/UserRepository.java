package com.forum.forummanagementsystem.repositories.interfaces;

import com.forum.forummanagementsystem.models.User;

import java.util.List;

public interface UserRepository {
    User getByUsername(String username);

    User getById(int id);

    List<User> getAll();

    void register(User user);

    void updateProfile(User user);

    void blockUser(User user);

    void unblockUser(User user);
    // List<User> getUsersBy(FilterOptions filterOptions);
}
