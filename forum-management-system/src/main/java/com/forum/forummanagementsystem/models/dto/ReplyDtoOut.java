package com.forum.forummanagementsystem.models.dto;

import java.sql.Timestamp;

public class ReplyDtoOut {

    private String createdBy;
    private String content;
    private Timestamp createdAt;

    public ReplyDtoOut() {
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
