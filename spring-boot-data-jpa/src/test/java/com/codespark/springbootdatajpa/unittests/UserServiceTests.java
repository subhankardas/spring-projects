package com.codespark.springbootdatajpa.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.codespark.springbootdatajpa.datajpa.UserEntity;
import com.codespark.springbootdatajpa.datajpa.UserRepository;
import com.codespark.springbootdatajpa.datajpa.UserService;
import com.codespark.springbootdatajpa.inputvalidation.User;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserEntity getMockUser() {
        UserEntity mockUser = new UserEntity();
        mockUser.setId(1L);
        mockUser.setName("John Doe");
        mockUser.setAge(25);
        mockUser.setEmail("john@doe.com");
        mockUser.setAddress("123 Main St");
        mockUser.setRegisteredOn(LocalDate.now());
        mockUser.setIpAddress("127.0.0.1");
        mockUser.setPassword("John@123");
        return mockUser;
    }

    @Test
    public void assertFindUserByEmail() {
        // Given mock user is returned by the repository
        UserEntity mockUser = getMockUser();
        when(userRepository.findByEmail("john@doe.com")).thenReturn(mockUser);

        // When user is requested by email
        User user = userService.findUserByEmail("john@doe.com");

        // Then user is asserted to have correct values
        assertNotNull(user);
        assertEquals("John Doe", user.getName());
        assertEquals(25, user.getAge());
        assertEquals("john@doe.com", user.getEmail());
        assertEquals("123 Main St", user.getAddress());
    }

}
