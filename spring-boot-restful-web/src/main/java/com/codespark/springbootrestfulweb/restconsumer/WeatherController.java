package com.codespark.springbootrestfulweb.restconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class WeatherController {

    @Autowired
    private RestConsumer restConsumer;

    /**
     * Example request: GET localhost:8080/api/v1/weather?location=London
     * 
     * @param location Location
     * @return Weather details for the location
     */
    @GetMapping("/weather")
    public WeatherDTO getWeatherForLocation(@RequestParam String location) {
        return restConsumer.getWeatherDTO(location);
    }

}
