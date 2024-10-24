package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.models.FilterOptions;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.Reply;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.ReplyRepository;
import com.forum.forummanagementsystem.services.interfaces.AdminService;
import com.forum.forummanagementsystem.services.interfaces.ReplyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {
    public static final String BLOCKED_USER_ERROR =
            "You are not allowed to create a reply! Please contact customer support!";
    public static final String USER_NOT_CREATOR_ERROR = "You are not allowed to modify this reply!";
    public static final String USER_NOT_AUTHORIZED_DELETE_REPLY_ERROR = "Only admins and creator of reply can delete it!";

    private final ReplyRepository replyRepository;
    private final AdminService adminService;

    public ReplyServiceImpl(ReplyRepository replyRepository, AdminService adminService) {
        this.replyRepository = replyRepository;
        this.adminService = adminService;
    }

    // TODO
    @Override
    public List<Reply> getReplies(FilterOptions filterOptions) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Reply getReplyById(int id) {
        return replyRepository.getReplyById(id);
    }

    @Override
    public void createReply(Post post, User user, Reply reply) {
        checkIfUserIsBlocked(user);

        reply.setCreatedBy(user);
        reply.setPostId(post);
        replyRepository.createReply(reply);
    }

    @Override
    public void updateReply(User user, Reply reply) {
        checkIfUserIsCreator(reply.getId(), user);

        replyRepository.updateReply(reply);
    }

    @Override
    public void deleteReply(int replyId, User user) {
        try {
            checkIfUserIsCreator(replyId, user);
        } catch (AuthorizationException e) {
            checkIfUserIsAdmin(user);
        }

        replyRepository.deleteReply(replyId);
    }

    @Override
    public List<Post> getTopTenMostCommentedPosts() {
        return replyRepository.getTopTenMostCommentedPosts();
    }

    public void checkIfUserIsBlocked(User user) {
        if (user.isBlocked()) {
            throw new AuthorizationException(BLOCKED_USER_ERROR);
        }
    }

    public void checkIfUserIsCreator(int replyId, User user) {
        Reply reply = replyRepository.getReplyById(replyId);

        if (!(reply.getCreatedBy().equals(user))) {
            throw new AuthorizationException(USER_NOT_CREATOR_ERROR);
        }
    }

    public void checkIfUserIsAdmin(User user) {
        boolean isAdmin = true;

        try {
            adminService.getAdminByUserId(user.getId());
        } catch (EntityNotFoundException e) {
            isAdmin = false;
        }

        if (!isAdmin) {
            throw new AuthorizationException(USER_NOT_AUTHORIZED_DELETE_REPLY_ERROR);
        }

    }
}
