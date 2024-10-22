package com.forum.forummanagementsystem.repositories;

import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.models.FilterOptions;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.PostRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public PostRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Post> get(FilterOptions filterOptions) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Post getPostById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Post post = session.get(Post.class, id);

            if (post == null) {
                throw new EntityNotFoundException("Post", id);
            }

            return post;
        }
    }

    @Override
    public Post getPostByTitle(String title) {
        try (Session session = sessionFactory.openSession()) {
            Query<Post> query = session.createQuery("from Post where title = :title", Post.class);
            query.setParameter("title", title);

            List<Post> result = query.list();

            if (result.isEmpty()) {
                throw new EntityNotFoundException("Post", "title", title);
            }

            return result.get(0);
        }
    }

    @Override
    public void create(Post post) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(post);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Post post, User user) {

    }

    @Override
    public void delete(int id, User user) {

    }
}
