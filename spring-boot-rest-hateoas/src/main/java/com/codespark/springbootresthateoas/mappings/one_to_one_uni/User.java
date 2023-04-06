package com.codespark.springbootresthateoas.mappings.one_to_one_uni;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
