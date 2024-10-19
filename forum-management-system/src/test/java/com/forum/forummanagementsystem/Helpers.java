package com.forum.forummanagementsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
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

//    public static Beer createMockBeer() {
//        var mockBeer = new Beer();
//        mockBeer.setId(1);
//        mockBeer.setName("MockBeer");
//        mockBeer.setCreatedBy(createMockUser());
//        mockBeer.setStyle(createMockStyle());
//        return mockBeer;
//    }


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
