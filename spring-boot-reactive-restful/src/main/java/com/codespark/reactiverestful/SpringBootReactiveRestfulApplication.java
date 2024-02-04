package com.codespark.reactiverestful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codespark.reactiverestful.models.Employee;
import com.codespark.reactiverestful.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class SpringBootReactiveRestfulApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootReactiveRestfulApplication.class);

	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactiveRestfulApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		employeeRepository.deleteAll();

		logger.info("Saving new employees");
		employeeRepository.save(new Employee(1, "John Doe", "Developer", "emp1@example.com"));
		employeeRepository.save(new Employee(2, "Jane Smith", "Manager", "emp2@example.com"));
		employeeRepository.save(new Employee(3, "Mike Johnson", "Finance", "emp2@example.com"));
	}

}
