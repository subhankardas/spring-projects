package com.codespark.springbootdatajpa.datajpa;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

/*
 * @Entity annotation is used to mark the class as an entity/table for JPA.
 * @Id annotation is used to mark the primary key of the entity.
 * @GeneratedValue annotation is used to generate the primary key of the entity.
 * @Column annotation is used to add a column to the entity.
 * @Transient annotation is used to mark the column as transient i.e. not stored in the database.
 */
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // ID is an auto-generated primary key
    private String name;
    private int age;

    @Column(unique = true)
    private String email; // Email is unique
    private String address;

    @Column(name = "registration_date") // This column is renamed as "registration_date"
    private LocalDate registeredOn;

    @Transient
    private String ipAddress; // IP address is not stored
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
