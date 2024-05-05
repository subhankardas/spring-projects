package com.codespark.springbootkubernetes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codespark.springbootkubernetes.data.MovieRepository;
import com.codespark.springbootkubernetes.models.Movie;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

    private MovieRepository movieRepository;

    @GetMapping
    public Flux<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/{name}")
    public Mono<Movie> getMovieByName(@PathVariable String name) {
        return movieRepository.findByName(name);
    }

    @GetMapping("/year/{year}")
    public Flux<Movie> getAllMoviesReleasedAfter(@PathVariable int year) {
        return movieRepository.findAllReleasedAfter(year);
    }

}
