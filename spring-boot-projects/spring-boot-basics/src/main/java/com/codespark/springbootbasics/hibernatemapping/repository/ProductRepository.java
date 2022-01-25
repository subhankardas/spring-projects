package com.codespark.springbootbasics.hibernatemapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codespark.springbootbasics.hibernatemapping.entity.many_to_many_bi.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
