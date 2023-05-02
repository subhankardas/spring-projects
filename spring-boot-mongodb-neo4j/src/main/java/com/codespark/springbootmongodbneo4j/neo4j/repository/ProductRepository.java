package com.codespark.springbootmongodbneo4j.neo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.codespark.springbootmongodbneo4j.neo4j.entity.Product;

public interface ProductRepository extends Neo4jRepository<Product, Long> {

}