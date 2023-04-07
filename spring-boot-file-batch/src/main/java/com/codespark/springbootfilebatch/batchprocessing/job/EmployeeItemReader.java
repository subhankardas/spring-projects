package com.codespark.springbootfilebatch.batchprocessing.job;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.codespark.springbootfilebatch.batchprocessing.Employee;

@Component
public class EmployeeItemReader implements ItemReader<Employee> {

    public FlatFileItemReader<Employee> employeeFileItemReader;

    public EmployeeItemReader(FlatFileItemReader<Employee> employeeFileItemReader) {
        // Open the CSV file to read the data
        this.employeeFileItemReader = employeeFileItemReader;
        this.employeeFileItemReader.open(new ExecutionContext());
    }

    @Override
    @Nullable
    public Employee read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        // Read the next record from the CSV file, and return it as item Employee
        Employee employee = employeeFileItemReader.read();
        return employee;
    }

}
