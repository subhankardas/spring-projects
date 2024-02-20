package com.codespark.springbootgraphql;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Movie(
        @JsonProperty("Title") String title,
        @JsonProperty("Year") String year,
        @JsonProperty("Genre") String genre,
        @JsonProperty("Director") String director,
        @JsonProperty("Actors") String actors,
        @JsonProperty("Plot") String plot,
        @JsonProperty("Rated") String rating,
        Audience audience) {

}
