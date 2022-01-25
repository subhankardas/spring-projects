package com.codespark.springbootbasics.hibernatemapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codespark.springbootbasics.hibernatemapping.entity.one_to_one_bi.RegistrationDetails;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationDetails, Integer> {

}
