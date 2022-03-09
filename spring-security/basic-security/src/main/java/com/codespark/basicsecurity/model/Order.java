package com.codespark.basicsecurity.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private List<String> products;
	private List<Double> prices;

	public double getTotal() {
		return prices.stream().mapToDouble(Double::doubleValue).sum();
	}

}
