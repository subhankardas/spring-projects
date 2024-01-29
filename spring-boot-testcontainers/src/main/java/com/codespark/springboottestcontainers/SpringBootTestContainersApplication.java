package com.codespark.springboottestcontainers;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codespark.springboottestcontainers.entity.Employee;
import com.codespark.springboottestcontainers.repository.EmployeeRepository;

@SpringBootApplication
public class SpringBootTestContainersApplication implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;

	private static Logger logger = LoggerFactory.getLogger(SpringBootTestContainersApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestContainersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		employeeRepository.deleteAll();

		logger.info("Saving employee John");
		employeeRepository.save(new Employee(1l, "John", "hKb1w@example.com", LocalDate.of(2099, 10, 12)));

		List<Employee> employees = employeeRepository.findByDateOfBirthGreaterThan(LocalDate.now());
		for (Employee employee : employees) {
			logger.info(employee.toString());
		}
	}

}
