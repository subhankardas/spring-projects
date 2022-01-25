package com.codespark.springbootbasics.hibernatemapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codespark.springbootbasics.hibernatemapping.entity.many_to_may_uni.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

}
