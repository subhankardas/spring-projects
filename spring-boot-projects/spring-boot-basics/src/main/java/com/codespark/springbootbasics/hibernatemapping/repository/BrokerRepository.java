package com.codespark.springbootbasics.hibernatemapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codespark.springbootbasics.hibernatemapping.entity.one_to_many_uni.Broker;

public interface BrokerRepository extends JpaRepository<Broker, Integer> {

}
