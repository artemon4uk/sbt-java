package ru.sbt.course.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
class WeatherControllerTest {
    @Autowired
    private WeatherController weatherController;

    @Test
    public void getWeatherHistoryWithoutCity() throws Exception {
        MockMvc mockMvc = standaloneSetup(weatherController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/weather?n=3"))
                .andExpect(status().isOk());
    }

    @Test
    public void getWeatherHistoryWithCity() throws Exception {
        MockMvc mockMvc = standaloneSetup(weatherController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/weather?n=3&city=London"))
                .andExpect(status().isOk());
    }
}