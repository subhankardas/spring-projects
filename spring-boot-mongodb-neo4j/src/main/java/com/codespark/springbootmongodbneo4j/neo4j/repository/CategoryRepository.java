package com.codespark.springbootmongodbneo4j.neo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.codespark.springbootmongodbneo4j.neo4j.entity.Category;

public interface CategoryRepository extends Neo4jRepository<Category, Long> {

}