package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.exceptions.EntityDuplicateException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.forum.forummanagementsystem.Helpers.createMockUser;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTests {

    @Mock
    UserRepository mockUserRepository;

    @InjectMocks
    UserServiceImpl mockUserService;

    @Test
    public void registerUser_Should_CallRepository_When_UserWithSameUsernameDoesNotExist() {
        // Arrange
        User mockUser = createMockUser();

        Mockito.when(mockUserRepository.getByUsername(Mockito.anyString()))
                .thenThrow(EntityNotFoundException.class);

        // Act
        mockUserService.register(mockUser);

        // Assert
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .register(mockUser);
    }

    @Test
    public void registerUser_Should_Throw_When_UserWithSameUsernameExists() {
        // Arrange
        User mockUser = createMockUser();

        Mockito.when(mockUserRepository.getByUsername(Mockito.anyString()))
                .thenReturn(mockUser);

        // Act, Assert

        Assertions.assertThrows(EntityDuplicateException.class,
                ()->mockUserService.register(mockUser));

    }
}
