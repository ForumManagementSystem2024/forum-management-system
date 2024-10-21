package com.forum.forummanagementsystem.services.interfaces;

import com.forum.forummanagementsystem.models.User;

public interface AdminService {

    void block(User user);
    void unblock(User user);
}
