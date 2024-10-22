package com.forum.forummanagementsystem.repositories.interfaces;

import com.forum.forummanagementsystem.models.Admin;

public interface AdminRepository {
    Admin getAdminById(int id);
}
