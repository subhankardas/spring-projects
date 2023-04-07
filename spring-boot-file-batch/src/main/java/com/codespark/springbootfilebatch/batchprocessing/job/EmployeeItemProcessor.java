package com.codespark.springbootfilebatch.batchprocessing.job;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.codespark.springbootfilebatch.batchprocessing.Employee;

@Component
public class EmployeeItemProcessor implements ItemProcessor<Employee, Employee> {

    private static final Logger log = LoggerFactory.getLogger(EmployeeItemProcessor.class);
    private static final Map<String, String> DEPARTMENTS = new HashMap<>() {
        {
            put("011", "R&D");
            put("012", "Sales");
            put("013", "Finance");
            put("014", "Marketing");
        }
    };

    @Override
    @Nullable
    public Employee process(@NonNull Employee employee) throws Exception {
        // Processing the raw data item with required values
        employee.setDepartment(DEPARTMENTS.get(employee.getDepartment()));
        employee.setDate(LocalDate.now());

        log.info("Processed employee data: {} at {}", employee, LocalDateTime.now());
        return employee;
    }

}
