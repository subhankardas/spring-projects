package com.codespark.springboottesting.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codespark.springboottesting.entities.Movie;
import com.codespark.springboottesting.services.interfaces.MovieService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/movies/search")
public class MovieSearchController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> search(@RequestParam("q") String query) {
        List<Movie> results = movieService.findByTitleContaining(query);

        if (results == null || results.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(results);
    }

    @GetMapping("/genre")
    public ResponseEntity<List<Movie>> searchByGenre(@RequestParam("q") String query) {
        List<Movie> results = movieService.findByGenre(query);

        if (results == null || results.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(results);
    }

}
