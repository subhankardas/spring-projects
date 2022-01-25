package com.codespark.springbootbasics.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.codespark.springbootbasics.restfulweb.ResponseModel;

/**
 * 2. Assert the behavior of the application, start the application and listen
 * for a connection (as it would do in production) and then send an HTTP request
 * and assert the response. The application must be running for the HTTP tests
 * to succeed.
 * 
 * Note the use of webEnvironment=RANDOM_PORT to start the server with a random
 * port (useful to avoid conflicts in test environments) and the injection of
 * the port with @LocalServerPort. Also, note that Spring Boot has automatically
 * provided a TestRestTemplate for you.
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SimpleControllerTestIT {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private static final String GET_USER_BY_ID_URL = "http://localhost:%d/api/users?id=%d";

	@Test
	public void assertSimpleControllerGetUserById() throws Exception {
		int mockUserId = 1234;
		String mockUsername = "Test User 1";
		String url = String.format(GET_USER_BY_ID_URL, port, mockUserId);

		// Create mock data for assertion
		ResponseModel mock = new ResponseModel();
		mock.setUserId(mockUserId);
		mock.setUsername(mockUsername);

		// HTTP request to the controller end-point
		ResponseModel response = restTemplate.getForObject(url, ResponseModel.class);

		// Assertion
		assertThat(response).isNotNull();
		assertThat(response.getUserId()).isEqualTo(mockUserId);
		assertThat(response.getUsername()).isEqualTo(mockUsername);
	}

}
