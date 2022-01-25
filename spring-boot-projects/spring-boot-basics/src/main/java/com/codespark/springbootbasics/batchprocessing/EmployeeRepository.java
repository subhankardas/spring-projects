package com.codespark.springbootbasics.batchprocessing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codespark.springbootbasics.batchprocessing.job.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
