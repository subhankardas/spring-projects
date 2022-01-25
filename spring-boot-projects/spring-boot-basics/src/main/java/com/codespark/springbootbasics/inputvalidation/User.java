package com.codespark.springbootbasics.inputvalidation;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class User {

	@NotNull(message = "Name cannot be null.")
	@Size(min = 3, max = 30, message = "Name should be within 3 to 30 characters.")
	private String name;

	@NotNull(message = "Age cannot be null.")
	@Min(value = 18, message = "Age should be greater than or equal to 18 years.")
	private Integer age;

	@NotNull(message = "Email cannot be null.")
	@Email(message = "Email should be valid.")
	private String email;

	@NotBlank(message = "Address cannot be blank.")
	private String address;

	@PastOrPresent(message = "Registration date cannot be future date.")
	private LocalDate register;

	@Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$", message = "Invalid IP address.")
	private String ip;

}
