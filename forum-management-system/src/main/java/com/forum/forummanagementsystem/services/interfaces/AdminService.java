package com.forum.forummanagementsystem.services.interfaces;

import com.forum.forummanagementsystem.models.Admin;
import com.forum.forummanagementsystem.models.User;

public interface AdminService {

    Admin getAdminById(int id);

    void blockUser(User userToBlock);

    void unblock(User user);
}
