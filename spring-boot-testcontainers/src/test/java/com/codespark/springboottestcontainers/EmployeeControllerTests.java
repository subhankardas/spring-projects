package com.codespark.springboottestcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import com.codespark.springboottestcontainers.entity.Employee;
import com.codespark.springboottestcontainers.repository.EmployeeRepository;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTests {

	// ----- SETUP POSTGRES TEST CONTAINER ----- //
	@LocalServerPort
	private Integer port;

	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
			"postgres:15-alpine");

	@BeforeAll
	static void beforeAll() {
		postgres.start();
	}

	@AfterAll
	static void afterAll() {
		postgres.stop();
	}

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
	}

	// ----- SETUP TEST DEPENDENCIES & STEPS ----- //
	@Autowired
	private EmployeeRepository employeeRepository;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost:" + port;
		employeeRepository.deleteAll();
	}

	@Test
	void shouldGetAllEmployees() {
		// Setup
		List<Employee> employees = List.of(
				new Employee(1l, "John", "hKb1w@example.com", LocalDate.of(2099, 10, 12)),
				new Employee(2l, "Jane", "2hKb1w@example.com", LocalDate.of(2000, 5, 25)));
		employeeRepository.saveAll(employees);

		RestAssured.given() // With JSON content type
				.contentType(ContentType.JSON)
				.when() // The GET all employees API is called
				.get("/api/employees")
				.then() // The response should be OK with 2 employees
				.statusCode(HttpStatus.OK.value())
				.body(".", org.hamcrest.Matchers.hasSize(2));
	}

	@Test
	void testCRUDOperations() {
		// Given a new employee
		employeeRepository.save(new Employee(1l, "John", "hKb1w@example.com", LocalDate.of(2999, 10, 12)));

		// When the employee is retrieved
		List<Employee> employees = employeeRepository.findByDateOfBirthGreaterThan(LocalDate.now());

		// Then the employee should have valid data
		assertEquals(1, employees.size());
		assertEquals("John", employees.get(0).getName());
		assertEquals("hKb1w@example.com", employees.get(0).getEmail());
	}

}
