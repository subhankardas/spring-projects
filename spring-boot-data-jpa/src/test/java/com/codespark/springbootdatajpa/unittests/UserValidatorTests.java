package com.codespark.springbootdatajpa.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codespark.springbootdatajpa.inputvalidation.User;

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
    public void validateSmallName() {
        User user = autoFillUserData();
        user.setName("Jo");

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertFalse(violations.isEmpty());
        assertEquals("Name should be within 3 to 30 characters.", violations.iterator().next().getMessage());
    }

    @Test
    public void validateNullPassword() {
        User user = autoFillUserData();
        user.setPassword(null);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());
    }

}
