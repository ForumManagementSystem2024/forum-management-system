package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
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

    @Test
    void updateProfile_Should_CallRepository_When_UserIsOwner() {
        // Arrange
        User mockUser = createMockUser();
        User mockMappedUser = createMockUser();

        // Act
        mockUserService.updateProfile(mockUser, mockMappedUser);

        // Assert
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .updateProfile(mockMappedUser);
    }

    @Test
    public void updateProfile_Should_ThrowException_When_UserIsNotOwner() {
        // Arrange
        User mockUser = createMockUser();
        User mockMappedUser = createMockUser();
        mockMappedUser.setId(2);

        // Act, Assert
        Assertions.assertThrows(
                AuthorizationException.class,
                () -> mockUserService.updateProfile(mockUser,mockMappedUser));
    }
}
