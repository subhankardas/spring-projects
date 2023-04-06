package com.codespark.springbootresthateoas.mappings.one_to_many_uni;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asset {

    @Id
    private int id;

    private String name;
    private float valuation;

}
