package com.codespark.springbootmongodbneo4j.neo4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.codespark.springbootmongodbneo4j.neo4j.entity.Category;
import com.codespark.springbootmongodbneo4j.neo4j.entity.Customer;
import com.codespark.springbootmongodbneo4j.neo4j.entity.Product;
import com.codespark.springbootmongodbneo4j.neo4j.repository.CategoryRepository;
import com.codespark.springbootmongodbneo4j.neo4j.repository.CustomerRepository;
import com.codespark.springbootmongodbneo4j.neo4j.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Neo4jBootstrap implements CommandLineRunner {

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
        Customer mike = customerRepository.save(new Customer("Mike"));
        Customer jane = customerRepository.save(new Customer("Jane"));

        // ---------- PRODUCTS ---------- //
        Product iphone = productRepository.save(new Product("IPhone 11 XS"));
        Product legion = productRepository.save(new Product("Lenovo Legion"));
        Product note5 = productRepository.save(new Product("Mi Note 5 Pro"));

        // ---------- CATEGORIES ---------- //
        Category phone = categoryRepository.save(new Category("Phone"));
        Category laptop = categoryRepository.save(new Category("Laptop"));

        // ---------- PRODUCT -- [IS_A] --> CATEGORY ---------- //
        iphone.isA(phone);
        legion.isA(laptop);
        note5.isA(phone);

        iphone = productRepository.save(iphone);
        legion = productRepository.save(legion);
        note5 = productRepository.save(note5);

        //    CUSTOMER -- [WISHLIST/VIEW/BOUGHT] --> PRODUCT   //
        mike.bought(iphone);
        mike.viewed(note5);
        mike = customerRepository.save(mike);

        jane.wishlisted(iphone);
        jane.bought(legion);
        jane = customerRepository.save(jane);

        log.info("Finished setting up sample database");
    }

}
