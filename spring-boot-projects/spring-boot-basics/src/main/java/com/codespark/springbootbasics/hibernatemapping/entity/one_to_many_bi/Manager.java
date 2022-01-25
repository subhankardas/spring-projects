package com.codespark.springbootbasics.hibernatemapping.entity.one_to_many_bi;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private short rating;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "manager_id")
	List<Asset> assets;

	public Manager(int id, String name, short rating) {
		this.id = id;
		this.name = name;
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", name=" + name + ", rating=" + rating + "]";
	}

}
