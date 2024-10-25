package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.models.Tag;
import com.forum.forummanagementsystem.repositories.interfaces.TagRepository;
import com.forum.forummanagementsystem.services.interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Set<Tag> findTagsByName(Set<String> tagsNames) {
        Set<Tag> resultTags = new HashSet<>();

        for (String tagName : tagsNames) {
            Tag tag = tagRepository.findTagByName(tagName);

            if (tag != null) {
                resultTags.add(tag);
            } else {
                Tag newTag = createTag(tagName);
                resultTags.add(newTag);
            }
        }

        return resultTags;
    }

    @Override
    public Tag createTag(String tagName) {
        return tagRepository.createTag(tagName);
    }
}
