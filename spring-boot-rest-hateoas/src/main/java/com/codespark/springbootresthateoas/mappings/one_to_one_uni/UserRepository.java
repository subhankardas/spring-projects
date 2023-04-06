package com.codespark.springbootresthateoas.mappings.one_to_one_uni;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
