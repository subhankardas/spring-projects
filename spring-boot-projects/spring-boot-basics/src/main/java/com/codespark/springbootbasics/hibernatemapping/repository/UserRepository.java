package com.codespark.springbootbasics.hibernatemapping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codespark.springbootbasics.hibernatemapping.entity.one_to_one_uni.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByEmail(String email);

}
