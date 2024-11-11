package com.forum.forummanagementsystem.models;

import jakarta.persistence.*;

@Entity
@Table(name = "profile_photos")
public class ProfilePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_photo_id")
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "public_id")
    private String publicId;

    public ProfilePhoto() {
    }

    @Lob
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Lob
    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }
}
