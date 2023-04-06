package com.codespark.springbootresthateoas.mappings.one_to_one_bi;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Registration {

    @Id
    private int id;

    private String reference;
    private LocalDate date;

    @OneToOne(mappedBy = "registration")
    private Company company;

    @Override
    public String toString() {
        return "Registration(id=" + id + ", reference=" + reference + ", date=" + date + ")";
    }

}
