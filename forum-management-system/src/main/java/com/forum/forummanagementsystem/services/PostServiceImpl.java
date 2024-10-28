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
import com.forum.forummanagementsystem.services.interfaces.AdminService;
import com.forum.forummanagementsystem.services.interfaces.PostService;
import com.forum.forummanagementsystem.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    public static final String BLOCKED_USER_ERROR = "You are not allowed to create a post! Please contact customer support!";
    public static final int DEFAULT_LIKES = 0;
    public static final String USER_NOT_CREATOR_ERROR = "You are not allowed to modify this post!";
    public static final String USER_NOT_AUTHORIZED_DELETE_POST_ERROR = "Only admins and creator of post can delete it!";

    private final PostRepository postRepository;
    private final AdminService adminService;
    private final LikeRepository likeRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserService userService, AdminService adminService, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.adminService = adminService;
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
        post.setLikes(DEFAULT_LIKES);
        postRepository.create(post);
    }

    @Override
    public void update(Post post, User user) {
        try {
            checkIfUserIsCreator(post.getId(), user);
        } catch (AuthorizationException e) {
            checkIfUserIsAdmin(user);
        }

        boolean duplicateExists = true;
        try{
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
        //TODO: rework without try-catch
        try {
            checkIfUserIsCreator(postId, user);
        } catch (AuthorizationException e) {
            checkIfUserIsAdmin(user);
        }

        postRepository.delete(postId);
    }

    @Override
    public Post likePost(int postId, User user) {
        Post post = postRepository.getPostById(postId);
        checkIfUserIsBlocked(user);

        Like like = likeRepository.existsByUserIdAndPostId(user.getId(), postId);

        if(like != null) {
           return removeLikePost(post, like);
        }

        post.setLikes(post.getLikes() + 1);

        Like newLike = new Like();
        newLike.setUserId(user);
        newLike.setPostId(post);

        likeRepository.save(newLike);
        postRepository.update(post);

        return getPostById(postId);
    }

    @Override
    public Post removeLikePost(Post post,Like like){
        post.setLikes(post.getLikes() - 1);
        postRepository.update(post);

        likeRepository.removeLike(like);

        return getPostById(post.getId());
    }

    @Override
    public List<Post> getTopTenMostRecentPosts() {
        return postRepository.getTopTenMostRecentPosts();
    }

    public void checkIfUserIsCreator(int postId, User user) {
        Post post = postRepository.getPostById(postId);

        if(!(post.getCreatedBy().equals(user))) {
            throw new AuthorizationException(USER_NOT_CREATOR_ERROR);
        }
    }

    public void checkIfUserIsAdmin(User user) {
        //TODO: rework without try-catch
        boolean isAdmin = true;

        try {
            adminService.getAdminByUserId(user.getId());
        } catch (EntityNotFoundException e) {
            isAdmin = false;
        }

        if (!isAdmin) {
            throw new AuthorizationException(USER_NOT_AUTHORIZED_DELETE_POST_ERROR);
        }

    }

    public void checkIfUserIsBlocked(User user) {
        if(user.isBlocked()){
            throw new AuthorizationException(BLOCKED_USER_ERROR);
        }
    }
}
