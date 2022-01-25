package com.codespark.springbootbasics.inputvalidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InputValidator<T> {

	private static final Logger log = LoggerFactory.getLogger(InputValidator.class);

	/**
	 * Receive a generic domain class and validate fields using a validator and
	 * return a list of error messages.
	 * 
	 * @param domain Domain object to validate
	 * @return List of error messages
	 */
	public List<String> validate(T domain) {
		log.info("Validate: " + domain.toString());
		List<String> errors = new ArrayList<>();

		// Programmatic validation API
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		// Validate domain class and return list of error messages
		Set<ConstraintViolation<T>> violations = validator.validate(domain);
		for (ConstraintViolation<T> violation : violations) {
			errors.add(violation.getMessage());
		}

		return errors;
	}

}
