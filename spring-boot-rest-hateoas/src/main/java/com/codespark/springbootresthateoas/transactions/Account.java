package com.codespark.springbootresthateoas.transactions;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;

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
