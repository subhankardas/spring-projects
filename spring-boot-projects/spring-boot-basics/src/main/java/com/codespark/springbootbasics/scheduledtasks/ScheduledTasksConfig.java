package com.codespark.springbootbasics.scheduledtasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * The @EnableScheduling annotation ensures that a background task executor is
 * created.
 */
//@Configuration
@EnableScheduling
public class ScheduledTasksConfig {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasksConfig.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	// Scheduler time periods
	private static final int DURATION = 100000;
	private static final String CRON_EXP = "0 * * * * ?";

	/**
	 * Execute the scheduled task with a fixed period in milliseconds between
	 * invocations.
	 */
	@Scheduled(fixedRate = DURATION)
	public void repeatTask() {
		log.info("T1: The time is now {}", dateFormat.format(new Date()));
	}

	/**
	 * Execute the scheduled task with a CRON expression as time period.
	 */
	@Scheduled(cron = CRON_EXP)
	public void cronTask() {
		log.info("T2: The time is now {}", dateFormat.format(new Date()));
	}

}
