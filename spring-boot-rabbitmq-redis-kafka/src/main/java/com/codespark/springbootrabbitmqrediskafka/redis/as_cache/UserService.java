package com.codespark.springbootrabbitmqrediskafka.redis.as_cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    /*
     * Cache the method response with key using combination of passed arguments.
     * For first time since cached response is not available, it is fetched from DB.
     * For subsequent calls, it is fetched from cache.
     */
    @Cacheable("USER_CACHE")
    public User findById(String id) {
        User user = userRepository.findById(id);
        log.info("Fetched user from DB: {}", user);
        return user;
    }

    /* Clears cache after 10 minutes. */
    @CacheEvict(allEntries = true, cacheNames = { "USER_CACHE" })
    @Scheduled(fixedDelay = 600000)
    public void cacheEvict() {
        log.info("Cleaning cache USER_CACHE");
    }

}
