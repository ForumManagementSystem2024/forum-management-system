package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
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

    private static final String MODIFY_PROFILE_ERROR_MESSAGE = "Only profile owner can make changes to the profile.";

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
    public User getUserById(int id) {
        return userRepository.getById(id);
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
            user.setBlocked(false);
            userRepository.register(user);
        }
    }

    @Override
    public void updateProfile(User userAuthenticated, User userMapped) {
        checkModifyPermissions(userAuthenticated, userMapped);
        // TODO could implement "Users should not be able to change their username once registered. "

        userRepository.updateProfile(userMapped);

    }

    private void checkModifyPermissions(User userAuthenticated, User userMapped) {
        if (!(userAuthenticated.equals(userMapped))) {
            throw new AuthorizationException(MODIFY_PROFILE_ERROR_MESSAGE);
        }
    }
}
