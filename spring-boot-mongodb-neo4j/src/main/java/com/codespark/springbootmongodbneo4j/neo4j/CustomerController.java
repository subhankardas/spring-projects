package com.codespark.springbootmongodbneo4j.neo4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codespark.springbootmongodbneo4j.neo4j.entity.Customer;
import com.codespark.springbootmongodbneo4j.neo4j.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping // http://localhost:8080/customers?q=Mike
	public Customer findByName(@RequestParam("q") String name) {
		return customerRepository.findByName(name);
	}

	@GetMapping("/wishlist") // http://localhost:8080/customers/wishlist?q=IPhone%2011%20XS
	public List<Customer> findByWishlistName(@RequestParam("q") String productName) {
		return customerRepository.findByWishlistName(productName);
	}

	@GetMapping("/wishlist/category") // http://localhost:8080/customers/wishlist/category?q=Phone
	public List<Customer> findByWishlistCategoryName(@RequestParam("q") String productName) {
		return customerRepository.findByWishlistCategoryName(productName);
	}

}
