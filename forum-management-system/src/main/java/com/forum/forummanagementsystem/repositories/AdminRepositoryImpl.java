package com.forum.forummanagementsystem.repositories;

import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.AdminRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public AdminRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void block(User user) {

    }

    @Override
    public void unblock(User user) {

    }
}
