package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.AdminRepository;
import com.forum.forummanagementsystem.services.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void block(User user) {

    }

    @Override
    public void unblock(User user) {

    }
}
