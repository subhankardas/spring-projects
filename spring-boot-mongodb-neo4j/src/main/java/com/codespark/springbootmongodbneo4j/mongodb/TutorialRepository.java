package com.codespark.springbootmongodbneo4j.mongodb;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TutorialRepository extends MongoRepository<Tutorial, Long> {

    public Optional<Tutorial> findByAuthorName(String name);

    public Optional<Tutorial> findByAuthorNameContaining(String query);

    public List<Tutorial> findByTopicsContaining(String topic);

    public List<Tutorial> findByAuthorRatingGreaterThan(float rating);

}
