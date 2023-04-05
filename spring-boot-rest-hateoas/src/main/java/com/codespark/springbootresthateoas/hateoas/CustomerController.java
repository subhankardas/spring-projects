package com.codespark.springbootresthateoas.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        Customer customer = customerRepository.getCustomerById(id);

        // Add self and purchases links
        addLinksToCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{id}/purchases")
    public ResponseEntity<List<Purchase>> getCustomerPurchases(@PathVariable int id) {
        Customer customer = customerRepository.getCustomerById(id);
        List<Purchase> purchases = customer.getPurchases();

        // Add customer link to purchases
        purchases.forEach(purchase -> addLinksToPurchase(id, purchase));
        return ResponseEntity.ok(purchases);
    }

    private void addLinksToCustomer(Customer customer) {
        // Add self link to customer if not already there
        Link selfLink = linkTo(methodOn(CustomerController.class).getCustomerById(customer.getId())).withSelfRel();
        if (!customer.hasLink(selfLink.getRel())) {
            customer.add(selfLink);
        }

        // Add purchases link to customer if not already there
        Link purchasesLink = linkTo(methodOn(CustomerController.class).getCustomerPurchases(customer.getId()))
                .withRel("purchases");
        if (!customer.hasLink(purchasesLink.getRel())) {
            customer.add(purchasesLink);
        }
    }

    private void addLinksToPurchase(int customerId, Purchase purchase) {
        // Add customer link to purchase if not already there
        Link customerLink = linkTo(methodOn(CustomerController.class).getCustomerById(customerId))
                .withRel("customer");
        if (!purchase.hasLink(customerLink.getRel())) {
            purchase.add(customerLink);
        }
    }
}
