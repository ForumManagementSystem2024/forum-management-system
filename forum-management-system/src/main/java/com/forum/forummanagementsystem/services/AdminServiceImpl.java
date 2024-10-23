package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.exceptions.EntityDuplicateException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.models.Admin;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.AdminRepository;
import com.forum.forummanagementsystem.repositories.interfaces.UserRepository;
import com.forum.forummanagementsystem.services.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;


    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Admin getAdminByUserId(int id) {
        return adminRepository.getAdminByUserId(id);
    }

    @Override
    public void blockUser(User userToBlock) {
        userRepository.blockUser(userToBlock);
    }


    @Override
    public void unblockUser(User userToUnblock) {
        userRepository.unblockUser(userToUnblock);
    }

    @Override
    public void makeAdmin(User userToMakeAdmin) {
        try {
           Admin admin = adminRepository.getAdminByUserId(userToMakeAdmin.getId());

           if (admin != null) {
               throw new EntityDuplicateException("Admin", admin.getId());
           }

        } catch (EntityNotFoundException e) {
            adminRepository.makeAdmin(userToMakeAdmin);
        }
    }
}
