package com.codespark.springbootkubernetes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableR2dbcRepositories(basePackages = "com.codespark.springbootkubernetes.data")
public class SpringBootKubernetesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKubernetesApplication.class, args);
	}

}
