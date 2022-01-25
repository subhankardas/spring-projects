package com.codespark.springbootbasics.hibernatemapping.entity.one_to_one_uni;

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
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	private Profile profile;

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", profile=" + profile + "]";
	}

}
