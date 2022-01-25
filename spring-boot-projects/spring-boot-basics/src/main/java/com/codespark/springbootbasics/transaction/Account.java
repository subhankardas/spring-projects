package com.codespark.springbootbasics.transaction;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	@Id
	private int id;
	private String name;

	@DecimalMin(value = "10.0")
	private BigDecimal balance;

}
