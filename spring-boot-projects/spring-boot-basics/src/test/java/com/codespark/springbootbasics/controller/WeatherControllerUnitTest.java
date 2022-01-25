package com.codespark.springbootbasics.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.codespark.springbootbasics.restconsumer.RestConsumerController;
import com.codespark.springbootbasics.restconsumer.SimpleRestConsumer;
import com.codespark.springbootbasics.restconsumer.WeatherData;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 4. The @MockBean annotation is used to create and inject a mock for the
 * SimpleRestConsumer, which is a service component used by the
 * RestConsumerController, and we set its expectations using Mockito. Missing
 * mock dependency injection using @MockBean will result in failing to load
 * application context. These test cases will pass even if application is not
 * running.
 */
@WebMvcTest(controllers = { RestConsumerController.class })
public class WeatherControllerUnitTest {

	@Value("${server.port}")
	private int port;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SimpleRestConsumer restConsumer;

	@Autowired
	private ObjectMapper objectMapper;

	private static final String GET_WEATHER_DATA_URL = "http://localhost:%d/api/weather?location=%s";

	private WeatherData mock;

	@BeforeEach
	public void setup() {
		mock = new WeatherData();
		mock.setId(123);
	}

	@Test
	public void assertWeatherControllerMockService() throws Exception {
		String mockLocation = "Mock Location";
		String url = String.format(GET_WEATHER_DATA_URL, port, mockLocation);

		// Create mock data for assertion
		mock.setName(mockLocation);
		ResponseEntity<WeatherData> mockResponse = new ResponseEntity<>(mock, HttpStatus.OK);

		// Mock service method return value, is called internally by controller.
		when(restConsumer.getWeatherData(mockLocation)).thenReturn(mockResponse);

		// Mock HTTP request to the controller end-point and status code assertion
		MvcResult result = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();

		WeatherData response = objectMapper.readValue(content, WeatherData.class);

		// Assertion
		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo(123);
		assertThat(response.getName()).isEqualTo(mockLocation);
	}

}
