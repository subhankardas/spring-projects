package com.codespark.springbootdatajpa.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codespark.springbootdatajpa.datajpa.UserEntity;
import com.codespark.springbootdatajpa.datajpa.UserRepository;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        UserEntity john = new UserEntity();
        john.setName("John Doe");
        john.setAge(18);
        john.setEmail("johndoe@me.com");

        UserEntity jane = new UserEntity();
        jane.setName("Jane Doe");
        jane.setAge(20);
        jane.setEmail("jane@me.com");

        // Given we have users in the database
        userRepository.save(john);
        userRepository.save(jane);
    }

    @Test
    public void assertFindByEmail() {
        // When we find a user by email
        UserEntity user1 = userRepository.findByEmail("johndoe@me.com");
        UserEntity user2 = userRepository.findByEmail("jane@me.com");

        // Then we should find the user
        assertEquals("John Doe", user1.getName());
        assertEquals(18, user1.getAge());
        assertEquals("johndoe@me.com", user1.getEmail());

        assertEquals("Jane Doe", user2.getName());
        assertEquals(20, user2.getAge());
        assertEquals("jane@me.com", user2.getEmail());
    }

}
