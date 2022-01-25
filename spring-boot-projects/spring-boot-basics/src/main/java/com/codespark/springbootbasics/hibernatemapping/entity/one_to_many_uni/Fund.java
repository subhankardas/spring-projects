package com.codespark.springbootbasics.hibernatemapping.entity.one_to_many_uni;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fund {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String reference;

	private double amount;

}
