package com.codespark.reactiverestful.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.codespark.reactiverestful.models.Employee;

public interface EmployeeReactiveRepository extends ReactiveCrudRepository<Employee, Integer> {

}
