package com.codespark.springbootbasics.inputvalidation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/validate")
public class InputValidationController {

	@Autowired
	private InputValidator<Object> validator;

	@GetMapping("/user")
	public List<String> validateUserDetails(@RequestBody User user) {
		return validator.validate(user);
	}

}
