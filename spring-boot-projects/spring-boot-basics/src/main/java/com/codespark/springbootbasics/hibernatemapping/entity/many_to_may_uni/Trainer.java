package com.codespark.springbootbasics.hibernatemapping.entity.many_to_may_uni;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private int reputation;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	private Set<Course> courses = new HashSet<>();

	public Trainer(int id, String name, int reputation) {
		this.id = id;
		this.name = name;
		this.reputation = reputation;
	}

	public void addCourse(Course course) {
		courses.add(course);
	}

	public void removeCourse(Course course) {
		courses.remove(course);
	}

	@Override
	public String toString() {
		return "Trainer [id=" + id + ", name=" + name + ", reputation=" + reputation + "]";
	}

}
