package com.codespark.springboottesting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codespark.springboottesting.entities.Movie;
import com.codespark.springboottesting.repositories.MovieRepository;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class SpringBootTestingApplication implements CommandLineRunner {

	private final MovieRepository movieRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		movieRepository.save(new Movie(0l, "Avengers", "Action", 2019));
		movieRepository.save(new Movie(0l, "Godfather", "Drama", 1972));
		movieRepository.save(new Movie(0l, "Harry Potter", "Fantasy", 2011));
		movieRepository.save(new Movie(0l, "Avengers: Endgame", "Action", 2021));
		movieRepository.save(new Movie(0l, "Pursuit to happiness", "Drama", 2015));
		movieRepository.save(new Movie(0l, "Mission Impossible", "Action", 2015));
	}

}
