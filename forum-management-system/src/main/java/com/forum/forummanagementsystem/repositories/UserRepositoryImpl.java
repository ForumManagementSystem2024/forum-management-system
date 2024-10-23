package com.forum.forummanagementsystem.repositories;

import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where username = :username", User.class);
            query.setParameter("username", username);

            List<User> users = query.list();

            if (users.isEmpty()) {
                throw new EntityNotFoundException("User", "username", username);
            }

            return users.get(0);
        }
    }

    @Override
    public User getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);

            if (user == null) {
                throw new EntityNotFoundException("User", id);
            }

            return user;
        }
    }

    @Override
    public void register(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateProfile(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void blockUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            user.setBlocked(true);
            session.merge(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void unblockUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            user.setBlocked(false);
            session.merge(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(user);
            session.getTransaction().commit();
        }
    }
}
