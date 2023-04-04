package com.codespark.springbootdatajpa.unittests;

import static org.awaitility.Awaitility.await;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codespark.springbootdatajpa.scheduledtasks.ScheduledTasksConfig;

@SpringBootTest
public class ScheduledTasksTests {

    @Autowired
    private ScheduledTasksConfig scheduledTasksConfig;

    @Test
    public void testRepeatTaskAtFixedInterval() {
        // Waits for 2 minutes, until asserted that task is executed 2 times.
        await().atMost(2, TimeUnit.MINUTES).untilAsserted(() -> {
            assert scheduledTasksConfig.task1ExecutedTimes >= 2;
        });
    }

}
