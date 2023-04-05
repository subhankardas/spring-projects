package com.codespark.springbootdatajpa.datajpa;

import org.springframework.data.repository.CrudRepository;

/**
 * Data access layer implementation for User entities. CrudRepository is a
 * Spring Data JPA implementation for CRUD operations on User entities. We also
 * added a method findByEmail() i.e. a named query on the email column.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

}
