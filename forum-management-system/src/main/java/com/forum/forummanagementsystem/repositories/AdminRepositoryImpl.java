package com.forum.forummanagementsystem.repositories;

import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.models.Admin;

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
    public Admin getAdminById(int id) {
        try(Session session = sessionFactory.openSession()) {
            Query<Admin> query = session.createQuery("from Admin a where a.userId.id = :id", Admin.class);
            query.setParameter("id", id);

            List<Admin> result = query.list();

            if (result.isEmpty()) {
                throw new EntityNotFoundException("Admin", id);
            }

            return result.get(0);
        }
    }
}