package com.forum.forummanagementsystem.repositories.interfaces;

import com.forum.forummanagementsystem.models.User;

public interface AdminRepository {

    void block(User user);
    void unblock(User user);
}
