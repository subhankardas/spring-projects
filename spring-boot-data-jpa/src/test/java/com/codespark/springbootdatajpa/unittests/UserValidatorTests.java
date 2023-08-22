package com.codespark.springbootdatajpa.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codespark.springbootdatajpa.inputvalidation.User;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@SpringBootTest
public class UserValidatorTests {

    @Autowired
    private Validator validator;

    public User autoFillUserData() {
        User user = new User();
        user.setName("John Doe");
        user.setAge(18);
        user.setEmail("johndoe@me.com");
        user.setAddress("London");
        user.setRegisteredOn(LocalDate.now());
        user.setIpAddress("123.456.789.001");
        user.setPassword("John@123");
        return user;
    }

    @Test
    public void validateSmallNameViolation() {
        // Given a name that is too short
        User user = autoFillUserData();
        user.setName("Jo");

        // When we validate it
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then we should get a violation
        assertFalse(violations.isEmpty());
        assertEquals("Name should be within 3 to 30 characters.", violations.iterator().next().getMessage());
    }

    @Test
    public void validateNullPasswordViolation() {
        // Given a null password
        User user = autoFillUserData();
        user.setPassword(null);

        // When we validate it
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then we should get a violation
        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());
    }

}
