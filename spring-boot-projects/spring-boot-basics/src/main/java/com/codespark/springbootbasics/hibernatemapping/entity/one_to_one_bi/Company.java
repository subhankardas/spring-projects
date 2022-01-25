package com.codespark.springbootbasics.hibernatemapping.entity.one_to_one_bi;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String reference;

	@OneToOne(cascade = CascadeType.ALL)
	private RegistrationDetails registration;

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", reference=" + reference + "]";
	}

}
