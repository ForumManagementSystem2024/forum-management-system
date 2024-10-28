package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.exceptions.AuthorizationException;
import com.forum.forummanagementsystem.exceptions.EntityDuplicateException;
import com.forum.forummanagementsystem.exceptions.EntityNotFoundException;
import com.forum.forummanagementsystem.models.FilterOptionsUser;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.repositories.interfaces.AdminRepository;
import com.forum.forummanagementsystem.repositories.interfaces.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.forum.forummanagementsystem.Helpers.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTests {

    @Mock
    UserRepository mockUserRepository;

    @Mock
    AdminRepository mockAdminRepository;

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
        Mockito.verify(mockUserRepository, times(1)).getByUsername(mockUser.getUsername());
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
        Mockito.verify(mockUserRepository, times(1)).getById(Mockito.anyInt());
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
        Mockito.verify(mockUserRepository, times(1)).search(filterOptionsUser);
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
        Mockito.verify(mockUserRepository, times(1)).search(filterOptionsUser);
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
        Mockito.verify(mockUserRepository, times(1))
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
        Mockito.verify(mockUserRepository, times(1))
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
        User mockUserAuthenticated = createMockUser();
        mockUserAuthenticated.setAdmin(true);

        // Act
        mockUserService.deleteUser(mockUser, mockUserAuthenticated);

        // Assert
        Mockito.verify(mockUserRepository, times(1)).deleteUser(mockUser);
    }

    @Test
    public void blockUser_Should_CallRepository() {
        // Arrange
        User mockUser = createMockUser();
        User mockUserAuthenticated = createMockUser();
        mockUserAuthenticated.setAdmin(true);

        // Act
        mockUserService.blockUser(mockUser, mockUserAuthenticated);

        // Assert
        Mockito.verify(mockUserRepository, times(1))
                .blockUser(Mockito.any());
    }

    @Test
    public void unblockUser_Should_CallRepository() {
        // Arrange
        User mockUser = createMockUser();
        User mockUserAuthenticated = createMockUser();
        mockUserAuthenticated.setAdmin(true);

        // Act
        mockUserService.unblockUser(mockUser, mockUserAuthenticated);

        // Assert
        Mockito.verify(mockUserRepository, times(1))
                .unblockUser(Mockito.any());
    }

    @Test
    public void makeAdmin_Should_ThrowAuthorizationException_When_UserIsNotAdmin() {
        // Arrange
        User userToMakeAdmin = createMockUser();
        User nonAdminUser = createMockUser();
        nonAdminUser.setAdmin(false);

        // Act & Assert
        Assertions.assertThrows(AuthorizationException.class, () ->
                mockUserService.makeAdmin(userToMakeAdmin, nonAdminUser)
        );
    }

    @Test
    public void makeAdmin_Should_SetAdminPrivileges_When_UserIsAdmin() {
        // Arrange
        User userToMakeAdmin = createMockUser();
        User adminUser = createMockUser();
        adminUser.setAdmin(true);

        // Act
        mockUserService.makeAdmin(userToMakeAdmin, adminUser);

        // Assert
        Mockito.verify(mockUserRepository, times(1)).updateProfile(userToMakeAdmin);
        Mockito.verify(mockAdminRepository, times(1)).makeAdmin(userToMakeAdmin);
        Assertions.assertTrue(userToMakeAdmin.isAdmin());
    }

//    @Test
//    public void makeAdmin_Should_NotModifyOtherUsers_When_Called() {
//        // Arrange
//        User userToMakeAdmin = createMockUser();
//        User anotherUser = createMockUser();
//        anotherUser.setAdmin(false);  // Unrelated user
//        User adminUser = createMockUser();
//        adminUser.setAdmin(true);
//
//        // Act
//        mockUserService.makeAdmin(userToMakeAdmin, adminUser);
//
//        // Assert
//        Mockito.verify(mockUserRepository, never()).updateProfile(anotherUser);
//        Mockito.verify(mockAdminRepository, never()).makeAdmin(anotherUser);
//    }

}
