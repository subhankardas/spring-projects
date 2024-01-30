package com.codespark.asyncmethod.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codespark.asyncmethod.service.SlowProcessService;

@RestController
public class SlowProcessController {

    private static final Logger logger = LoggerFactory.getLogger(SlowProcessController.class);

    @Autowired
    private SlowProcessService slowProcessService;

    @GetMapping("/slow") // Response time will be always be greater than 1000 ms
    public void slowProcess() {
        long startTime = System.currentTimeMillis();
        slowProcessService.process();
        long endTime = System.currentTimeMillis();
        logger.info(String.format("API call execution time: %s ms and current thread: %s", endTime - startTime,
                Thread.currentThread().getName()));
    }

    @GetMapping("/async-slow") // Response time will be always be less, not blocked by the slow process
    public void slowProcessAsync() {
        long startTime = System.currentTimeMillis();
        slowProcessService.processAsync();
        long endTime = System.currentTimeMillis();
        logger.info(String.format("API call execution time: %s ms and current thread: %s", endTime - startTime,
                Thread.currentThread().getName()));
    }

}
