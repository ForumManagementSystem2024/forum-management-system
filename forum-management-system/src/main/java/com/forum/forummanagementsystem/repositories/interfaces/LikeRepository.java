package com.forum.forummanagementsystem.repositories.interfaces;

import com.forum.forummanagementsystem.models.Like;

public interface LikeRepository {

    Like getLikeById(int id);
    Like save(Like like);
    void removeLike(Like like);
}
