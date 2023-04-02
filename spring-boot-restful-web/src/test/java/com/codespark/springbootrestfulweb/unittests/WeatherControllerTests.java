package com.codespark.springbootrestfulweb.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.codespark.springbootrestfulweb.restconsumer.RestConsumer;
import com.codespark.springbootrestfulweb.restconsumer.WeatherController;
import com.codespark.springbootrestfulweb.restconsumer.WeatherDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = { WeatherController.class })
public class WeatherControllerTests {

    // Dependencies
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Mock dependencies
    @MockBean
    private RestConsumer restConsumer;

    // Mock data
    private static final int PORT = 8080;
    private static final long MOCK_ID = 123L;
    private static final String MOCK_LOCATION = "London";
    private WeatherDTO mockWeatherDTO;

    private static final String GET_WEATHER_DATA_URL = "http://localhost:%d/api/v1/weather?location=%s";

    @BeforeEach
    public void setup() {
        mockWeatherDTO = new WeatherDTO();
        mockWeatherDTO.setId(MOCK_ID);
        mockWeatherDTO.setName(MOCK_LOCATION);
    }

    @Test
    public void assertWeatherControllerGetWeatherForLocation() throws Exception {
        // Given
        // Mock dependency service method call to return mock data
        when(restConsumer.getWeatherDTO(MOCK_LOCATION)).thenReturn(mockWeatherDTO);

        // When
        // Perform GET request to controller endpoint, map response to WeatherDTO
        MvcResult result = mockMvc.perform(get(String.format(GET_WEATHER_DATA_URL, PORT, MOCK_LOCATION)))
                            .andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        WeatherDTO weatherDTO = objectMapper.readValue(response, WeatherDTO.class);

        // Then
        assertNotNull(weatherDTO);
        assertEquals(MOCK_ID, weatherDTO.getId());
        assertEquals(MOCK_LOCATION, weatherDTO.getName());
    }

}
