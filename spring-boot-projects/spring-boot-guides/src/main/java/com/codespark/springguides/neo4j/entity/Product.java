package com.codespark.springguides.neo4j.entity;

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
public class Product {

	@Id
	@GeneratedValue
	private Long id;

	@Getter
	private String name;

	public Product(String name) {
		this.name = name;
	}

	@Getter
	@Relationship(type = "IS_A")
	private Category category;

	public void isA(Category category) {
		this.category = category;
	}

}
