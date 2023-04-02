package com.codespark.springbootrestfulweb.restservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This code uses Spring '@RestController' annotation, which marks the class as
 * a controller where every method returns a domain object instead of a view. It
 * is shorthand for including both '@Controller' and '@ResponseBody'.
 * 
 * The '@ResponseBody' annotation tells a controller that the object returned is
 * automatically serialized into JSON and passed back into the HttpResponse
 * object.
 * 
 * Spring’s MappingJackson2HttpMessageConverter is automatically chosen to
 * convert the response instance to JSON.
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {

    /**
     * Simple REST end point that gets request parameters from query string and
     * returns JSON response.
     * 
     * Example request: GET localhost:8080/api/v1/users?id=1
     * 
     * @param userId User ID
     * @return User details with ID and name
     */
    @GetMapping("/users")
    public ResponseEntity<User> getUserById(@RequestParam(name = "id", defaultValue = "0") int userId) {
        switch (userId) {
            case 1:
                return ResponseEntity.status(HttpStatus.OK).body(new User(1, "John Doe"));
            case 2:
                return ResponseEntity.status(HttpStatus.OK).body(new User(2, "Jane Doe"));
            default:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
