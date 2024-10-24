package com.forum.forummanagementsystem.services.interfaces;

import com.forum.forummanagementsystem.models.FilterOptions;
import com.forum.forummanagementsystem.models.Like;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.User;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts(FilterOptions filterOptions);
    Post getPostById(int id);
    void create(Post post, User user);
    void update(Post post, User user);
    void delete(int postId, User user);
    Post likePost(int postId, User user);
    Post removeLikePost(Post post, Like like);

    List<Post> getTopTenMostRecentPosts();
}
