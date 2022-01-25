package com.codespark.springbootbasics.redisdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.codespark.springbootbasics.redisdata.cache.DataService;
import com.codespark.springbootbasics.redisdata.data.Report;
import com.codespark.springbootbasics.redisdata.data.ReportRepository;
import com.codespark.springbootbasics.redisdata.messagequeue.RedisMessagePublisher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RedisBootstrap implements CommandLineRunner {

	@Autowired
	private RedisMessagePublisher redisPublisher;

	@Autowired
	private ReportRepository reportRepository;

	@Autowired
	private DataService dataService;

	@Override
	public void run(String... args) throws Exception {
		// 1. Use Redis as a messaging queue
		redisAsMessageQueue();

		// 2. Use Redis as in-memory NoSQL database
		redisAsDatabase();

		// 3. Use Redis as a distributed cache
		redisAsCache();
	}

	private void redisAsMessageQueue() {
		Object report = (Object) new Report("Sales", "Waston", 34700.34);
		redisPublisher.publish(report);
	}

	private void redisAsDatabase() {
		// Using custom Redis hash CRUD implementations
		reportRepository.save(new Report("Sales", "Azim", 120000.12));
		reportRepository.save(new Report("Revenue", "Ratan", 376000.45));
		reportRepository.save(new Report("Loss", "Parker", 78400.87));
		reportRepository.save(new Report("Market", "Jenny", 8500.6));

		log.info("Finished database setup for reports");
		log.info("Getting reports from database");

		// Reading data from both types of managed hashes
		reportRepository.findAll().forEach(report -> {
			log.info(report.toString());
		});
	}

	private void redisAsCache() throws Exception {
		Report report = null;

		// For the first time data is read from actual db, hence log is visible
		report = dataService.findById("Sales");
		log.info("[O] Report: " + report);

		report = dataService.findById("Revenue");
		log.info("[O] Report: " + report);

		// For second time and later, data is fetched from the cache
		report = dataService.findById("Sales");
		log.info("[O] Report: " + report);
	}

}
