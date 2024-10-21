package com.forum.forummanagementsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forum.forummanagementsystem.models.Post;
import com.forum.forummanagementsystem.models.User;
import com.forum.forummanagementsystem.models.dto.UserDto;

public class Helpers {

//    public static User createMockAdmin() {
//        User mockUser = createMockUser();
//        mockUser.setAdmin(true);
//        return mockUser;
//    }

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
        mockPost.setLikes(3);
        mockPost.setCreatedBy(createMockUser());

        return mockPost;
    }


//    public static FilterOptions createMockFilterOptions() {
//        return new FilterOptions(
//                "name",
//                0.0,
//                10.0,
//                1,
//                "sort",
//                "order");
//    }

    public static UserDto createUserDto() {
        UserDto dto = new UserDto();
        dto.setFirstName("MockFirstName");
        dto.setLastName("MockLastName");
        dto.setUsername("MockUsername");
        dto.setPassword("MockPassword");
        dto.setEmail("mock@user.com");

        return dto;
    }

    /**
     * Accepts an object and returns the stringified object.
     * Useful when you need to pass a body to a HTTP request.
     */
    public static String toJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
