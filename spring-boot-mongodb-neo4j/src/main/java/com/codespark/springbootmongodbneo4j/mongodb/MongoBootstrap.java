package com.codespark.springbootmongodbneo4j.mongodb;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MongoBootstrap implements CommandLineRunner {

    private Logger log = LoggerFactory.getLogger(MongoBootstrap.class);

    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private TutorialsDAO tutorialsDAO;

    @Override
    public void run(String... args) throws Exception {
        saveTutorials();
        findByAuthorName();
        findByTopicAndRating();

        saveTutorialsDAO();
        findTutorialsDAO();
    }

    private void saveTutorials() {
        tutorialRepository.save(new Tutorial(1, Arrays.asList("Java", "Spring"), new Author("John", 4.2f)));
        tutorialRepository.save(new Tutorial(2, Arrays.asList("Spring", "Web"), new Author("Mary", 3.1f)));
        tutorialRepository.save(new Tutorial(3, Arrays.asList("React", "UI"), new Author("Jane", 4f)));
    }

    private void findByAuthorName() {
        log.info("Find by author name Mary: {}", tutorialRepository.findByAuthorName("Mary"));
        log.info("Find by author name containing Jo: {}", tutorialRepository.findByAuthorNameContaining("Jo").get());
    }

    private void findByTopicAndRating() {
        log.info("Find by topics Spring: {}", tutorialRepository.findByTopicsContaining("Spring"));
        log.info("Find tutorials with rating more than 4: {}", tutorialRepository.findByAuthorRatingGreaterThan(4));
    }

    private void saveTutorialsDAO() {
        tutorialsDAO.save(new Tutorial(4, Arrays.asList("Angular", "UI"), new Author("Max", 3.5f)));
        tutorialsDAO.save(new Tutorial(5, Arrays.asList("UX", "Figma"), new Author("Oliver", 4.6f)));
    }

    private void findTutorialsDAO() {
        log.info("Find tutorial by ID 3: {}", tutorialsDAO.findById(3));
        log.info("Find by author Oliver: {}", tutorialsDAO.findByAuthorName("Oliver"));
        log.info("Find by author name containing Ma: {}", tutorialsDAO.findByAuthorNameContaining("Ma"));
        log.info("Find by topics Angular: {}", tutorialsDAO.findByTopicsContaining("Angular"));
        log.info("Find tutorials with rating more than 4: {}", tutorialsDAO.findByAuthorRatingGreaterThan(4));
    }

}
