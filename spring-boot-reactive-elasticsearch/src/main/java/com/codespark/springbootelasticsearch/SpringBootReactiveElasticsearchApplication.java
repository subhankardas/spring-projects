package com.codespark.springbootelasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.codespark.springbootelasticsearch.data")
public class SpringBootReactiveElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactiveElasticsearchApplication.class, args);
	}

}
