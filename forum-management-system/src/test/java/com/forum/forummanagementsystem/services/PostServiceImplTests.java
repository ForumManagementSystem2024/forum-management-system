package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityDuplicateException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.models.*;
import com.forum.forummanagementsystem.repositories.interfaces.LikeRepository;
import com.forum.forummanagementsystem.repositories.interfaces.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.HashSet;
import static com.forum.forummanagementsystem.Helpers.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceImplTests {

    @Mock
    PostRepository mockPostRepository;

    @Mock
    LikeRepository mockLikeRepository;

    @InjectMocks
    PostServiceImpl mockPostService;

    @Test
    void getAllPosts_Should_CallRepository() {
        // Arrange
        FilterOptions mockFilterOptions = createMockFilterOptions();
        Mockito.when(mockPostRepository.getAllPosts(mockFilterOptions))
                .thenReturn(null);

        // Act
        mockPostService.getAllPosts(mockFilterOptions);

        // Assert
        Mockito.verify(mockPostRepository, Mockito.times(1))
                .getAllPosts(mockFilterOptions);
    }

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
    public void create_Should_Throw_When_UserIsBlocked() {
        // Arrange
        Post mockPost = createMockPost();
        User mockUser = createMockUser();
        mockUser.setBlocked(true);

        // Act, Assert
        Assertions.assertThrows(
                AuthorizationException.class,
                () -> mockPostService.create(mockPost, mockUser));
    }

    @Test
    void update_Should_CallRepository_When_UserIsCreator() {
        //Arrange
        Post mockPost = createMockPost();
        User mockUserCreator = mockPost.getCreatedBy();

        Mockito.when(mockPostRepository.getPostById(Mockito.anyInt()))
                .thenReturn(mockPost);

        Mockito.when(mockPostRepository.getPostByTitle(Mockito.anyString()))
                .thenThrow(EntityNotFoundException.class);

        //Act
        mockPostService.update(mockPost, mockUserCreator);

        //Assert
        Mockito.verify(mockPostRepository, Mockito.times(1))
                .update(mockPost);
    }

    @Test
    public void update_Should_CallRepository_When_UpdatingExistingPost() {
        // Arrange
        Post mockPost = createMockPost();
        User mockUserCreator = mockPost.getCreatedBy();

        Mockito.when(mockPostRepository.getPostById(Mockito.anyInt()))
                .thenReturn(mockPost);

        Mockito.when(mockPostRepository.getPostByTitle(Mockito.anyString()))
                .thenReturn(mockPost);

        // Act
        mockPostService.update(mockPost, mockUserCreator);

        // Assert
        Mockito.verify(mockPostRepository, Mockito.times(1))
                .update(mockPost);
    }

    @Test
    public void update_Should_ThrowException_When_UserIsNotCreator() {
        // Arrange
        Post mockPost = createMockPost();
        User mockNonCreatorUser = createMockUser();
        mockNonCreatorUser.setId(mockPost.getCreatedBy().getId() + 1);

        Mockito.when(mockPostRepository.getPostById(mockPost.getId()))
                .thenReturn(mockPost);

        Mockito.lenient().when(mockPostRepository.getPostByTitle(mockPost.getTitle()))
                .thenThrow(EntityNotFoundException.class);

        //TODO check if the below is needed
//        Mockito.when(adminService.getAdminByUserId(mockNonCreatorUser.getId()))
//                .thenThrow(EntityNotFoundException.class);

        // Act, Assert
        Assertions.assertThrows(
                AuthorizationException.class,
                () -> mockPostService.update(mockPost, mockNonCreatorUser));
    }

    @Test
    public void update_Should_ThrowException_When_PostTitleIsTaken() {
        // Arrange
        Post mockPost = createMockPost();

        User mockUserCreator = mockPost.getCreatedBy();

        Mockito.when(mockPostRepository.getPostById(Mockito.anyInt()))
                .thenReturn(mockPost);

        Post mockExistingPostWithSameTitle = createMockPost();
        mockExistingPostWithSameTitle.setId(2);

        Mockito.when(mockPostRepository.getPostByTitle(mockPost.getTitle()))
                .thenReturn(mockExistingPostWithSameTitle);

        // Act, Assert
        Assertions.assertThrows(
                EntityDuplicateException.class,
                () -> mockPostService.update(mockPost, mockUserCreator));
    }

    @Test
    public void delete_Should_CallRepository_When_UserIsCreator() {
        Post mockPost = createMockPost();
        User mockUserOwner = mockPost.getCreatedBy();

        Mockito.when(mockPostRepository.getPostById(Mockito.anyInt()))
                .thenReturn(mockPost);

        mockPostService.delete(mockPost.getId(), mockUserOwner);

        Mockito.verify(mockPostRepository, Mockito.times(1))
                .delete(mockPost.getId());

    }

    @Test
    public void delete_Should_CallRepository_When_UserIsAdmin() {
        Post mockPost = createMockPost();

        User mockAdminUser = createMockUser();
        mockAdminUser.setAdmin(true);

        Mockito.when(mockPostRepository.getPostById(Mockito.anyInt()))
                .thenReturn(mockPost);

        mockPostService.delete(mockPost.getId(), mockAdminUser);

        Mockito.verify(mockPostRepository, Mockito.times(1))
                .delete(mockPost.getId());
    }

    @Test
    public void delete_Should_ThrowException_When_UserIsNotCreatorNorAdmin() {
        Post mockPost = createMockPost();
        User mockCreator = createMockUser();
        mockPost.setCreatedBy(mockCreator);
        User mockNonCreator = createMockUser();
        mockNonCreator.setId(2);

        Mockito.when(mockPostRepository.getPostById(Mockito.anyInt()))
                .thenReturn(mockPost);

        //TODO check if the below is needed
//        Mockito.when(adminService.getAdminByUserId(mockNonCreator.getId()))
//                .thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(
                AuthorizationException.class,
                () -> mockPostService.delete(mockPost.getId(), mockNonCreator));

        Mockito.verify(mockPostRepository, Mockito.never()).delete(Mockito.anyInt());
    }

    @Test
    public void likePost_Should_IncrementLikes_When_UserHasNotLikedBefore() {
        // Arrange
        Post mockPost = createMockPost();
        User mockUser = createMockUser();
        mockPost.setLikes(new HashSet<>());

        Mockito.when(mockPostRepository.getPostById(mockPost.getId()))
                .thenReturn(mockPost);
//        Mockito.when(mockLikeRepository.existsByUserIdAndPostId(mockUser.getId(), mockPost.getId()))
//                .thenReturn(null);

        // Act
        Post result = mockPostService.likePost(mockPost.getId(), mockUser);

        // Assert
        Assertions.assertEquals(1, result.getLikes().size());
        Mockito.verify(mockLikeRepository, Mockito.times(1)).save(Mockito.any(Like.class));
        Mockito.verify(mockPostRepository, Mockito.times(1)).update(mockPost);
    }

    @Test
    public void likePost_Should_RemoveLike_When_UserHasLikedBefore() {
        // Arrange
        Post mockPost = createMockPost();
        User mockUser = createMockUser();
        Like mockLike = createMockLike();
        mockLike.setUser(mockUser);
        mockLike.setPost(mockPost);
        mockPost.setLikes(new HashSet<>(Collections.singleton(mockLike)));

        Mockito.when(mockPostRepository.getPostById(mockPost.getId()))
                .thenReturn(mockPost);

        // Act
        Post result = mockPostService.likePost(mockPost.getId(), mockUser);

        // Assert
        Assertions.assertEquals(0, result.getLikes().size());
        Mockito.verify(mockLikeRepository, Mockito.times(1)).removeLike(mockLike);
        Mockito.verify(mockPostRepository, Mockito.times(1)).update(mockPost);
    }

    @Test
    public void likePost_Should_ThrowException_When_UserIsBlocked() {
        // Arrange
        Post mockPost = createMockPost();
        User mockUser = createMockUser();
        mockUser.setBlocked(true);

        // Act, Assert
        Assertions.assertThrows(
                AuthorizationException.class,
                () -> mockPostService.likePost(mockPost.getId(), mockUser));
    }

    @Test
    public void likePost_Should_ThrowException_When_PostDoesNotExist() {
        // Arrange
        User mockUser = createMockUser();

        Mockito.when(mockPostRepository.getPostById(Mockito.anyInt()))
                .thenThrow(EntityNotFoundException.class);

        // Act, Assert
        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> mockPostService.likePost(1, mockUser));
    }

    @Test
    public void removeLikePost_Should_DecrementLikesAndRemoveLike() {
        // Arrange
        Post mockPost = createMockPost();
        Like mockLike = new Like();
        mockLike.setUser(mockPost.getCreatedBy());

        Mockito.when(mockPostRepository.getPostById(mockPost.getId())).thenReturn(mockPost);

        // Act
        Post result = mockPostService.removeLikePost(mockPost, mockLike);

        // Assert
        Assertions.assertEquals(0, result.getLikes().size());
        Mockito.verify(mockPostRepository, Mockito.times(1)).update(mockPost);
        Mockito.verify(mockLikeRepository, Mockito.times(1)).removeLike(mockLike);
    }

    @Test
    void getTopTenMostRecentPosts_Should_CallRepository() {
        // Act
        mockPostService.getTopTenMostRecentPosts();

        // Assert
        Mockito.verify(mockPostRepository, Mockito.times(1)).getTopTenMostRecentPosts();
    }
}