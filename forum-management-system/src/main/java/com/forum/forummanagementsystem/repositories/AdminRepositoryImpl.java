package com.forum.forummanagementsystem.repositories;

import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.models.Admin;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.AdminRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public AdminRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void makeAdmin(User userToMakeAdmin) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Admin admin = new Admin();
            admin.setUser(userToMakeAdmin);
            session.persist(admin);
            session.getTransaction().commit();
        }
    }

    @Override
    public Admin getAdminById(int adminId) {
        try (Session session = sessionFactory.openSession()) {
            Admin admin = session.get(Admin.class, adminId);

            if (admin == null) {
                throw new EntityNotFoundException("Admin", adminId);
            }

            return admin;
        }
    }

    @Override
    public void updatePhoneOfAdmin(Admin admin) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(admin);
            session.getTransaction().commit();
        }
    }
}
