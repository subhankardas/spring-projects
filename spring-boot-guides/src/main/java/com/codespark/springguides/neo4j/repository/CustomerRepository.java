package com.codespark.springguides.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.codespark.springguides.neo4j.entity.Customer;

public interface CustomerRepository extends Neo4jRepository<Customer, Long> {

	Customer findByName(String name);

	List<Customer> findByWishlistName(String productName);

	List<Customer> findByWishlistCategoryName(String productName);

}