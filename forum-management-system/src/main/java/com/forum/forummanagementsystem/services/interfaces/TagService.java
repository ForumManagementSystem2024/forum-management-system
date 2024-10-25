package com.forum.forummanagementsystem.services.interfaces;

import com.forum.forummanagementsystem.models.Tag;

import java.util.Set;

public interface TagService {

    Set<Tag> findTagsByName(Set<String> tags);

    Tag createTag(String tagName);
}
