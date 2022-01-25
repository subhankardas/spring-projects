package com.codespark.springbootbasics.hibernatemapping.entity.one_to_many_bi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asset {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String reference;

	private double valuation;

	@ManyToOne
	private Manager manager;

	public Asset(int id, String reference, double valuation) {
		this.id = id;
		this.reference = reference;
		this.valuation = valuation;
	}

	@Override
	public String toString() {
		return "Asset [id=" + id + ", reference=" + reference + ", valuation=" + valuation + "]";
	}

}
