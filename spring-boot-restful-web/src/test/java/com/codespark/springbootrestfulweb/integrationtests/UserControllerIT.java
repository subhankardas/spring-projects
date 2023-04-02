package com.codespark.springbootrestfulweb.integrationtests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.codespark.springbootrestfulweb.restservice.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String GET_USER_BY_ID_URL = "http://localhost:%d/api/v1/users?id=%d";

    @Test
    public void assertUserControllerGetUserById0() {
        // Given
        int userID = 0;

        // When
        ResponseEntity<User> response = restTemplate.getForEntity(String.format(GET_USER_BY_ID_URL, port, userID),
                User.class);
        User user = response.getBody();

        // Then
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(user);
    }

    @Test
    public void assertUserControllerGetUserById1() {
        // Given
        int userID = 1;

        // When
        ResponseEntity<User> response = restTemplate.getForEntity(String.format(GET_USER_BY_ID_URL, port, userID),
                User.class);
        User user = response.getBody();

        // Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(user);
        assertEquals(userID, user.getId());
        assertEquals("John Doe", user.getName());
    }

    @Test
    public void assertUserControllerGetUserById2() {
        // Given
        int userID = 2;

        // When
        ResponseEntity<User> response = restTemplate.getForEntity(String.format(GET_USER_BY_ID_URL, port, userID),
                User.class);
        User user = response.getBody();

        // Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(user);
        assertEquals(userID, user.getId());
        assertEquals("Jane Doe", user.getName());
    }

}
