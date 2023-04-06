package com.codespark.springbootresthateoas.mappings.one_to_one_uni;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Profile {

    @Id
    private int id;

    private String address;
    private LocalDate dateOfBirth;

}
