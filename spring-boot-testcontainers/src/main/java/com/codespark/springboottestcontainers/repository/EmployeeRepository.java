package com.codespark.springboottestcontainers.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codespark.springboottestcontainers.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public List<Employee> findByName(String name);

    public List<Employee> findByDateOfBirthGreaterThan(LocalDate date);

}
