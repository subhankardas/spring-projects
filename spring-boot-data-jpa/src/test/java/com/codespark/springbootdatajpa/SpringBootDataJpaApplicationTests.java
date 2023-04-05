package com.codespark.springbootdatajpa;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codespark.springbootdatajpa.datajpa.UserRepository;
import com.codespark.springbootdatajpa.datajpa.UserService;

@SpringBootTest
class SpringBootDataJpaApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Test
	void smokeTestWithContextLoads() {
		assertNotNull(userRepository);
		assertNotNull(userService);
	}

}
