package com.codespark.springguides.mongodb;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import com.codespark.springguides.mongodb.entity.Author;
import com.codespark.springguides.mongodb.entity.Tutorial;
import com.codespark.springguides.mongodb.repository.TutorialsDAOImpl;
import com.codespark.springguides.mongodb.repository.TutorialsRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@EnableMongoRepositories
public class MongoDataBootstrap implements CommandLineRunner {

	@Autowired
	private TutorialsRepository tutorialsRepository;

	@Autowired
	private TutorialsDAOImpl tutorialsDAO;

	@Override
	public void run(String... args) throws Exception {
		deleteTutorials();
		addTutorials();
		findTutorialsUsingRepository();
		findTutorialsUsingTemplate();
	}

	private void deleteTutorials() {
		log.info("Cleaning up mongo database");
		tutorialsRepository.deleteAll();
	}

	private void addTutorials() {
		log.info("Saving new tutorials");

		Tutorial tutorial1 = new Tutorial("Core Java", new Author("Durga Sir", 4.5f), Arrays.asList("Java", "JVM"));
		Tutorial tutorial2 = new Tutorial("Spring Boot", new Author("Naveen Sir", 4.2f),
				Arrays.asList("Java", "Spring"));
		Tutorial tutorial3 = new Tutorial("AWS", new Author("Pradeep Sir", 3f), Arrays.asList("DevOps", "CD"));

		tutorialsRepository.save(tutorial1);
		tutorialsRepository.save(tutorial2);
		tutorialsRepository.save(tutorial3);
	}

	private void findTutorialsUsingRepository() {
		log.info("Finding tutorials using MongoRepository");

		Tutorial tutorial = tutorialsRepository.findById("Core Java").get();
		log.info(tutorial.toString());

		tutorial = tutorialsRepository.findByAuthorName("Pradeep Sir").get();
		log.info(tutorial.toString());

		tutorial = tutorialsRepository.findByAuthorNameContaining("Naveen").get();
		log.info(tutorial.toString());

		List<Tutorial> tutorials = tutorialsRepository.findByTopicsContaining("Java");
		log.info("Tutorials searched by topic Java");
		tutorials.stream().forEach(tutorl -> {
			log.info(tutorl.toString());
		});

		tutorials = tutorialsRepository.findByAuthorRatingGreaterThan(3.5f);
		log.info("Tutorials searched by author rating greater than 3.5");
		tutorials.stream().forEach(tutorl -> {
			log.info(tutorl.toString());
		});
	}

	private void findTutorialsUsingTemplate() {
		log.info("Finding tutorials using MongoTemplate");

		List<Tutorial> tutorials = tutorialsDAO.findAll();
		log.info("List of all tutorials");
		tutorials.stream().forEach(tutorl -> {
			log.info(tutorl.toString());
		});

		Tutorial tutorial = tutorialsDAO
				.save(new Tutorial("Hibernate", new Author("Rajiv Sir", 3.1f), Arrays.asList("ORM", "Hibernate")));

		tutorial = tutorialsDAO.findById("Hibernate").get();
		log.info("Tutorial by ID");
		log.info(tutorial.toString());

		tutorial = tutorialsDAO.findByAuthorName("Pradeep Sir").get();
		log.info(tutorial.toString());

		tutorial = tutorialsDAO.findByAuthorNameContaining("Nav").get();
		log.info(tutorial.toString());

		tutorials = tutorialsDAO.findByTopicsContaining("Java");
		log.info("Tutorials searched by topic Java");
		tutorials.stream().forEach(tutorl -> {
			log.info(tutorl.toString());
		});

		tutorials = tutorialsDAO.findByAuthorRatingGreaterThan(3.5f);
		log.info("Tutorials searched by author rating greater than 3.5");
		tutorials.stream().forEach(tutorl -> {
			log.info(tutorl.toString());
		});

		boolean result = tutorialsDAO.isTutorialAuthorNameContaining("Hibernate", "Naveen Sir");
		log.info("Naveen Sir teaches Hibernate: " + result);

		result = tutorialsDAO.isTutorialAuthorNameContaining("Hibernate", "Rajiv");
		log.info("Rajiv Sir teaches Hibernate : " + result);
	}

}
