package com.codespark.springsecuritybasic.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/private")
public class PrivateResourceController {

    private static final List<User> USERS = Arrays.asList(
            new User(1, "John", "Wick"),
            new User(2, "Jane", "Doe"),
            new User(3, "Jack", "Knight"));

    private static final Map<Integer, List<Movie>> WATCHLIST = new HashMap<>();

    static {
        WATCHLIST.put(1, Arrays.asList(
                new Movie(1, "The Shawshank Redemption", "Drama", 9),
                new Movie(2, "The Godfather", "Drama", 9.2f)));
        WATCHLIST.put(2, Arrays.asList(
                new Movie(3, "The Dark Knight", "Action", 9.5f),
                new Movie(4, "12 Angry Men", "Comedy", 8.3f)));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(USERS);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        Optional<User> user = USERS.stream().filter(u -> u.getId() == id).findFirst();
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/watchlist/{id}")
    public ResponseEntity<Watchlist> getWatchlist(@PathVariable int id) {
        List<Movie> watchlist = WATCHLIST.get(id);
        if (watchlist != null) {
            return ResponseEntity.ok(new Watchlist(id, watchlist));
        }
        return ResponseEntity.notFound().build();
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class User {

    private int id;
    private String name;
    private String email;

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Watchlist {

    private int id;
    private List<Movie> movies;

}