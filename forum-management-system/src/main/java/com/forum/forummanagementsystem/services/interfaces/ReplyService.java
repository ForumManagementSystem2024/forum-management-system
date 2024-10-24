package com.forum.forummanagementsystem.services.interfaces;

import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.Reply;
import com.forum.forummanagementsystem.models.User;

import java.util.List;

public interface ReplyService {
    Reply getReplyById(int id);
    void createReply(Post post, User user, Reply reply);
    void updateReply(User user, Reply reply);
    void deleteReply(int replyId, User user);
    List<Post> getTopTenMostCommentedPosts();
}
