package com.forum.forummanagementsystem.repositories;

import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.models.Like;
import com.forum.forummanagementsystem.repositories.interfaces.LikeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LikeRepositoryImpl implements LikeRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public LikeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean existsByUserIdAndPostId(int userId, int postId) {
        try (Session session = sessionFactory.openSession()){
            Query<Like> query = session.createQuery("from Like where userId.id = :userId and postId.id = :postId", Like.class);
            query.setParameter("userId", userId);
            query.setParameter("postId", postId);

            List<Like> likes = query.list();

            return likes.size()>0;
        }
    }

    @Override
    public void save(Like like) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(like);
            session.getTransaction().commit();
        }
    }


}
