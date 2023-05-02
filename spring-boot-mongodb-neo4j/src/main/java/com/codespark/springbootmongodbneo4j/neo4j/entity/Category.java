package com.codespark.springbootmongodbneo4j.neo4j.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Node
@NoArgsConstructor
@ToString
public class Category {

	@Id
	@GeneratedValue
	private Long id;

	@Getter
	private String name;

	public Category(String name) {
		this.name = name;
	}

}
