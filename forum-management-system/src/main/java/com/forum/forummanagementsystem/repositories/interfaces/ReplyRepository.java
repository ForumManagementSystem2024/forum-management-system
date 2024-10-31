package com.forum.forummanagementsystem.repositories.interfaces;

import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.Reply;

import java.util.List;

public interface ReplyRepository {
    List<Reply> get(int postId);
    Reply getReplyById(int id);
    void createReply(Reply reply);
    void updateReply(Reply reply);
    void deleteReply(int id);
    List<Post> getTopTenMostCommentedPosts();
}