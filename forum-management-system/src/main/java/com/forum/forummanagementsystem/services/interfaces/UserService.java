package com.forum.forummanagementsystem.services.interfaces;

import com.forum.forummanagementsystem.models.FilterOptionsUser;
import com.forum.forummanagementsystem.models.User;

import java.util.List;

public interface UserService {

    User getByUsername(String username);

    User getUserById(int id);

    void register(User user);

    void updateProfile(User userAuthenticated, User userMapped);

    List<User> search(FilterOptionsUser filterOptionsUser);

    void deleteUser(User user, User userAuthenticated);

    void blockUser(User userToBlock, User userAuthenticated);

    void unblockUser(User userToUnblock, User userAuthenticated);

    void makeAdmin(User userToMakeAdmin, User userAuthenticated);
}