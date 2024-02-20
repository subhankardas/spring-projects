package com.codespark.springbootgraphql;

import java.util.Map;

public record Audience(String rating, String type) {

    private static final Map<String, String> types = Map.of(
            "G", "General",
            "PG", "Parental Guidance",
            "PG-13", "Parental Guidance",
            "R", "Restricted",
            "NC-17", "Adults Only",
            "NR", "Not Rated",
            "X", "Adults Only");

    public static Audience getByRating(String rating) {
        return new Audience(rating, types.getOrDefault(rating, "Not Found"));
    }

}