package com.codespark.springbootbasics.hibernatemapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codespark.springbootbasics.hibernatemapping.entity.one_to_many_bi.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {

}
