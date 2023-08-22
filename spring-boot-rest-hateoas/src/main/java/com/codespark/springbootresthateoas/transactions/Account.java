package com.codespark.springbootresthateoas.transactions;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account {

    private static final String MIN_ACCOUNT_BALANCE = "100.00";

    @Id
    private int id;
    private String name;

    @DecimalMin(value = MIN_ACCOUNT_BALANCE)
    private BigDecimal balance;

}
