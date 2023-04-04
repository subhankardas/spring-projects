package com.codespark.springbootdatajpa.scheduledtasks;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * The '@EnableScheduling' annotation ensures that a background task executor is
 * created.
 */
@Configuration
@EnableScheduling
public class ScheduledTasksConfig {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasksConfig.class);

    public int task1ExecutedTimes;

    @Scheduled(fixedRate = 120000) // Every 2 minutes
    public void repeatTaskAtFixedInterval() {
        log.info("Running scheduled task at {}", LocalDateTime.now());
        task1ExecutedTimes++;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Every day at 00:00:00 AM
    public void repeatTaskAtSpecificTimeWithCron() {
        log.info("Running scheduled cron task at {}", LocalDateTime.now());
    }

}
