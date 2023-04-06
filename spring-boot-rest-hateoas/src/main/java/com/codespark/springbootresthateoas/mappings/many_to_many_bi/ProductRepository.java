package com.codespark.springbootresthateoas.mappings.many_to_many_bi;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
