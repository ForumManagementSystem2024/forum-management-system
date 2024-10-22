package com.forum.forummanagementsystem.services;

import com.forum.forummanagementsystem.models.Admin;
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

import static com.forum.forummanagementsystem.Helpers.createMockAdmin;
import static com.forum.forummanagementsystem.Helpers.createMockUser;

@ExtendWith(MockitoExtension.class)
public class AdminServiceImplTests {

    @Mock
    AdminRepository mockAdminRepository;

    @Mock
    UserRepository mockUserRepository;

    @InjectMocks
    AdminServiceImpl mockAdminService;

    @Test
    public void getAdminById_Should_CallRepository() {
        mockAdminService.getAdminById(Mockito.anyInt());

        Mockito.verify(mockAdminRepository, Mockito.times(1))
                .getAdminById(Mockito.anyInt());
    }

    @Test
    public void getAdminById_Should_ReturnAdmin_When_MatchByIdExist() {
        Admin mockAdmin = createMockAdmin();

        Mockito.when(mockAdminRepository.getAdminById(Mockito.anyInt()))
                .thenReturn(mockAdmin);

        Admin result = mockAdminService.getAdminById(mockAdmin.getId());

        Assertions.assertEquals(mockAdmin, result);
    }

    @Test
    public void blockUser_Should_CallRepository() {
        mockAdminService.blockUser(Mockito.any());

        Mockito.verify(mockUserRepository, Mockito.times(1))
                .blockUser(Mockito.any());
    }

    @Test
    public void unblockUser_Should_CallRepository() {
        mockAdminService.unblockUser(Mockito.any());

        Mockito.verify(mockUserRepository, Mockito.times(1))
                .unblockUser(Mockito.any());
    }
}
