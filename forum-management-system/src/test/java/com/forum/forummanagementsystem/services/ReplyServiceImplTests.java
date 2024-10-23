package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.Reply;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.ReplyRepository;
import com.forum.forummanagementsystem.services.interfaces.AdminService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.forum.forummanagementsystem.Helpers.*;

@ExtendWith(MockitoExtension.class)
public class ReplyServiceImplTests {

    @Mock
    ReplyRepository mockReplyRepository;

    @Mock
    AdminService mockAdminService;

    @InjectMocks
    ReplyServiceImpl mockReplyService;

    @Test
    public void get_Should_ReturnReply_When_MatchByIdExist() {
        // Arrange
        Reply mockReply = createMockReply();

        Mockito.when(mockReplyRepository.getReplyById(Mockito.anyInt()))
                .thenReturn(mockReply);

        // Act
        Reply result = mockReplyService.getReplyById(mockReply.getId());

        // Assert
        Assertions.assertEquals(mockReply, result);
    }

    @Test
    public void createReply_Should_CallRepository(){
        // Arrange
        User mockUser = createMockUser();
        Post mockPost = createMockPost();
        Reply mockReply = createMockReply();

        // Act
        mockReplyService.createReply(mockPost, mockUser, mockReply);

        // Assert
        Mockito.verify(mockReplyRepository, Mockito.times(1))
                .createReply(mockReply);
    }

    @Test
    public void createReply_Should_Throw_When_UserIsBlocked(){
        // Arrange
        Post mockPost = createMockPost();
        Reply mockReply = createMockReply();
        User mockUser = createMockUser();
        mockUser.setBlocked(true);

        // Act, Assert
        Assertions.assertThrows(
                AuthorizationException.class,
                () -> mockReplyService.createReply(mockPost, mockUser, mockReply));
    }

    @Test
    void updateReply_Should_CallRepository_When_UserIsCreator() {
        //Arrange
        Reply mockReply = createMockReply();
        User mockUserCreator = mockReply.getCreatedBy();

        Mockito.when(mockReplyRepository.getReplyById(Mockito.anyInt()))
                .thenReturn(mockReply);

        //Act
        mockReplyService.updateReply(mockUserCreator, mockReply);

        //Assert
        Mockito.verify(mockReplyRepository, Mockito.times(1))
                .updateReply(mockReply);
    }

    @Test
    public void updateReply_Should_ThrowException_When_UserIsNotCreator() {
        // Arrange
        Reply mockReply = createMockReply();

        Mockito.when(mockReplyRepository.getReplyById(Mockito.anyInt()))
                .thenReturn(mockReply);

        // Act, Assert
        Assertions.assertThrows(
                AuthorizationException.class,
                () -> mockReplyService.updateReply(Mockito.any(), mockReply));
    }

    @Test
    void delete_Should_CallRepository_When_UserIsCreator() {
        // Arrange
        Reply mockReply = createMockReply();
        User mockUserCreator = mockReply.getCreatedBy();

        Mockito.when(mockReplyRepository.getReplyById(Mockito.anyInt()))
                .thenReturn(mockReply);

        // Act
        mockReplyService.deleteReply(1, mockUserCreator);

        // Assert
        Mockito.verify(mockReplyRepository, Mockito.times(1))
                .deleteReply(1);
    }

    @Test
    void delete_Should_CallRepository_When_UserIsAdmin() {
        // Arrange
        Reply mockReply = createMockReply();
        User mockUserAdmin = createMockUser();

        Mockito.when(mockReplyRepository.getReplyById(Mockito.anyInt()))
                .thenReturn(mockReply);

        // Act
        mockReplyService.deleteReply(1, mockUserAdmin);

        // Assert
        Mockito.verify(mockReplyRepository, Mockito.times(1))
                .deleteReply(1);
    }

    @Test
    public void checkIfUserIsAdmin_Should_ThrowAuthorizationException_When_UserIsNotAdmin() {
        // Arrange
        User mockUser = createMockUser();
        Mockito.doThrow(new EntityNotFoundException("Admin", 1))
                .when(mockAdminService).getAdminByUserId(mockUser.getId());

        // Act, Assert
        Assertions.assertThrows(AuthorizationException.class, () -> {
            mockReplyService.checkIfUserIsAdmin(mockUser);
        });
    }


}
