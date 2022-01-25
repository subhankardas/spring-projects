package com.codespark.springbootbasics.hibernatemapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codespark.springbootbasics.hibernatemapping.entity.one_to_many_bi.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

}
