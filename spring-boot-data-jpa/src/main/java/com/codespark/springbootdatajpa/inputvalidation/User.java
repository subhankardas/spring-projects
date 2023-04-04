package com.codespark.springbootdatajpa.inputvalidation;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

    @NotNull(message = "Name cannot be empty.")
    @Size(min = 3, max = 30, message = "Name should be within 3 to 30 characters.")
    private String name;

    @NotNull(message = "Age cannot be empty.")
    @Min(value = 18, message = "Age should be greater than or equal to 18 years.")
    private Integer age;

    @NotNull(message = "Email cannot be empty.")
    @Email(message = "Email should be valid.")
    private String email;

    @NotBlank(message = "Address cannot be empty.")
    private String address;

    @PastOrPresent(message = "Registration date cannot be a future date.")
    private LocalDate registeredOn;

    @Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$", message = "Invalid IP address.")
    private String ipAddress;

    @Password
    private String password;

    // Constructors
    public User() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

    public void setRegisteredOn(LocalDate register) {
        this.registeredOn = register;
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
