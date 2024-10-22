package com.forum.forummanagementsystem.repositories.interfaces;

import com.forum.forummanagementsystem.models.FilterOptions;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.Reply;
import com.forum.forummanagementsystem.models.User;

import java.util.List;

public interface ReplyRepository {
    List<Reply> get(FilterOptions filterOptions);
    Post getReplyById(int id);
    void createReply(Reply reply);
    void updateReply(Post post, User user, Reply reply);
    void deleteReply(Reply reply, User user);
}