package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.Reply;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.ReplyRepository;
import com.forum.forummanagementsystem.services.interfaces.ReplyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {
    public static final String BLOCKED_USER_ERROR =
            "You are not allowed to create a reply! Please contact customer support!";
    private static final String MODIFY_REPLY_ERROR_MESSAGE = "Only admin or reply creator can modify a reply.";

    private final ReplyRepository replyRepository;

    public ReplyServiceImpl(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
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
        checkModifyPermissions(reply.getId(), user);

        replyRepository.updateReply(reply);
    }

    @Override
    public void deleteReply(int replyId, User user) {
        checkModifyPermissions(replyId, user);

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

    private void checkModifyPermissions(int replyId, User user) {
        Reply reply = replyRepository.getReplyById(replyId);
        if (!(user.isAdmin() || reply.getCreatedBy().equals(user))) {
            throw new AuthorizationException(MODIFY_REPLY_ERROR_MESSAGE);
        }
    }
}
