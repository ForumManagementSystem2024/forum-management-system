package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.models.FilterOptions;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.Reply;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.ReplyRepository;
import com.forum.forummanagementsystem.services.interfaces.ReplyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    public ReplyServiceImpl(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    @Override
    public List<Reply> getReplies(FilterOptions filterOptions) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Post getReplyById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void createReply(Post post, User user, Reply reply) {
        reply.setCreatedBy(user);
        reply.setPostId(post);
        replyRepository.createReply(reply);
    }

    @Override
    public void updateReply(Post post, User user, Reply reply) {

    }

    @Override
    public void deleteReply(Reply reply, User user) {

    }
}
