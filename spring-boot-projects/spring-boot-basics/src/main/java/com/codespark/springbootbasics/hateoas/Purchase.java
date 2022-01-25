package com.codespark.springbootbasics.hateoas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

	private int purchaseId;
	private String name;
	private int quantity;
	private float price;

}
