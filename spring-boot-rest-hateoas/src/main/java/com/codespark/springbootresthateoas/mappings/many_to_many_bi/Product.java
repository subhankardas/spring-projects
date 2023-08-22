package com.codespark.springbootresthateoas.mappings.many_to_many_bi;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    private int id;

    private String name;
    private float price;

    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Seller> sellers = new HashSet<>();

    public void addSeller(Seller seller) {
        sellers.add(seller);
    }

    public void removeSeller(Seller seller) {
        sellers.remove(seller);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product(id=" + id + ", name=" + name + ", price=" + price + ")";
    }

}
