package com.codespark.springbootresthateoas.hateoas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class CustomerRepository {

    private Map<Integer, Customer> customers;

    CustomerRepository() {
        customers = new HashMap<>();
        // Create a couple of customers
        List<Purchase> purchases1 = List.of(
                new Purchase(1, "item1", 1, 10.0),
                new Purchase(2, "item2", 2, 20.0),
                new Purchase(3, "item3", 1, 30.0));
        Customer customer1 = new Customer(1, "customer1", "email1", purchases1);

        List<Purchase> purchases2 = List.of(
                new Purchase(4, "item4", 2, 40.0),
                new Purchase(5, "item5", 1, 50.0));
        Customer customer2 = new Customer(2, "customer2", "email2", purchases2);

        // Add some purchases
        customers.put(1, customer1);
        customers.put(2, customer2);
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
