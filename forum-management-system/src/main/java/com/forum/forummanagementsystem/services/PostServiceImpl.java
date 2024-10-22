package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityDuplicateException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
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

    public static final String BLOCKED_USER_ERROR = "You are not allowed to create a post! Please contact customer support!";
    public static final int DEFAULT_LIKES = 0;
    private final PostRepository postRepository;

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
        checkIfUserIsBlocked(user);

        boolean duplicateExists = true;
        try {
            postRepository.getPostByTitle(post.getTitle());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new EntityDuplicateException("Post", "title", post.getTitle());
        }

        post.setCreatedBy(user);
        post.setLikes(DEFAULT_LIKES);
        postRepository.create(post);
    }

    @Override
    public void update(Post post, User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Post post, User user) {
        throw new UnsupportedOperationException();
    }

    private static void checkIfUserIsBlocked(User user) {
        if(user.isBlocked()){
            throw new AuthorizationException(BLOCKED_USER_ERROR);
        }
    }
    
}
