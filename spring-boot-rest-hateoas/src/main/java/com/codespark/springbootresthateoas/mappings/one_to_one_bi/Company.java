package com.codespark.springbootresthateoas.mappings.one_to_one_bi;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Company {

    @Id
    private int id;

    private String name;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Registration registration;

    @Override
    public String toString() {
        return "Company(id=" + id + ", name=" + name + ", email=" + email + ")";
    }

}
