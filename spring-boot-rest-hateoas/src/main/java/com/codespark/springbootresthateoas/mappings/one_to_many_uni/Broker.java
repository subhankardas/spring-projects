package com.codespark.springbootresthateoas.mappings.one_to_many_uni;

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
public class Broker {

    @Id
    private int id;

    private String name;
    private String address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "asset_id")
    private List<Asset> assets;

}
