package com.codespark.reactiverestful.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.codespark.reactiverestful.models.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, Integer> {

}
