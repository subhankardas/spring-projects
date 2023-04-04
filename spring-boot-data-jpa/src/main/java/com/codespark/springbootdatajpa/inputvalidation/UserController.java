package com.codespark.springbootdatajpa.inputvalidation;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Endpoint to add user data with field specific input validations. If any error
     * is found, it will return list of errors with field name and error message.
     * The '@Valid' annotation performs validation on the request body fields.
     * Example valid request body:
     * {
     * "name": "John",
     * "age": 18,
     * "email": "john@doe",
     * "address": "London",
     * "registeredOn": "2023-04-04",
     * "ipAddress": "123.456.789.001",
     * "password": "John@123"
     * }
     */
    @PostMapping
    public List<InputError> addUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return result.getAllErrors().stream()
                    .map(error -> new InputError(((FieldError) error).getField(), error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
        return null;
    }

}

class InputError {

    private String field;
    private String message;

    public InputError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

}