package com.forum.forummanagementsystem.repositories.interfaces;

import com.forum.forummanagementsystem.models.ProfilePhoto;
import com.forum.forummanagementsystem.services.CloudinaryImage;

public interface ProfilePhotoRepository {

    ProfilePhoto findByUrl(String url);

    void uploadProfilePhoto(CloudinaryImage cloudinaryImage);

}
