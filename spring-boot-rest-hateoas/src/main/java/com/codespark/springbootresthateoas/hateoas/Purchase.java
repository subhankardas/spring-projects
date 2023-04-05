package com.codespark.springbootresthateoas.hateoas;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Purchase extends RepresentationModel<Purchase> {

    private int id;
    private String item;
    private int quantity;
    private double price;

}
