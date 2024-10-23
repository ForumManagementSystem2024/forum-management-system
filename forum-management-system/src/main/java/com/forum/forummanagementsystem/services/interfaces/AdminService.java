package com.forum.forummanagementsystem.services.interfaces;

import com.forum.forummanagementsystem.models.Admin;
import com.forum.forummanagementsystem.models.User;

public interface AdminService {

    Admin getAdminByUserId(int id);

    void blockUser(User userToBlock);

    void unblockUser(User userToUnblock);

    void makeAdmin(User userToMakeAdmin);
}
