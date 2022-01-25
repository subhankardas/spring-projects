package com.codespark.springguides.mongodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.codespark.springguides.mongodb.entity.Tutorial;

@Repository
public class TutorialsDAOImpl {

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Tutorial> findAll() {
		return mongoTemplate.findAll(Tutorial.class);
	}

	public Optional<Tutorial> findById(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		return Optional.ofNullable(mongoTemplate.findOne(query, Tutorial.class));
	}

	public Tutorial save(Tutorial tutorial) {
		mongoTemplate.save(tutorial);
		return tutorial;
	}

	public Optional<Tutorial> findByAuthorName(String name) {
		String key = "name";

		Query query = new Query();
		query.fields().include("author");
		query.fields().include("topics");
		query.addCriteria(Criteria.where("author." + key).is(name));

		Tutorial tutorial = mongoTemplate.findOne(query, Tutorial.class);
		return Optional.ofNullable(tutorial);
	}

	public Optional<Tutorial> findByAuthorNameContaining(String name) {
		String key = "name";

		Query query = new Query();
		query.fields().include("author");
		query.fields().include("topics");
		query.addCriteria(Criteria.where("author." + key).regex(name));

		Tutorial tutorial = mongoTemplate.findOne(query, Tutorial.class);
		return Optional.ofNullable(tutorial);
	}

	public List<Tutorial> findByTopicsContaining(String topic) {
		Query query = new Query();
		// query.fields().include("author"); // Author not to be included i.e. = null
		query.fields().include("topics");
		query.addCriteria(Criteria.where("topics").in(topic));

		List<Tutorial> tutorials = mongoTemplate.find(query, Tutorial.class);
		return tutorials;
	}

	public List<Tutorial> findByAuthorRatingGreaterThan(float rating) {
		Query query = new Query();
		query.fields().include("author");
		query.addCriteria(Criteria.where("author.rating").gt(rating));

		List<Tutorial> tutorials = mongoTemplate.find(query, Tutorial.class);
		return tutorials;
	}

	public boolean isTutorialAuthorNameContaining(String id, String name) {
		Query query = new Query();
		query.fields().include("author");
		query.fields().include("topics");
		query.addCriteria(Criteria.where("id").is(id).andOperator(Criteria.where("author.name").regex(name)));

		Tutorial tutorial = mongoTemplate.findOne(query, Tutorial.class);
		return tutorial != null ? true : false;
	}

}