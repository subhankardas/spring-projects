package com.codespark.springbootbasics.restfulweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This code uses Spring @RestController annotation, which marks the class as a
 * controller where every method returns a domain object instead of a view. It
 * is shorthand for including both @Controller and @ResponseBody.
 * 
 * The @ResponseBody annotation tells a controller that the object returned is
 * automatically serialized into JSON and passed back into the HttpResponse
 * object.
 * 
 * Spring’s MappingJackson2HttpMessageConverter is automatically chosen to
 * convert the response instance to JSON.
 */
@RestController
@RequestMapping("/api")
public class SimpleRestController {

	/**
	 * Simple REST end point that gets request parameters from query string and
	 * returns JSON response.
	 * 
	 * @param userId User ID
	 * @return User
	 */
	@GetMapping("/users")
	public ResponseModel getUser1(@RequestParam(name = "id", defaultValue = "1") int userId) {
		ResponseModel response = new ResponseModel();
		response.setUserId(userId);
		response.setUsername("Test User 1");

		return response;
	}

	/**
	 * Simple REST end point that gets request parameters from request body and
	 * returns JSON response.
	 * 
	 * @param userId User ID
	 * @return User
	 */
	@GetMapping("/user")
	public ResponseModel getUser2(@RequestBody RequestModel request) {
		ResponseModel response = new ResponseModel();
		response.setUserId(request.getUserId());
		response.setUsername("Test User 2");

		return response;
	}

}
