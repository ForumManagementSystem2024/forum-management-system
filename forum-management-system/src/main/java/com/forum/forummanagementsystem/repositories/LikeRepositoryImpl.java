package com.forum.forummanagementsystem.repositories;

import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.models.Like;
import com.forum.forummanagementsystem.repositories.interfaces.LikeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LikeRepositoryImpl implements LikeRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public LikeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Like save(Like like) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(like);
            session.getTransaction().commit();
        }

        return getLikeById(like.getLikeId());
    }
    @Override
    public Like getLikeById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Like like = session.get(Like.class, id);

            if (like == null) {
                throw new EntityNotFoundException("Like", id);
            }

            return like;
        }
    }

    @Override
    public void removeLike(Like like) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(like);
            session.getTransaction().commit();
        }
    }
}
