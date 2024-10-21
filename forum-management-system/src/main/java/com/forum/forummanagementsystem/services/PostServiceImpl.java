package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.models.FilterOptions;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.PostRepository;
import com.forum.forummanagementsystem.services.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> get(FilterOptions filterOptions) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Post getPostById(int id) {
        return postRepository.getPostById(id);
    }

    @Override
    public void create(Post post, User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Post post, User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Post post, User user) {
        throw new UnsupportedOperationException();
    }
}
