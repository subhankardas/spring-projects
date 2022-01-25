package com.codespark.springbootbasics.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codespark.springbootbasics.restfulweb.SimpleRestController;

/**
 * 1. Simple sanity check test that will fail if the application context cannot
 * start. The @SpringBootTest annotation tells Spring Boot to look for a main
 * configuration class (one with @SpringBootApplication, for instance) and use
 * that to start a Spring application context.
 */
@SpringBootTest
class SmokeTest {

	@Autowired
	private SimpleRestController simpleRestController;

	@Test
	void sanityCheckOnContextLoad() {
		assertThat(simpleRestController).isNotNull();
	}

}
