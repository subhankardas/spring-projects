package com.codespark.springbootdatajpa.inputvalidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordCustomValidator.class)
@interface Password {
    String message() default "Password should be valid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value() default "";
}

/**
 * Password Custom Validator: Implements the Password constraint validator.
 */
public class PasswordCustomValidator implements ConstraintValidator<Password, String> {

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 50;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        // 1. Validate the password for null
        if (password == null) {
            context.buildConstraintViolationWithTemplate("Password cannot be empty.").addConstraintViolation();
            return false;
        }

        // 2. Validate the password length
        if (password.length() < MIN_LENGTH || password.length() > MAX_LENGTH) {
            context.buildConstraintViolationWithTemplate(
                    "Password length must be between " + MIN_LENGTH + " and " + MAX_LENGTH + " characters.")
                    .addConstraintViolation();
            return false;
        }

        // 3. Validate the password pattern
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$";
        if (!password.matches(pattern)) {
            context.buildConstraintViolationWithTemplate(
                    "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

}
