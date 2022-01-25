package com.codespark.springbootbasics.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.codespark.springbootbasics.restfulweb.ResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 3. Another useful approach is to not start the server at all but to test only
 * the layer below that, where Spring handles the incoming HTTP request and
 * hands it off to your controller. To do that, use Spring’s MockMvc and ask for
 * that to be injected for you by using the @AutoConfigureMockMvc annotation on
 * the test case. In this test, the full Spring application context is started
 * but without the server.
 * 
 * We can narrow the tests to only the web layer by using @WebMvcTest, in that
 * case, Spring Boot instantiates only the web layer rather than the whole
 * context. In an application with multiple controllers, you can even ask for
 * only one to be instantiated by using, for
 * example, @WebMvcTest(HomeController.class).
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class WebLayerTestIT {

	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private static final String GET_USER_BY_ID_URL = "http://localhost:%d/api/users?id=%d";

	@Test
	public void assertSimpleControllerGetUserById() throws UnsupportedEncodingException, Exception {
		int mockUserId = 1234;
		String mockUsername = "Test User 1";
		String url = String.format(GET_USER_BY_ID_URL, port, mockUserId);

		// Create mock data for assertion
		ResponseModel mock = new ResponseModel();
		mock.setUserId(mockUserId);
		mock.setUsername(mockUsername);

		// Mock HTTP request to the controller end-point and status code assertion
		MvcResult result = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();

		ResponseModel response = objectMapper.readValue(content, ResponseModel.class);

		// Assertion
		assertThat(response).isNotNull();
		assertThat(response.getUserId()).isEqualTo(mockUserId);
		assertThat(response.getUsername()).isEqualTo(mockUsername);
	}

}
