package com.codespark.docker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codespark.docker.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
