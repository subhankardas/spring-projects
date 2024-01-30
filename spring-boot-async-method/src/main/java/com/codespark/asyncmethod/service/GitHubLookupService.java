package com.codespark.asyncmethod.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.codespark.asyncmethod.entity.GitHubUser;

@Service
public class GitHubLookupService {

    private static Logger logger = LoggerFactory.getLogger(GitHubLookupService.class);
    private final RestTemplate restTemplate;
    private static final String GITHUB_API_URL = "https://api.github.com/users/%s";

    GitHubLookupService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Async
    public CompletableFuture<GitHubUser> findUser(String username) throws InterruptedException {
        logger.info("Looking up " + username);

        String url = String.format(GITHUB_API_URL, username);
        GitHubUser results = restTemplate.getForObject(url + "", GitHubUser.class);

        Thread.sleep(1000l); // Simulate expensive operation
        return CompletableFuture.completedFuture(results);
    }

}
