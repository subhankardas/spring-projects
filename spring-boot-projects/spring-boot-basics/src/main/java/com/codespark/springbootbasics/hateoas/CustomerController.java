package com.codespark.springbootbasics.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerRepository repository;

	/**
	 * Returns list of all customers with links to related resources.
	 * 
	 * @return List of customers
	 */
	@GetMapping("/customers")
	public CollectionModel<Customer> getAllCustomers() {
		// Get the list of customers from store
		List<Customer> customers = repository.getAllCustomers();

		// Add self links for each individual customers in the list
		for (Customer customer : customers) {
			Link selfLink = linkTo(methodOn(CustomerController.class).getCustomerById(customer.getCustomerId()))
					.withSelfRel();

			customer.removeLinks();
			customer.add(selfLink);
		}

		// Add a self link for this resource
		Link selfLink = linkTo(methodOn(CustomerController.class).getAllCustomers()).withSelfRel();
		CollectionModel<Customer> response = CollectionModel.of(customers, selfLink);

		return response;
	}

	/**
	 * Returns details for a customer with related resource links.
	 * 
	 * @param customerId Customer ID
	 * @return Customer details
	 */
	@GetMapping("/customers/{id}")
	public Customer getCustomerById(@PathVariable("id") int customerId) {
		// Get customer from store
		Customer customer = repository.getCustomerById(customerId);

		// Create a self link and related purchases link
		Link selfLink = linkTo(methodOn(CustomerController.class).getCustomerById(customer.getCustomerId()))
				.withSelfRel();
		Link purchasesLink = linkTo(
				methodOn(CustomerController.class).getPurchasesForCustomer(customer.getCustomerId()))
						.withRel("purchases");

		// Add required links
		customer.removeLinks();
		customer.add(selfLink);
		customer.add(purchasesLink);

		return customer;
	}

	/**
	 * Returns list of purchases for a customer.
	 * 
	 * @param customerId Customer ID
	 * @return List of purchases
	 */
	@GetMapping("/customers/{id}/purchases")
	public CollectionModel<Purchase> getPurchasesForCustomer(@PathVariable("id") int customerId) {
		// Get list of purchases from store
		List<Purchase> purchases = repository.getPurchasesForCustomer(customerId);

		// Create a self link for this resource
		Link selfLink = linkTo(methodOn(CustomerController.class).getPurchasesForCustomer(customerId))
				.withRel("purchases");

		return CollectionModel.of(purchases, selfLink);
	}

}
