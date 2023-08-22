package com.codespark.springbootresthateoas.mappings.one_to_one_uni;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import lombok.Data;

@Entity(name = "users")
@Data
public class User {

    @Id
    private int id;

    private String name;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;

}
