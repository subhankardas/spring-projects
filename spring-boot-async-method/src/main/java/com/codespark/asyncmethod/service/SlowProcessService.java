package com.codespark.asyncmethod.service;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * In this tutorial, we have shown you how to use async processing in a Spring
 * Boot application. We started by creating a simple slow operation and then
 * showed you how to make it async using the @Async annotation.
 */
@Service
public class SlowProcessService {

    private static final Logger logger = LoggerFactory.getLogger(SlowProcessService.class);

    // This slow process will be executed in the same thread as the HTTP request,
    // hence will block the request from being processed.
    public void process() {
        long startTime = System.currentTimeMillis();
        try {
            // Simulate slow process
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(String.format("Slow process execution time: %s ms and current thread: %s",
                System.currentTimeMillis() - startTime, Thread.currentThread().getName()));
    }

    // ------ MULTIPLE ASYNC EXECUTORS ------ //
    // Useful if we want to use multiple thread pools for async different tasks.
    @Bean("process1Executor")
    public Executor process1Executor() {
        return Executors.newFixedThreadPool(100);
    }

    @Bean("process2Executor")
    public Executor process2Executor() {
        return Executors.newFixedThreadPool(10);
    }

    // This is an asynchronous method, processed in a separate thread from the
    // executor pool. Even thought it will have same execution time as the above
    // slow process but it will not block the request. Here we are also using a
    // custom configured thread pool.
    @Async(value = "process1Executor")
    public void processAsync() {
        long startTime = System.currentTimeMillis();
        try {
            // Simulate slow process
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(String.format("Async slow process execution time: %s ms and current thread: %s",
                System.currentTimeMillis() - startTime, Thread.currentThread().getName()));
    }

}
