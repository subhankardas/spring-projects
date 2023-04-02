package com.codespark.springbootrestfulweb.restservice;

/**
 * 
 * Model Representation Class: The '@RequestBody' annotation maps the
 * HttpRequest body to a transfer or domain object, enabling automatic
 * deserialization of the inbound HttpRequest body onto a Java object of this
 * class.
 *
 */
public class User {

    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
