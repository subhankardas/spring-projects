package com.codespark.springbootfilebatch.batchprocessing;

import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EmployeeJobLauncher implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(EmployeeJobLauncher.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job employeeBatchJob;

    @Override
    public void run(String... args) throws Exception {
        BatchStatus status = startBatchJob();
        log.info("Batch job status: {} at {}", status, LocalDateTime.now());
    }

    public BatchStatus startBatchJob() throws JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        // Setup some job parameters for logging
        JobParameters params = new JobParametersBuilder()
                .addDate("TIME", new Date(), true)
                .addString("BY_USER", "John Doe", true)
                .toJobParameters();

        // Start batch job execution
        JobExecution jobExecution = jobLauncher.run(employeeBatchJob, params);

        return jobExecution.getStatus();
    }

}
