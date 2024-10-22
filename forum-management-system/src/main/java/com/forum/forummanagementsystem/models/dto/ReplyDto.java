package com.forum.forummanagementsystem.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReplyDto {

    @NotNull(message = "Content cannot be empty!")
    @Size(min = 32, max = 8192, message = "Content should be between 32 and 8192 symbols!")
    private String content;

    public ReplyDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
