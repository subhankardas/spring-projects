package com.codespark.grpc.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codespark.grpc.movie.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByGenre(String genre);
}
