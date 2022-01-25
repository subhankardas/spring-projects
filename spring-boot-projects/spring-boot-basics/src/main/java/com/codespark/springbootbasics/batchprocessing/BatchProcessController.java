package com.codespark.springbootbasics.batchprocessing;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/batch")
public class BatchProcessController {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	/**
	 * End-point to trigger the batch job for migrating employee data from CSV file
	 * to database. Starts the batch job with some parameters i.e start time and
	 * user name for logging on completion of the job.
	 */
	@GetMapping
	public BatchStatus startBatchJob() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		// Setup some job parameters for logging
		Map<String, JobParameter> paramsMap = new HashMap<>();
		paramsMap.put("TIME", new JobParameter(new Date()));
		paramsMap.put("BY_USER", new JobParameter("Batch Admin User"));

		JobParameters params = new JobParameters(paramsMap);

		// Start batch job execution
		JobExecution jobExecution = jobLauncher.run(job, params);

		return jobExecution.getStatus();
	}

}
