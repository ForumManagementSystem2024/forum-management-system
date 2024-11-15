package com.forum.forummanagementsystem.models.dto;

import java.util.Optional;

public class FilterDto {

    private String title;
    private String createdByUsername;
    private String sortBy;
    private String sortOrder;
//    private String tagName;

    public FilterDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedByUsername() {
        return createdByUsername;
    }

    public void setCreatedByUsername(String createdByUsername) {
        this.createdByUsername = createdByUsername;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

//    public String getTagName() {
//        return tagName;
//    }
//
//    public void setTagName(String tagName) {
//        this.tagName = tagName;
//    }
}
