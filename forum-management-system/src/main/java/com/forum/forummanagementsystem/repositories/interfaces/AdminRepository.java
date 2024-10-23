package com.forum.forummanagementsystem.repositories.interfaces;

import com.forum.forummanagementsystem.models.Admin;
import com.forum.forummanagementsystem.models.User;

public interface AdminRepository {
    Admin getAdminByUserId(int id);
    void makeAdmin(User userToMakeAdmin);
}
