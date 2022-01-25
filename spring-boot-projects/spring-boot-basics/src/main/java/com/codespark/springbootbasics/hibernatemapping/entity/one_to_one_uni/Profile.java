package com.codespark.springbootbasics.hibernatemapping.entity.one_to_one_uni;

import java.util.Date;

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
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String type;

	private Date timestamp;

	@Override
	public String toString() {
		return "Profile [id=" + id + ", type=" + type + ", timestamp=" + timestamp + "]";
	}

}
