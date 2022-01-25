package com.codespark.springbootbasics.hibernatemapping.entity.one_to_one_bi;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="registrationdetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String fileID;

	private Date creationDate;

	@OneToOne(mappedBy = "registration")
	private Company company;

	@Override
	public String toString() {
		return "RegistrationDetails [id=" + id + ", fileID=" + fileID + ", creationDate=" + creationDate + "]";
	}

}
