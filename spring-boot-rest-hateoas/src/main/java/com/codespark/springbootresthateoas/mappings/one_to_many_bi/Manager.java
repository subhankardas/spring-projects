package com.codespark.springbootresthateoas.mappings.one_to_many_bi;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Manager {

    @Id
    private int id;

    private String name;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "manager_id")
    private List<Employee> employees;

    @Override
    public String toString() {
        return "Manager(id=" + id + ", name=" + name + ", email=" + email + ")";
    }

}
