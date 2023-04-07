package com.codespark.springbootfilebatch.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codespark.springbootfilebatch.batchprocessing.Employee;
import com.codespark.springbootfilebatch.batchprocessing.EmployeeRepository;

@SpringBootTest
@SpringBatchTest
public class EmployeeBatchJobTests {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setup() {
        employeeRepository.deleteAll();
    }

    @AfterEach
    public void cleanup() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    public void testEmployeeBatchJob() throws Exception {
        // Given the job is launchedEMPLOYEE
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        // Then assert the job status is COMPLETED
        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

        // Assert that employee details are present
        Employee employee = employeeRepository.findByName("Jim Thomas");
        assertNotNull(employee);
        assertEquals("Jim Thomas", employee.getName());
        assertEquals(120000, employee.getSalary());
        assertEquals("Sales", employee.getDepartment());
    }

}
