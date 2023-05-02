package com.codespark.springbootmongodbneo4j.neo4j.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Node
@NoArgsConstructor
@ToString
public class Customer {

	@Id
	@GeneratedValue
	private Long id;

	@Getter
	private String name;

	public Customer(String name) {
		this.name = name;
	}

	@Getter
	@Relationship(type = "BOUGHT")
	private List<Product> orders;

	public void bought(Product product) {
		if (orders == null)
			orders = new ArrayList<>();
		orders.add(product);
	}

	@Getter
	@Relationship(type = "WISHLIST")
	private Set<Product> wishlist;

	public void wishlisted(Product product) {
		if (wishlist == null)
			wishlist = new HashSet<>();
		wishlist.add(product);
	}

	@Getter
	@Relationship(type = "VIEWED")
	private Set<Product> viewed;

	public void viewed(Product product) {
		if (viewed == null)
			viewed = new HashSet<>();
		viewed.add(product);
	}

}
