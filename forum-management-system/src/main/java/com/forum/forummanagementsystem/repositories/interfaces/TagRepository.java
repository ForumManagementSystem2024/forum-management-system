package com.forum.forummanagementsystem.repositories.interfaces;

import com.forum.forummanagementsystem.models.Tag;

public interface TagRepository {

    Tag findTagByName(String name);
    Tag createTag(String tagName);
}
