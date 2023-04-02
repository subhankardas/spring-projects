package com.codespark.springbootrestfulweb;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codespark.springbootrestfulweb.restconsumer.WeatherController;
import com.codespark.springbootrestfulweb.restservice.UserController;

@SpringBootTest
class SpringBootRestfulWebApplicationTests {

	@Autowired
	private UserController userController;

	@Autowired
	private WeatherController weatherController;

	/**
	 * 1. Simple sanity check test that will fail if the application context cannot
	 * start. The '@SpringBootTest' annotation tells Spring Boot to look for a main
	 * configuration class (one with '@SpringBootApplication', for instance) and use
	 * that to start a Spring application context.
	 */
	@Test
	void smokeTestsOnContextLoads() {
		assertNotNull(userController);
		assertNotNull(weatherController);
	}

}
