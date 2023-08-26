package com.codespark.springsecuritybasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import com.codespark.springsecuritybasic.utils.JwtUtils;

@SpringBootTest
@AutoConfigureMockMvc
class JwtUtilsTests {

	private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsIm5hbWUiOiJKb2h"
			+ "uIERvZSIsImlhdCI6MTUxNjIzOTAyMn0.EEPAF1aOGx7EK8gNs6cogwt658zrmnjeDitrr4ecvrY";

	@Autowired
	private JwtUtils jwtUtil;

	@Autowired
	private MockHttpServletRequest request;

	@Test
	void contextLoads() {
		assertNotNull(jwtUtil);
	}

	@Test
	void testGetTokenPresent() {
		request.addHeader("Authorization", "Bearer TK123");
		assertEquals(jwtUtil.getToken(request), "TK123");
	}

	@Test
	void testGetTokenBasicPresent() {
		request.addHeader("Authorization", "Basic TK123");
		assertNull(jwtUtil.getToken(request));
	}

	@Test
	void testGetTokenNotPresent() {
		assertNull(jwtUtil.getToken(request));
	}

	@Test
	void testGetTokenInvalidPresent() {
		request.addHeader("Authorization", "TK123");
		assertNull(jwtUtil.getToken(request));
	}

	@Test
	void testValidateTokenSuccess() {
		assertTrue(jwtUtil.validateToken(VALID_TOKEN));
	}

	@Test
	void testValidateTokenInvalid() {
		assertFalse(jwtUtil.validateToken("TK123"));
	}

	@Test
	void testGetUsernameSuccess() {
		assertEquals(jwtUtil.getUsername(VALID_TOKEN), "admin");
	}

}
