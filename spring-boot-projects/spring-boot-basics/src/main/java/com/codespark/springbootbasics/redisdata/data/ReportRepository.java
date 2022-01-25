package com.codespark.springbootbasics.redisdata.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Custom implementation of hash operations for performing CRUD operations on a
 * Redis hash. Here we have implemented CrudRepository methods but for Redis
 * hashes. Hash key is derived as appended string i.e. hash + CACHE_KEY.
 */
@Service
public class ReportRepository implements CrudRepository<Report, String> {

	private final String CACHE_KEY = "REPORT";

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	private HashOperations<String, String, Report> hashOperations;

	@PostConstruct
	private void intializeHashOperations() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public <S extends Report> S save(S entity) {
		hashOperations.put(CACHE_KEY, entity.getId(), entity);
		return entity;
	}

	@Override
	public <S extends Report> Iterable<S> saveAll(Iterable<S> entities) {
		entities.forEach(entity -> {
			hashOperations.put(CACHE_KEY, entity.getId(), entity);
		});
		return entities;
	}

	@Override
	public Optional<Report> findById(String id) {
		return Optional.ofNullable((Report) hashOperations.get(CACHE_KEY, id));
	}

	@Override
	public boolean existsById(String id) {
		return hashOperations.get(CACHE_KEY, id) == null ? false : true;
	}

	@Override
	public Iterable<Report> findAll() {
		return hashOperations.entries(CACHE_KEY).values().stream().collect(Collectors.toList());
	}

	@Override
	public Iterable<Report> findAllById(Iterable<String> ids) {
		List<String> list = new ArrayList<>();
		ids.iterator().forEachRemaining(list::add);
		hashOperations.entries(CACHE_KEY).values().stream().filter(ent -> list.contains(ent.getId()))
				.collect(Collectors.toList());
		return null;
	}

	@Override
	public long count() {
		return hashOperations.size(CACHE_KEY);
	}

	@Override
	public void deleteById(String id) {
		hashOperations.delete(CACHE_KEY, id);
	}

	@Override
	public void delete(Report entity) {
		hashOperations.delete(CACHE_KEY, entity.getId());
	}

	@Override
	public void deleteAll(Iterable<? extends Report> entities) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAll() {
		throw new UnsupportedOperationException();
	}

}
