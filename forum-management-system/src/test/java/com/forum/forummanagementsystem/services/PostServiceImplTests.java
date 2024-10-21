package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityDuplicateException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.helpers.AuthenticationHelper;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.PostRepository;
import com.forum.forummanagementsystem.services.interfaces.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.forum.forummanagementsystem.Helpers.createMockPost;
import static com.forum.forummanagementsystem.Helpers.createMockUser;

@ExtendWith(MockitoExtension.class)
public class PostServiceImplTests {

    @Mock
    PostRepository mockPostRepository;

    @InjectMocks
    PostServiceImpl mockPostService;

    @Test
    public void get_Should_ReturnPost_When_MatchByIdExist() {
        // Arrange
        Post mockPost = createMockPost();

        Mockito.when(mockPostRepository.getPostById(Mockito.anyInt()))
                .thenReturn(mockPost);

        // Act
        Post result = mockPostService.getPostById(mockPost.getId());

        // Assert
        Assertions.assertEquals(mockPost, result);
    }

    @Test
    public void create_Should_CallRepository_When_PostWithSameTitleDoesNotExists() {
        // Arrange
        Post mockPost = createMockPost();
        User mockUser = createMockUser();
        mockPost.setCreatedBy(mockUser);

        Mockito.when(mockPostRepository.getPostByTitle(Mockito.anyString()))
                .thenThrow(EntityNotFoundException.class);

        // Act
        mockPostService.create(mockPost, mockUser);

        // Assert
        Mockito.verify(mockPostRepository, Mockito.times(1))
                .create(mockPost);
    }

    @Test
    public void create_Should_Throw_When_PostWithSameTitleExists() {
        // Arrange
        Post mockPost = createMockPost();
        User mockUser = createMockUser();
        mockPost.setCreatedBy(mockUser);

        Mockito.when(mockPostRepository.getPostByTitle(Mockito.anyString()))
                .thenReturn(mockPost);

        // Act, Assert
        Assertions.assertThrows(
                EntityDuplicateException.class,
                () -> mockPostService.create(mockPost, mockUser));
    }

    @Test
    public void create_Should_Throw_When_UserIsBlocked(){
        // Arrange
        Post mockPost = createMockPost();
        User mockUser = createMockUser();
        mockUser.setBlocked(true);

        // Act, Assert
        Assertions.assertThrows(
                AuthorizationException.class,
                () -> mockPostService.create(mockPost, mockUser));
    }
}
