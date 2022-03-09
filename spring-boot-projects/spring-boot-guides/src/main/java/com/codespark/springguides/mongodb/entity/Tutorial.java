package com.codespark.springguides.mongodb.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tutorials")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tutorial {

	@Id
	private String id;

	private Author author;
	private List<String> topics;

}
