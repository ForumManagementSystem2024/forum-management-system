package com.forum.forummanagementsystem.models.dto;

import com.forum.forummanagementsystem.models.Reply;
import com.forum.forummanagementsystem.models.Tag;

import java.sql.Timestamp;
import java.util.Set;

public class PostDtoOut {

    private String title;
    private String content;
    private String createdBy;
    private int likes;
    private Set<ReplyDtoOut> replies;
    private Timestamp createdAt;
    private Set<TagDtoOut> tags;

    public PostDtoOut() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Set<ReplyDtoOut> getReplies() {
        return replies;
    }

    public void setReplies(Set<ReplyDtoOut> replies) {
        this.replies = replies;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Set<TagDtoOut> getTags() {
        return tags;
    }

    public void setTags(Set<TagDtoOut> tags) {
        this.tags = tags;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
