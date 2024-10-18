package com.forum.forummanagementsystem.repositories.interfaces;

import com.forum.forummanagementsystem.models.FilterOptions;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.Reply;
import com.forum.forummanagementsystem.models.User;

import java.util.List;

public interface ReplyRepository {
    List<Reply> get(FilterOptions filterOptions);
    Post getReplyById(int id);
    void create(Post post, User user, Reply reply);
    void update(Post post, User user, Reply reply);
    void delete(Reply reply, User user);
}