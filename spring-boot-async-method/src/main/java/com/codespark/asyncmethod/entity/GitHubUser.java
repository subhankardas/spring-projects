package com.codespark.asyncmethod.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubUser {

    private long id;
    private String name;
    private String company;
    private String bio;
    private String avatar_url;

}
