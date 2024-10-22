package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.Reply;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.ReplyRepository;
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

    @InjectMocks
    ReplyServiceImpl mockReplyService;

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


}
