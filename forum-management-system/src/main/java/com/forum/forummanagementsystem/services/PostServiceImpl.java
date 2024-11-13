package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityDuplicateException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.models.FilterOptions;
import com.forum.forummanagementsystem.models.Like;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.LikeRepository;
import com.forum.forummanagementsystem.repositories.interfaces.PostRepository;
import com.forum.forummanagementsystem.services.interfaces.PostService;
import com.forum.forummanagementsystem.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    public static final String BLOCKED_USER_ERROR = "You are not allowed to create a post! Please contact customer support!";
    private static final String MODIFY_POST_ERROR_MESSAGE = "Only admin or post creator can modify a post.";

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserService userService, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    @Override
    public List<Post> getAllPosts(FilterOptions filterOptions) {
        return postRepository.getAllPosts(filterOptions);
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
        post.setLikes(new HashSet<>());
        postRepository.create(post);
    }

    @Override
    public void update(Post post, User user) {
        checkModifyPermissions(post.getId(), user);

        boolean duplicateExists = true;
        try {
            Post existingPost = postRepository.getPostByTitle(post.getTitle());
            if (existingPost.getId() == post.getId()) {
                duplicateExists = false;
            }
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new EntityDuplicateException("Post", "title", post.getTitle());
        }

        postRepository.update(post);
    }

    @Override
    public void delete(int postId, User user) {
        checkModifyPermissions(postId, user);

        postRepository.delete(postId);
    }

    @Override
    public Post likePost(int postId, User user) {
        checkIfUserIsBlocked(user);
        Post post = postRepository.getPostById(postId);

        boolean alreadyLiked = false;

        for (Like like : post.getLikes()) {
            if (like.getUser().equals(user)) {
                alreadyLiked = true;
            }

            if (alreadyLiked) {
                return removeLikePost(post, like);
            }
        }

        Like newLike = new Like();
        newLike.setUser(user);
        newLike.setPost(post);

        newLike = likeRepository.save(newLike);
        post.getLikes().add(newLike);
        postRepository.update(post);

        return getPostById(postId);
    }

    @Override
    public Post removeLikePost(Post post, Like like) {
        post.getLikes().remove(like);

        postRepository.update(post);

        likeRepository.removeLike(like);

        return getPostById(post.getId());
    }

    @Override
    public List<Post> getTopTenMostRecentPosts() {
        return postRepository.getTopTenMostRecentPosts();
    }

    public void checkIfUserIsBlocked(User user) {
        if (user.isBlocked()) {
            throw new AuthorizationException(BLOCKED_USER_ERROR);
        }
    }

    public boolean hasUserLikedPost(int postId, User user) {
        return postRepository.hasUserLikedPost(postId, user);
    }

    private void checkModifyPermissions(int postId, User user) {
        Post post = postRepository.getPostById(postId);
        if (!(user.isAdmin() || post.getCreatedBy().equals(user))) {
            throw new AuthorizationException(MODIFY_POST_ERROR_MESSAGE);
        }
    }
}
