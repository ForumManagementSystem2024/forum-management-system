package com.forum.forummanagementsystem.services;

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

    @Mock
    AuthenticationHelper authenticationHelper;

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
}
