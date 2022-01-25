package com.codespark.springbootbasics.redisdata.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.codespark.springbootbasics.redisdata.data.Report;
import com.codespark.springbootbasics.redisdata.data.ReportRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataService {

	private static final String CACHE_NAME = "CACHE_REPORT_BY_ID";
	private static final int CACHE_EXPIRE_TIME = 20000;

	@Autowired
	private ReportRepository reportRepository;

	/**
	 * Clears out specified caches after desired time intervals.
	 */
	@CacheEvict(allEntries = true, cacheNames = { CACHE_NAME })
	@Scheduled(fixedDelay = CACHE_EXPIRE_TIME)
	public void cacheEvict() {
		log.info("[X] Clearing cached data");
	}

	/**
	 * Caches response for the method with key derived from combination of
	 * respective passed arguments. If cached response is not available, then
	 * proceeds to execute logic, fetches data and stores into cache. For the second
	 * time user sees cached data until it is present.
	 */
	@Cacheable(CACHE_NAME)
	public Report findById(String id) throws Exception {
		// This log should be only visible when no cache is present
		log.info("Reading " + id.toLowerCase() + " report data from db");

		// Fetch response from actual resource
		return reportRepository.findById(id).orElseThrow(() -> {
			return new Exception("Report not found.");
		});
	}

}
