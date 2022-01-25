package com.codespark.springbootbasics.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codespark.springbootbasics.batchprocessing.job.Employee;
import com.codespark.springbootbasics.datajpa.EmployeeDataRepository;

@SpringBootTest
public class EmployeeDataRepositoryUnitTest {

	@Autowired
	private EmployeeDataRepository employeeDataRepository;

	@BeforeEach
	public void setup() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(1, "Ayan", "Devloper", 12000, new Date()));
		employees.add(new Employee(2, "Deepak", "Management", 34000, new Date()));
		employees.add(new Employee(3, "Mohit", "DevOps", 23000, new Date()));

		employeeDataRepository.saveAll(employees);
	}

	@Test
	public void testFindByDepartment() {
		// Action to fetch records from database
		List<Employee> employees1 = (List<Employee>) employeeDataRepository.findByDepartment("Devloper");
		List<Employee> employees2 = (List<Employee>) employeeDataRepository.findByDepartment("DevOps");

		Employee employee1 = employees1.get(0);
		Employee employee2 = employees2.get(0);

		// Assert that records fetched have correct values
		assertThat(employee1.getId()).isEqualTo(1);
		assertThat(employee1.getName()).isEqualTo("Ayan");
		assertThat(employee1.getDepartment()).isEqualTo("Devloper");
		assertThat(employee1.getSalary()).isEqualTo(12000);

		assertThat(employee2.getId()).isEqualTo(3);
		assertThat(employee2.getName()).isEqualTo("Mohit");
		assertThat(employee2.getDepartment()).isEqualTo("DevOps");
		assertThat(employee2.getSalary()).isEqualTo(23000);
	}

}
