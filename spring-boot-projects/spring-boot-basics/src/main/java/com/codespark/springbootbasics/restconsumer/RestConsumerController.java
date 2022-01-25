package com.codespark.springbootbasics.restconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestConsumerController {

	@Autowired
	private SimpleRestConsumer restConsumer;

	/**
	 * Simple end point that consumes an external REST service to fetch weather data
	 * for a specified location as parameter.
	 * 
	 * @param location Location name
	 * @return Weather data
	 */
	@GetMapping("/weather")
	public ResponseEntity<WeatherData> getWeatherData(
			@RequestParam(name = "location", defaultValue = "Asansol") String location) {
		return restConsumer.getWeatherData(location);
	}

}
