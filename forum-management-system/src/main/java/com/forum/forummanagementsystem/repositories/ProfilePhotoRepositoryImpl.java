package com.forum.forummanagementsystem.repositories;

import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.models.ProfilePhoto;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.ProfilePhotoRepository;
import com.forum.forummanagementsystem.services.CloudinaryImage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfilePhotoRepositoryImpl implements ProfilePhotoRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public ProfilePhotoRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ProfilePhoto findByUrl(String url) {
        try (Session session = sessionFactory.openSession()) {
            Query<ProfilePhoto> query = session.createQuery("from ProfilePhoto where url = :url", ProfilePhoto.class);
            query.setParameter("url", url);

            List<ProfilePhoto> profilePhotos = query.list();

            if (profilePhotos.isEmpty()) {
                throw new EntityNotFoundException("Profile photo", "url", url);
            }

            return profilePhotos.get(0);
        }
    }

    @Override
    public void uploadProfilePhoto(CloudinaryImage cloudinaryImage) {
        ProfilePhoto profilePhoto = new ProfilePhoto();
        profilePhoto.setUrl(cloudinaryImage.getUrl());
        profilePhoto.setPublicId(cloudinaryImage.getPublicId());

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(profilePhoto);
            session.getTransaction().commit();
        }
    }
}
