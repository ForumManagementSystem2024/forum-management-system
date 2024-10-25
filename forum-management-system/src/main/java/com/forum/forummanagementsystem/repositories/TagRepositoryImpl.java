package com.forum.forummanagementsystem.repositories;

import com.forum.forummanagementsystem.models.Tag;
import com.forum.forummanagementsystem.repositories.interfaces.TagRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;

import java.util.List;

@Repository
public class TagRepositoryImpl implements TagRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public TagRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Tag findTagByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Tag> query = session.createQuery("from Tag where tagName = :name", Tag.class);
            query.setParameter("name", name);

            List<Tag> tags = query.list();

            if (tags.isEmpty()) {
                return null;
            }

            return tags.get(0);
        }
    }

    @Override
    public Tag createTag(String tagName) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Tag newTag = new Tag();
            newTag.setTagName(tagName);
            session.persist(newTag);
            session.getTransaction().commit();
        }

        return findTagByName(tagName);
    }
}
