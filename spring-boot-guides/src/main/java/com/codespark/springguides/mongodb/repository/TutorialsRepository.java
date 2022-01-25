package com.codespark.springguides.mongodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.codespark.springguides.mongodb.entity.Tutorial;

@Repository
public interface TutorialsRepository extends MongoRepository<Tutorial, String> {

	public Optional<Tutorial> findByAuthorName(String name);

	public Optional<Tutorial> findByAuthorNameContaining(String query);

	public List<Tutorial> findByTopicsContaining(String topic);

	public List<Tutorial> findByAuthorRatingGreaterThan(float rating);

}
