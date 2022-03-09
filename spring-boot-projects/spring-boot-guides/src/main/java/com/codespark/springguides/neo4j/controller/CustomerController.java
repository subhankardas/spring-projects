package com.codespark.springguides.neo4j.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codespark.springguides.neo4j.entity.Customer;
import com.codespark.springguides.neo4j.repository.CustomerRepository;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping
	public Customer findByName(@RequestParam("q") String name) {
		return customerRepository.findByName(name);
	}

	@GetMapping("/wishlist")
	public List<Customer> findByWishlistName(@RequestParam("q") String productName) {
		return customerRepository.findByWishlistName(productName);
	}

	@GetMapping("/wishlist/category")
	public List<Customer> findByWishlistCategoryName(@RequestParam("q") String productName) {
		return customerRepository.findByWishlistCategoryName(productName);
	}

}
