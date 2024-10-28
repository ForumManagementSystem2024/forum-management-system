package com.forum.forummanagementsystem;

import com.forum.forummanagementsystem.models.*;
import com.forum.forummanagementsystem.models.dto.UserDto;

public class Helpers {

    public static Admin createMockAdmin() {
        Admin mockAdmin = new Admin();
        mockAdmin.setId(1);
        mockAdmin.setUser(createMockUser());

        return mockAdmin;
    }

    public static User createMockUser() {
        var mockUser = new User();
        mockUser.setId(1);
        mockUser.setUsername("MockUsername");
        mockUser.setPassword("MockPassword");
        mockUser.setLastName("MockLastName");
        mockUser.setFirstName("MockFirstName");
        mockUser.setEmail("mock@user.com");

        return mockUser;
    }

    public static Post createMockPost() {
        Post mockPost = new Post();
        mockPost.setId(1);
        mockPost.setTitle("MockPost");
        mockPost.setContent("A History of Chocolate: From Mesoamerica to the Modern World', 'Chocolate has been cherished for thousands of years, dating back to the ancient civilizations of Mesoamerica. The Mayans and Aztecs believed cocoa beans were a gift from the gods, and today, chocolate is enjoyed in countless forms around the world.");
        mockPost.setCreatedBy(createMockUser());

        return mockPost;
    }

    public static Reply createMockReply(){
        Reply reply = new Reply();
        reply.setId(1);
        reply.setCreatedBy(createMockUser());
        reply.setPostId(createMockPost());
        reply.setContent("Very good! Perfect cake! The best in the world!");

        return reply;
    }

    public static FilterOptions createMockFilterOptions() {
        return new FilterOptions(
                "mockTitle",
                "mockUsername",
                "title",
                "desc"
        );
    }

    public static FilterOptionsUser createMockFilterOptionsUser() {
        return new FilterOptionsUser(
                "mockUsername",
                "mockEmailUser",
                "mockFirstName"
        );
    }

    public static UserDto createUserDto() {
        UserDto dto = new UserDto();
        dto.setFirstName("MockFirstName");
        dto.setLastName("MockLastName");
        dto.setUsername("MockUsername");
        dto.setPassword("MockPassword");
        dto.setEmail("mock@user.com");

        return dto;
    }
}
