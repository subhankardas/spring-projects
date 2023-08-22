package com.codespark.springbootfilebatch.batchprocessing.job;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.stereotype.Component;

@Component
public class EmployeeJobListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(EmployeeJobListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        JobParameters params = jobExecution.getJobParameters();
        log.info("Employee data migration batch job started by {}", params.getString("BY_USER"));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Employee data migration batch job completed at {}", LocalDateTime.now());
        }
    }

}
