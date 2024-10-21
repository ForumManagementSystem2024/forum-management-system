package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.exceptions.EntityDuplicateException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.UserRepository;
import com.forum.forummanagementsystem.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getByUsername(String username) {
       return userRepository.getByUsername(username);
    }

    @Override
    public User getById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void register(User user) {
        boolean existsDuplicate = true;

        try {
            userRepository.getByUsername(user.getUsername());
        } catch (EntityNotFoundException e) {
            existsDuplicate = false;
        }

        if (existsDuplicate) {
            throw new EntityDuplicateException("User", "username", user.getUsername());
        } else {
            userRepository.register(user);
        }
    }

    @Override
    public void updateProfile(User user) {
        throw new UnsupportedOperationException();
    }
}
