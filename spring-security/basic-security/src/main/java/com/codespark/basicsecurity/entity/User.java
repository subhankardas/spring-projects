package com.codespark.basicsecurity.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String username;
	private String password;
	private boolean enabled;

	@ElementCollection
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "username"))
	@Column(name = "role")
	private List<String> roles;

}
