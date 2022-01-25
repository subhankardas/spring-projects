package com.codespark.springbootbasics.hibernatemapping.bootstrap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codespark.springbootbasics.hibernatemapping.entity.many_to_many_bi.Product;
import com.codespark.springbootbasics.hibernatemapping.entity.many_to_many_bi.Seller;
import com.codespark.springbootbasics.hibernatemapping.entity.many_to_may_uni.Course;
import com.codespark.springbootbasics.hibernatemapping.entity.many_to_may_uni.Trainer;
import com.codespark.springbootbasics.hibernatemapping.repository.ProductRepository;
import com.codespark.springbootbasics.hibernatemapping.repository.SellerRepository;
import com.codespark.springbootbasics.hibernatemapping.repository.TrainerRepository;

@Component
public class ManyToManyBootstrap {

	private static Logger logger = LoggerFactory.getLogger(ManyToManyBootstrap.class);

	@Autowired
	private TrainerRepository trainerRepository;

	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private ProductRepository productRepository;

	public void run() {
		saveManyToManyUnidirectional();
		getManyToManyUnidirectional();

		saveManyToManyBidirectional();
		getManyToManyBidirectional();
	}

	public void saveManyToManyUnidirectional() {
		Course course1 = new Course(0, "Microservices");
		Course course2 = new Course(0, "Docker");

		Course course3 = new Course(0, "AI/ML");
		Course course4 = new Course(0, "Java");

		Trainer trainer1 = new Trainer(0, "Chayan Banerjee", 2000,
				new HashSet<>(Arrays.asList(course1, course2, course3)));

		Trainer trainer2 = new Trainer(0, "Ranjit Sharma", 3200, new HashSet<>(Arrays.asList(course2, course4)));

		trainerRepository.saveAll(Arrays.asList(trainer1, trainer2));

		logger.info("TRAINER1: " + trainer1);
		logger.info("COURSES1: " + trainer1.getCourses());

		logger.info("TRAINER2: " + trainer2);
		logger.info("COURSES2: " + trainer2.getCourses());
	}

	public void getManyToManyUnidirectional() {
		Optional<Trainer> data1 = trainerRepository.findById(1);

		// TRAINER --> COURSES
		if (data1.isPresent()) {
			Trainer trainer = data1.get();
			logger.info("TRAINER1: " + trainer);
			logger.info("COURSES1: " + trainer.getCourses());
		}

		Optional<Trainer> data2 = trainerRepository.findById(2);

		// TRAINER --> COURSES
		if (data2.isPresent()) {
			Trainer trainer = data2.get();
			logger.info("TRAINER2: " + trainer);
			logger.info("COURSES2: " + trainer.getCourses());
		}
	}

	public void saveManyToManyBidirectional() {
		Product product1 = new Product(0, "iPhone10", 1000000);
		Product product2 = new Product(0, "Nexus 6", 400000);
		Product product3 = new Product(0, "OnePlus 6T", 78000);

		Seller seller1 = new Seller(0, "ABC Stores", "SLR123", new HashSet<>(Arrays.asList(product1, product2)));
		Seller seller2 = new Seller(0, "XYZ Mobiles", "SLR456", new HashSet<>(Arrays.asList(product2, product3)));

		sellerRepository.saveAll(Arrays.asList(seller1, seller2));

		logger.info("SELLER1: " + seller1);
		logger.info("PRODUCTS1: " + seller1.getProducts());

		logger.info("SELLER2: " + seller2);
		logger.info("PRODUCTS2: " + seller2.getProducts());
	}

	public void getManyToManyBidirectional() {
		Optional<Seller> data1 = sellerRepository.findById(1);

		// SELLER <--> PRODUCTS
		if (data1.isPresent()) {
			Seller seller = data1.get();
			logger.info("SELLER1: " + seller);
			logger.info("PRODUCTS1: " + seller.getProducts());
		}

		Optional<Product> data2 = productRepository.findById(2);

		// PRODUCTS <--> SELLER
		if (data2.isPresent()) {
			Product product = data2.get();
			logger.info("PRODUCT2: " + product);
			logger.info("SELLERS: " + product.getSellers());
		}
	}
}