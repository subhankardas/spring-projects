package com.codespark.springbootbasics.datajpa;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.codespark.springbootbasics.batchprocessing.job.Employee;

/**
 * Spring Data REST takes the features of Spring HATEOAS and Spring Data JPA and
 * automatically combines them together. This repository is an interface that
 * lets you perform various operations involving Person objects. It gets these
 * operations by extending the PagingAndSortingRepository interface that is
 * defined in Spring Data Commons.
 * 
 * At runtime, Spring Data REST automatically creates an implementation of this
 * interface. Then it uses the @RepositoryRestResource annotation to direct
 * Spring MVC to create RESTful end points at /emp.
 */
@RepositoryRestResource(collectionResourceRel = "employee", path = "emp")
public interface EmployeeDataRepository extends PagingAndSortingRepository<Employee, Integer> {

	// Adding a custom search end point
	@RestResource(rel = "departments", path = "departments")
	public List<Employee> findByDepartment(@Param("q") String department);

	// Hiding a custom search implementation from the RESTful service
	@RestResource(exported = false)
	public List<Employee> findByName(String name);

}
