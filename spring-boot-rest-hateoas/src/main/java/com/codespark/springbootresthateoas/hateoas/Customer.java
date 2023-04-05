package com.codespark.springbootresthateoas.hateoas;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends RepresentationModel<Customer> {

    private int id;
    private String name;
    private String email;

    @JsonIgnore
    private List<Purchase> purchases;

}
