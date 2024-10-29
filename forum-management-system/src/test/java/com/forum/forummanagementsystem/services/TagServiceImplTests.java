package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.models.Tag;
import com.forum.forummanagementsystem.repositories.interfaces.TagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class TagServiceImplTests {

    @Mock
    private TagRepository mockTagRepository;

    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    public void findTagsByName_ShouldReturnExistingTags_WhenTagsExist() {
        // Arrange
        String existingTagName = "brownie";
        Tag existingTag = new Tag();
        existingTag.setTagName(existingTagName);

        Set<String> tagsNames = new HashSet<>();
        tagsNames.add(existingTagName);

        Mockito.when(mockTagRepository.findTagByName(existingTagName)).thenReturn(existingTag);

        // Act
        Set<Tag> resultTags = tagService.findTagsByName(tagsNames);

        // Assert
        Assertions.assertTrue(resultTags.contains(existingTag));
        Mockito.verify(mockTagRepository, Mockito.times(1)).findTagByName(existingTagName);
    }

    @Test
    public void findTagsByName_ShouldCreateAndReturnNewTags_WhenTagsDoNotExist() {
        // Arrange
        String newTagName = "brownie";

        Set<String> tagsNames = new HashSet<>();
        tagsNames.add(newTagName);

        Tag newTag = new Tag();
        Mockito.when(mockTagRepository.findTagByName(Mockito.anyString())).thenReturn(null);
        Mockito.when(mockTagRepository.createTag(Mockito.anyString())).thenReturn(newTag);

        // Act
        Set<Tag> resultTags = tagService.findTagsByName(tagsNames);

        // Assert
        Assertions.assertTrue(resultTags.contains(newTag));
        Mockito.verify(mockTagRepository, Mockito.times(1)).findTagByName(newTagName);
        Mockito.verify(mockTagRepository, Mockito.times(1)).createTag(newTagName);
    }

    @Test
    public void findTagsByName_ShouldHandleMixedExistingAndNewTags() {
        // Arrange
        String existingTagName = "chocolate";
        String newTagName = "brownie";

        Tag existingTag = new Tag();
        existingTag.setTagName(existingTagName);
        Tag newTag = new Tag();
        newTag.setTagName(newTagName);

        Set<String> tagsNames = Set.of(existingTagName, newTagName);

        Mockito.when(mockTagRepository.findTagByName(existingTagName)).thenReturn(existingTag);
        Mockito.when(mockTagRepository.findTagByName(newTagName)).thenReturn(null);
        Mockito.when(mockTagRepository.createTag(newTagName)).thenReturn(newTag);

        // Act
        Set<Tag> resultTags = tagService.findTagsByName(tagsNames);

        // Assert
        Assertions.assertTrue(resultTags.contains(existingTag));
        Assertions.assertTrue(resultTags.contains(newTag));
        Mockito.verify(mockTagRepository, Mockito.times(1)).findTagByName(existingTagName);
        Mockito.verify(mockTagRepository, Mockito.times(1)).findTagByName(newTagName);
        Mockito.verify(mockTagRepository, Mockito.times(1)).createTag(newTagName);
    }

    @Test
    public void createTag_ShouldCallRepositoryAndReturnNewTag() {
        // Arrange
        String tagName = "chocolate";
        Tag newTag = new Tag();
        newTag.setTagName(tagName);

        Mockito.when(mockTagRepository.createTag(tagName)).thenReturn(newTag);

        // Act
        Tag resultTag = tagService.createTag(tagName);

        // Assert
        Assertions.assertEquals(newTag, resultTag);
        Mockito.verify(mockTagRepository, Mockito.times(1)).createTag(tagName);
    }

}
