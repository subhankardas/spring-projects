package com.codespark.springguides.neo4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.codespark.springguides.neo4j.entity.Category;
import com.codespark.springguides.neo4j.entity.Customer;
import com.codespark.springguides.neo4j.entity.Product;
import com.codespark.springguides.neo4j.repository.CategoryRepository;
import com.codespark.springguides.neo4j.repository.CustomerRepository;
import com.codespark.springguides.neo4j.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataBootstrap implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void run(String... args) throws Exception {

		deleteAll();
		setupCustomerProductDetailsAndPurchaseHistory();

	}

	public void deleteAll() {
		customerRepository.deleteAll();
		productRepository.deleteAll();
		categoryRepository.deleteAll();

		log.info("Finished clearing Neo4j database");
	}

	public void setupCustomerProductDetailsAndPurchaseHistory() {
		// ---------- CUSTOMERS ---------- //
		Customer ayan = new Customer("Ayan");
		Customer samir = new Customer("Samir");

		ayan = customerRepository.save(ayan);
		samir = customerRepository.save(samir);

		// ---------- PRODUCTS ---------- //
		Product iphone = new Product("IPhone 11 XS");
		Product legion = new Product("Lenovo Legion");
		Product note5 = new Product("Mi Note 5 Pro");

		iphone = productRepository.save(iphone);
		legion = productRepository.save(legion);
		note5 = productRepository.save(note5);

		// ---------- CATEGORIES ---------- //
		Category phone = new Category("Phone");
		Category laptop = new Category("Laptop");

		phone = categoryRepository.save(phone);
		laptop = categoryRepository.save(laptop);

		// ---------- PRODUCT -> CATEGORY ---------- //
		iphone.isA(phone);
		legion.isA(laptop);
		note5.isA(phone);

		iphone = productRepository.save(iphone);
		legion = productRepository.save(legion);
		note5 = productRepository.save(note5);

		// ---------- CUSTOMER -> PRODUCT ---------- //
		ayan.bought(iphone);
		ayan.viewed(note5);
		ayan = customerRepository.save(ayan);

		samir.wishlisted(iphone);
		samir.bought(legion);
		samir = customerRepository.save(samir);

		log.info("Finished setting up sample database");
	}

}
