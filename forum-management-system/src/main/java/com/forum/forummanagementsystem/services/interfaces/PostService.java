package com.forum.forummanagementsystem.services.interfaces;

import com.forum.forummanagementsystem.models.FilterOptions;
import com.forum.forummanagementsystem.models.Post;

import java.util.List;

public interface PostService {

    List<Post> get(FilterOptions filterOptions);
    Post getPostById(int id);

}
