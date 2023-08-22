package com.codespark.springbootrabbitmqrediskafka.redis.as_datastore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class AccountRepository implements CrudRepository<Account, Integer> {

    private final String CACHE_KEY = "ACCOUNT_CACHE";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, Integer, Account> hashOperations;

    @PostConstruct
    private void intializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public <S extends Account> S save(S entity) {
        hashOperations.put(CACHE_KEY, entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends Account> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(entity -> {
            hashOperations.put(CACHE_KEY, entity.getId(), entity);
        });
        return entities;
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return Optional.ofNullable((Account) hashOperations.get(CACHE_KEY, id));
    }

    @Override
    public boolean existsById(Integer id) {
        return hashOperations.get(CACHE_KEY, id) == null ? false : true;
    }

    @Override
    public Iterable<Account> findAll() {
        return hashOperations.entries(CACHE_KEY).values().stream().collect(Collectors.toList());
    }

    @Override
    public Iterable<Account> findAllById(Iterable<Integer> ids) {
        List<Integer> list = new ArrayList<>();
        ids.iterator().forEachRemaining(list::add);
        hashOperations.entries(CACHE_KEY).values().stream()
                .filter(entity -> list.contains(entity.getId()))
                .collect(Collectors.toList());
        return null;
    }

    @Override
    public long count() {
        return hashOperations.size(CACHE_KEY);
    }

    @Override
    public void deleteById(Integer id) {
        hashOperations.delete(CACHE_KEY, id);
    }

    @Override
    public void delete(Account entity) {
        hashOperations.delete(CACHE_KEY, entity.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends Account> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
        throw new UnsupportedOperationException();
    }

}