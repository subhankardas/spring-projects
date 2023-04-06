package com.codespark.springbootresthateoas.mappings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.codespark.springbootresthateoas.mappings.many_to_many_bi.Product;
import com.codespark.springbootresthateoas.mappings.many_to_many_bi.ProductRepository;
import com.codespark.springbootresthateoas.mappings.many_to_many_bi.Seller;
import com.codespark.springbootresthateoas.mappings.many_to_many_bi.SellerRepository;
import com.codespark.springbootresthateoas.mappings.many_to_may_uni.Course;
import com.codespark.springbootresthateoas.mappings.many_to_may_uni.Trainer;
import com.codespark.springbootresthateoas.mappings.many_to_may_uni.TrainerRepository;

@Component
public class M2MMappingBootstrap implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(M2MMappingBootstrap.class);

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        manyToManyUnidirectional();
        manyToManyBiDirectional();
    }

    public void manyToManyUnidirectional() {
        Course course1 = new Course(1, "Java");
        Course course2 = new Course(2, "Spring MVC");
        Course course3 = new Course(3, "Spring Boot");
        Course course4 = new Course(4, "Hibernate");

        Trainer trainer1 = new Trainer();
        trainer1.setId(1);
        trainer1.setName("Juan");
        trainer1.setRating(4.5f);

        Trainer trainer2 = new Trainer();
        trainer2.setId(2);
        trainer2.setName("Jorge");
        trainer2.setRating(4.2f);

        // Many-to-many unidirectional mapping
        // Trainers --> Courses
        trainer1.addCourse(course2);
        trainer1.addCourse(course3);

        trainer2.addCourse(course1);
        trainer2.addCourse(course3);
        trainer2.addCourse(course4);

        trainerRepository.save(trainer1);
        trainerRepository.save(trainer2);

        trainer1 = trainerRepository.findById(1).get();
        trainer2 = trainerRepository.findById(2).get();
        log.info("Trainer1: {}", trainer1);
        log.info("Trainer2: {}", trainer2);
    }

    public void manyToManyBiDirectional() {
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("iPhone");
        product1.setPrice(12000f);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("iPad");
        product2.setPrice(1000f);

        Product product3 = new Product();
        product3.setId(3);
        product3.setName("Laptop");
        product3.setPrice(4000f);

        Seller seller1 = new Seller();
        seller1.setId(1);
        seller1.setName("Juan");
        seller1.setAddress("San Francisco");

        Seller seller2 = new Seller();
        seller2.setId(2);
        seller2.setName("Jorge");
        seller2.setAddress("New York");

        // Many-to-many bi-directional mapping
        // Products <--> Sellers
        seller1.addProduct(product1);
        seller1.addProduct(product2);

        seller2.addProduct(product2);
        seller2.addProduct(product3);

        sellerRepository.save(seller1);
        sellerRepository.save(seller2);

        seller1 = sellerRepository.findById(1).get();
        seller2 = sellerRepository.findById(2).get();

        product1 = productRepository.findById(1).get();
        product2 = productRepository.findById(2).get();
        product3 = productRepository.findById(3).get();

        log.info("Product1 sellers: {}", product1.getSellers());
        log.info("Product2 sellers: {}", product2.getSellers());
        log.info("Product3 sellers: {}", product3.getSellers());

        log.info("Seller1 products: {}", seller1.getProducts());
        log.info("Seller2 products: {}", seller2.getProducts());
    }

}
