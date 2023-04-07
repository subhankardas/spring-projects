package com.codespark.springbootfilebatch.batchprocessing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public Employee findByName(String name);

}