package com.forum.forummanagementsystem.repositories;

import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.Reply;
import com.forum.forummanagementsystem.repositories.interfaces.ReplyRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyRepositoryImpl implements ReplyRepository {

    private final SessionFactory sessionFactory;

    public ReplyRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Reply> get(int postId) {
        try(Session session = sessionFactory.openSession()) {
            Query<Reply> query = session.createQuery("from Reply where postId.id = :postId", Reply.class);
            query.setParameter("postId", postId);
            return query.list();
        }
    }

    @Override
    public Reply getReplyById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Reply reply = session.get(Reply.class, id);

            if (reply == null) {
                throw new EntityNotFoundException("Reply", id);
            }

            return reply;
        }
    }

    @Override
    public void createReply(Reply reply) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(reply);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateReply(Reply reply) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(reply);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteReply(int id) {
        Reply replyToDelete = getReplyById(id);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(replyToDelete);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Post> getTopTenMostCommentedPosts() {
        try (Session session = sessionFactory.openSession()) {
            Query<Post> query = session.createQuery(
                    "select r.postId from Reply r " +
                            "group by r.postId " +
                            "order by count(r.id) DESC", Post.class);

            query.setMaxResults(10);

            return query.getResultList();
        }
    }
}
