package com.codespark.springbootrestfulweb.restconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestConsumer {

    private static final Logger log = LoggerFactory.getLogger(RestConsumer.class);

    // Open Weather REST API data
    private static final String API_KEY = "a4ddcd75dbd6ebec8b0ed20a1247c7ff";
    private static final String UNITS = "metric";
    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=%s";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherDTO getWeatherDTO(String location) {
        String API_URL = String.format(URL, location, API_KEY, UNITS);

        /**
         * Consume a REST web service programmatically, make GET request and bind
         * response to custom domain object i.e WeatherData class.
         */
        WeatherDTO weather = (WeatherDTO) restTemplate.getForObject(API_URL, WeatherDTO.class);
        if (weather == null) {
            log.error(String.format("Could not find weather info for location %s", location));
            return null;
        }

        log.info(String.format("Collected weather info for location %s", location));
        log.info(weather.toString());

        return weather;
    }

}
