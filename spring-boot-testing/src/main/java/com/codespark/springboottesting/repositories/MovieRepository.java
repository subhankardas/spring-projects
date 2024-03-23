package com.codespark.springboottesting.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codespark.springboottesting.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitle(String title);

    List<Movie> findByGenre(String genre);

    @Query("SELECT m FROM Movie m WHERE m.title LIKE %?1%")
    List<Movie> findByTitleContaining(String keyword);

}
