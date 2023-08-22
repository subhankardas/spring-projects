package com.codespark.springbootresthateoas.mappings.one_to_one_uni;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Profile {

    @Id
    private int id;

    private String address;
    private LocalDate dateOfBirth;

}
