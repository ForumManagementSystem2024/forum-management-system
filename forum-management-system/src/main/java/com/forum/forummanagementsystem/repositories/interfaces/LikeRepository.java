package com.forum.forummanagementsystem.repositories.interfaces;

import com.forum.forummanagementsystem.models.Like;

public interface LikeRepository {
    Like existsByUserIdAndPostId(int userId, int postId);
    void save(Like like);
    void removeLike(Like like);
}
