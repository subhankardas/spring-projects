package com.codespark.springbootrabbitmqrediskafka.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.codespark.springbootrabbitmqrediskafka.redis.as_cache.UserService;
import com.codespark.springbootrabbitmqrediskafka.redis.as_datastore.Account;
import com.codespark.springbootrabbitmqrediskafka.redis.as_datastore.AccountRepository;
import com.codespark.springbootrabbitmqrediskafka.redis.as_messaging_queue.RedisMessagePublisher;

@Component
public class RedisBootstrap implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(RedisBootstrap.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RedisMessagePublisher messagePublisher;

    @Override
    public void run(String... args) throws Exception {
        /* 1. Using redis as distributed cache */
        userService.findById("1"); // Fetch from DB
        userService.findById("1"); // Fetch from cache

        userService.findById("2"); // Fetch from DB

        /* 2. Using redis as in-memory NoSQL database */
        accountRepository.save(new Account(1, "John", 1000.0));
        Account account = accountRepository.findById(1).get();
        log.info("Get account by ID=1: {}", account);
        log.info("Data store has {} accounts", accountRepository.count());

        /* 3. Using redis as messaging queue */
        messagePublisher.publish(new Account(1, "John", 1000.0));
    }

}
