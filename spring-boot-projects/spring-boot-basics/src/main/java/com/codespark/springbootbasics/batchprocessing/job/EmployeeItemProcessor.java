package com.codespark.springbootbasics.batchprocessing.job;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Transforms an object into another object according to business logic. These
 * objects can be of similar types or differ as per requirement. Here we take a
 * raw employee object with some missing and some raw field values and parse it
 * into a concrete employee object ready to store in database.
 */
@Component("employeeItemProcessor")
public class EmployeeItemProcessor implements ItemProcessor<Employee, Employee> {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeItemProcessor.class);

	// Data needed for processing the employee data
	private static final Map<String, String> DEPARTMENTS = new HashMap<>();

	public EmployeeItemProcessor() {
		// Initialize the department names
		DEPARTMENTS.put("011", "Android");
		DEPARTMENTS.put("012", "Web");
		DEPARTMENTS.put("013", "Java");
		DEPARTMENTS.put("014", "Python");
	}

	@Override
	public Employee process(Employee employee) throws Exception {
		/*
		 * Processing the raw data item with required values. Replacing department codes
		 * by names and setting current date for database record.
		 */
		employee.setDepartment(DEPARTMENTS.get(employee.getDepartment()));
		employee.setDate(new Date());

		logger.info("Processed Item: {}", employee);
		return employee;
	}

}
