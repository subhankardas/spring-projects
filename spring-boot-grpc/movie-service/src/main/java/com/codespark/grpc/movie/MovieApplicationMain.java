package com.codespark.grpc.movie;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codespark.grpc.movie.model.Movie;
import com.codespark.grpc.movie.repository.MovieRepository;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class MovieApplicationMain implements CommandLineRunner {

	private final MovieRepository movieRepository;

	public static void main(String[] args) {
		SpringApplication.run(MovieApplicationMain.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		movieRepository.save(new Movie(1, "The Matrix", "Action", 1999));
		movieRepository.save(new Movie(2, "Harry Potter", "Adventure", 2001));
		movieRepository.save(new Movie(3, "Star Wars", "Adventure", 1977));
		movieRepository.save(new Movie(4, "Forrest Gump", "Drama", 1994));
	}

}
