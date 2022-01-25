package com.codespark.springbootbasics.hateoas;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO class for a resource that overrides model DTO class which can contain
 * links.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends RepresentationModel<Customer> {

	private int customerId;
	private String name;
	private String address;

	@JsonIgnore
	private List<Purchase> purchases;

}
