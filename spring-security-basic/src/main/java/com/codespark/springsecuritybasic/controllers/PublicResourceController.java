package com.codespark.springsecuritybasic.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/public")
public class PublicResourceController {

    private static final List<Movie> MOVIES = Arrays.asList(
            new Movie(1, "The Shawshank Redemption", "Drama", 9),
            new Movie(2, "The Godfather", "Drama", 9.2f),
            new Movie(3, "The Dark Knight", "Action", 9.5f),
            new Movie(4, "12 Angry Men", "Comedy", 8.3f),
            new Movie(5, "Schindler's List", "Biography", 8.9f));

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovies() {
        return ResponseEntity.ok(MOVIES);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable int id) {
        Optional<Movie> movie = MOVIES.stream().filter(m -> m.getId() == id).findFirst();
        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        }
        return ResponseEntity.notFound().build();
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Movie {

    private int id;
    private String name;
    private String genre;
    private float rating;

}