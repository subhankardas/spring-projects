package com.codespark.springbootbasics.batchprocessing.job;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.stereotype.Component;

/**
 * Reads each data line one by one multiple times during batch execution and
 * parses the data into a raw employee object and returns it to be processed. We
 * can use other data sources such as a list of employees where we can read each
 * individual employee at a time and return.
 */
@Component("employeeItemReader")
public class EmployeeItemReader implements ItemReader<Employee> {

	public FlatFileItemReader<Employee> flatFileItemReader;

	public EmployeeItemReader(FlatFileItemReader<Employee> flatFileItemReader) {
		// Open the CSV file to read
		this.flatFileItemReader = flatFileItemReader;
		this.flatFileItemReader.open(new ExecutionContext());
	}

	@Override
	public Employee read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		Employee employee = flatFileItemReader.read();
		return employee;
	}

}
