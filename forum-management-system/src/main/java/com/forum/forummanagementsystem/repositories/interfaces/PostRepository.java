package com.forum.forummanagementsystem.repositories.interfaces;

import com.forum.forummanagementsystem.models.FilterOptions;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.User;

import java.util.List;

public interface PostRepository {
    List<Post> get(FilterOptions filterOptions);
    Post getPostById(int id);
    Post getPostByTitle(String title);
    void create(Post post);
    void update(Post post);
    void delete(Post post, User user);
}
