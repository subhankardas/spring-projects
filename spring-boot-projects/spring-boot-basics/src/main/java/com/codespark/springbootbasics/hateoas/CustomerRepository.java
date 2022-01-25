package com.codespark.springbootbasics.hateoas;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class CustomerRepository {

	private Map<Integer, Customer> customers;

	/**
	 * Initialize the repository records.
	 */
	public CustomerRepository() {
		customers = new HashMap<>();

		Purchase purchase1 = new Purchase(1, "iPhone", 2, 230000);
		Purchase purchase2 = new Purchase(2, "Laptop", 1, 130000);

		List<Purchase> purchases1 = Arrays.asList(purchase1, purchase2);
		Customer customer1 = new Customer(1, "VK Singh", "Kolkata", purchases1);

		Purchase purchase3 = new Purchase(3, "Monitor", 1, 30000);
		Purchase purchase4 = new Purchase(4, "Keyboard", 1, 5000);
		Purchase purchase5 = new Purchase(5, "Mouse", 2, 3000);

		List<Purchase> purchases2 = Arrays.asList(purchase3, purchase4, purchase5);
		Customer customer2 = new Customer(2, "MK Rao", "Odisha", purchases2);

		customers = new HashMap<>();
		customers.put(customer1.getCustomerId(), customer1);
		customers.put(customer2.getCustomerId(), customer2);
	}

	public List<Customer> getAllCustomers() {
		return customers.values().stream().collect(Collectors.toList());
	}

	public Customer getCustomerById(int customerId) {
		return customers.get(customerId);
	}

	public List<Purchase> getPurchasesForCustomer(int customerId) {
		return customers.get(customerId).getPurchases();
	}

}
