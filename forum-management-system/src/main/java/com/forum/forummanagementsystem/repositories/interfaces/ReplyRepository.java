package com.forum.forummanagementsystem.repositories.interfaces;

import com.forum.forummanagementsystem.models.FilterOptions;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.Reply;
import com.forum.forummanagementsystem.models.User;

import java.util.List;

public interface ReplyRepository {
    List<Reply> get(FilterOptions filterOptions);
    Reply getReplyById(int id);
    void createReply(Reply reply);
    void updateReply(Reply reply);
    void deleteReply(int id);
    List<Post> getTopTenMostCommentedPosts();
}