package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityDuplicateException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.models.FilterOptionsUser;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.forum.forummanagementsystem.Helpers.createMockFilterOptionsUser;
import static com.forum.forummanagementsystem.Helpers.createMockUser;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTests {

    @Mock
    UserRepository mockUserRepository;

    @InjectMocks
    UserServiceImpl mockUserService;

    @Test
    public void getByUsername_Should_ReturnUser_When_UserExists() {
        // Arrange
        User mockUser = createMockUser();
        Mockito.when(mockUserRepository.getByUsername(mockUser.getUsername())).thenReturn(mockUser);

        // Act
        User result = mockUserService.getByUsername(mockUser.getUsername());

        // Assert
        Assertions.assertEquals(mockUser, result);
        Mockito.verify(mockUserRepository, Mockito.times(1)).getByUsername(mockUser.getUsername());
    }

    @Test
    public void getByUsername_Should_ThrowEntityNotFoundException_When_UserDoesNotExist() {
        // Arrange
        Mockito.when(mockUserRepository.getByUsername(Mockito.anyString())).thenThrow(EntityNotFoundException.class);

        // Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            mockUserService.getByUsername(Mockito.anyString());
        });
    }

    @Test
    public void getUserById_Should_ReturnUser_When_UserExists() {
        // Arrange
        User mockUser = createMockUser();
        Mockito.when(mockUserRepository.getById(Mockito.anyInt())).thenReturn(mockUser);

        // Act
        User result = mockUserService.getUserById(Mockito.anyInt());

        // Assert
        Assertions.assertEquals(mockUser, result);
        Mockito.verify(mockUserRepository, Mockito.times(1)).getById(Mockito.anyInt());
    }

    @Test
    public void getUserById_Should_ThrowEntityNotFoundException_When_UserDoesNotExist() {
        // Arrange
        Mockito.when(mockUserRepository.getById(Mockito.anyInt())).thenThrow(EntityNotFoundException.class);

        // Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            mockUserService.getUserById(Mockito.anyInt());
        });
    }

    @Test
    public void search_Should_ReturnUserList_When_UsersFound() {
        // Arrange
        FilterOptionsUser filterOptionsUser = createMockFilterOptionsUser();
        List<User> mockUserList = List.of(createMockUser(), createMockUser());
        Mockito.when(mockUserRepository.search(filterOptionsUser)).thenReturn(mockUserList);

        // Act
        List<User> result = mockUserService.search(filterOptionsUser);

        // Assert
        Assertions.assertEquals(mockUserList.size(), result.size());
        Mockito.verify(mockUserRepository, Mockito.times(1)).search(filterOptionsUser);
    }

    @Test
    public void search_Should_ReturnEmptyList_When_NoUsersFound() {
        // Arrange
        FilterOptionsUser filterOptionsUser = createMockFilterOptionsUser();
        Mockito.when(mockUserRepository.search(filterOptionsUser)).thenReturn(List.of());

        // Act
        List<User> result = mockUserService.search(filterOptionsUser);

        // Assert
        Assertions.assertTrue(result.isEmpty());
        Mockito.verify(mockUserRepository, Mockito.times(1)).search(filterOptionsUser);
    }

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

    @Test
    public void deleteUser_Should_CallRepository_When_UserExists() {
        // Arrange
        User mockUser = createMockUser();

        // Act
        mockUserService.deleteUser(mockUser);

        // Assert
        Mockito.verify(mockUserRepository, Mockito.times(1)).deleteUser(mockUser);
    }
}
