package com.codespark.springbootbasics.restconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SimpleRestConsumer {

	private static final Logger log = LoggerFactory.getLogger(SimpleRestConsumer.class);

	// Open Weather REST API credentials
	private static final String API_KEY = "a4ddcd75dbd6ebec8b0ed20a1247c7ff";
	private static final String UNITS = "metric";
	private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=%s";

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Gets weather data for a location after consuming service from an external
	 * RESTful API.
	 * 
	 * To directly bind your data to your custom types, you need to specify the
	 * variable name to be exactly the same as the key in the JSON document returned
	 * from the API. In case your variable name and key in JSON doc do not match,
	 * you can use @JsonProperty annotation to specify the exact key of the JSON
	 * document.
	 * 
	 * @param location Location
	 * @return Weather data
	 */
	public ResponseEntity<WeatherData> getWeatherData(String location) {
		String API_URL = String.format(URL, location, API_KEY, UNITS);

		/**
		 * Consume a REST web service programmatically, make GET request and bind
		 * response to custom domain object i.e WeatherData class.
		 */
		WeatherData weatherData = (WeatherData) restTemplate.getForObject(API_URL, WeatherData.class);

		log.info(String.format("Collected weather info for location %s", weatherData.getName()));
		log.info(weatherData.toString());

		return new ResponseEntity<WeatherData>(weatherData, HttpStatus.OK);
	}

}
