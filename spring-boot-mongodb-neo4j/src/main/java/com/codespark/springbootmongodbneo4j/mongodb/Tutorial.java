package com.codespark.springbootmongodbneo4j.mongodb;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tutorials")
public class Tutorial {

    @Id
    private long id;
    private List<String> topics;
    private Author author;

    public Tutorial() {
    }

    public Tutorial(long id, List<String> topics, Author author) {
        this.id = id;
        this.topics = topics;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", topics=" + topics + ", author=" + author + "]";
    }

}

class Author {

    private String name;
    private float rating;

    public Author() {
    }

    public Author(String name, float rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "[name=" + name + ", rating=" + rating + "]";
    }

}