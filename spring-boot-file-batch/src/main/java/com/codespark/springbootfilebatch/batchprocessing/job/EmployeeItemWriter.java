package com.codespark.springbootfilebatch.batchprocessing.job;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codespark.springbootfilebatch.batchprocessing.Employee;
import com.codespark.springbootfilebatch.batchprocessing.EmployeeRepository;

@Component
public class EmployeeItemWriter implements ItemWriter<Employee> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void write(Chunk<? extends Employee> employees) throws Exception {
        employeeRepository.saveAll(employees);
    }

}
