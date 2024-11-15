package com.forum.forummanagementsystem.models;

import java.util.Optional;

public class FilterOptions {

    private Optional<String> title;
    private Optional<String> createdByUsername;
    private Optional<String> sortBy;
    private Optional<String> sortOrder;
//    private Optional<String> tagName;

//    public FilterOptions(String title, String createdBy, String tagName, String sortBy, String sortOrder) {
//        this.title = Optional.ofNullable(title);
//        this.createdByUsername = Optional.ofNullable(createdBy);
//        this.tagName = Optional.ofNullable(tagName);
//        this.sortBy = Optional.ofNullable(sortBy);
//        this.sortOrder = Optional.ofNullable(sortOrder);
//    }

    public FilterOptions(String title, String createdBy, String sortBy, String sortOrder) {
        this.title = Optional.ofNullable(title);
        this.createdByUsername = Optional.ofNullable(createdBy);
        this.sortBy = Optional.ofNullable(sortBy);
        this.sortOrder = Optional.ofNullable(sortOrder);
    }

    public Optional<String> getTitle() {
        return title;
    }

    public Optional<String> getCreatedByUsername() {
        return createdByUsername;
    }

//    public Optional<String> getTagName() {
//        return tagName;
//    }

    public Optional<String> getSortBy() {
        return sortBy;
    }

    public Optional<String> getSortOrder() {
        return sortOrder;
    }
}
