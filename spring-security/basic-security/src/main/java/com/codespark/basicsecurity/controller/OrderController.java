package com.codespark.basicsecurity.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codespark.basicsecurity.model.Order;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@GetMapping
	public List<Order> getOrders() {
		List<Order> orders = new ArrayList<>();

		Order order1 = new Order();
		order1.setId(1);
		order1.setProducts(Arrays.asList("Fruits", "Grocery", "Oil"));
		order1.setPrices(Arrays.asList(112d, 723d, 45d));

		Order order2 = new Order();
		order2.setId(2);
		order2.setProducts(Arrays.asList("Honey", "Towel"));
		order2.setPrices(Arrays.asList(34d, 33d));

		orders.add(order1);
		orders.add(order2);

		return orders;
	}

	@GetMapping("/{id}")
	public Order getOrder(@PathVariable("id") int id) {
		Order order = new Order();
		order.setId(id);
		order.setProducts(Arrays.asList("Fruits", "Grocery", "Oil"));
		order.setPrices(Arrays.asList(112d, 723d, 45d));

		return order;
	}

}
