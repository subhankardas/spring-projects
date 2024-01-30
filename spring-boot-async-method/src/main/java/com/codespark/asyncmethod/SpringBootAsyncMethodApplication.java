package com.codespark.asyncmethod;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.codespark.asyncmethod.entity.GitHubUser;
import com.codespark.asyncmethod.service.GitHubLookupService;

@SpringBootApplication
@EnableAsync
public class SpringBootAsyncMethodApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(SpringBootAsyncMethodApplication.class);

	@Autowired
	private GitHubLookupService gitHubLookupService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAsyncMethodApplication.class, args);
	}

	// Configure thread pool task executor for async method execution.
	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("GithubLookup-");
		executor.initialize();
		return executor;
	}

	@Override
	public void run(String... args) throws Exception {
		long startTime = System.currentTimeMillis();

		CompletableFuture<GitHubUser> user1 = gitHubLookupService.findUser("subhankardas");
		CompletableFuture<GitHubUser> user2 = gitHubLookupService.findUser("lkumarjain");
		CompletableFuture<GitHubUser> user3 = gitHubLookupService.findUser("arpanpathak");
		CompletableFuture<GitHubUser> user4 = gitHubLookupService.findUser("ashishroy077");

		// Wait for all of the futures to complete
		CompletableFuture.allOf(user1, user2, user3, user4).join();

		long duration = System.currentTimeMillis() - startTime;
		logger.info(String.format("Total execution time: %s ms", duration));

		logger.info("Fetched github data for user: " + user1.get());
		logger.info("Fetched github data for user: " + user2.get());
		logger.info("Fetched github data for user: " + user3.get());
		logger.info("Fetched github data for user: " + user4.get());
	}

}
