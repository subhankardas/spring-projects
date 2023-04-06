package com.codespark.springbootresthateoas.mappings.one_to_many_bi;

import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<Manager, Integer> {

}
