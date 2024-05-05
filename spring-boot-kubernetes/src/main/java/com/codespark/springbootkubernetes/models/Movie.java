package com.codespark.springbootkubernetes.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("movies")
public class Movie {

    @Id
    private int id;
    private String name;
    private int year;
    private String genre;

}
