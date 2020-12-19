package ru.sbt.course.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sbt.course.entities.Weather;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WeatherServiceTest {
    @Autowired
    private WeatherService weatherService;

    @Test
    public void getHistoryWeatherWithCity() {
        int n = 3;
        List<Weather> weatherList = weatherService.getHistoryWeather(n, "London");
        assertEquals(weatherList.size(), n);
        for (Weather weather : weatherList) {
            assertEquals(weather.getCity(), "London");
        }
    }

    @Test
    public void getHistoryWeatherWithoutCity() {
        int n = 3;
        List<Weather> weatherList = weatherService.getHistoryWeather(n);
        assertEquals(weatherList.size(), n);
        for (Weather weather : weatherList) {
            assertEquals(weather.getCity(), "Moscow");
        }
    }

    @Test
    public void getForecastWeatherWithCity() {
        String city = "London";
        Weather weather = weatherService.getForecastWeather(city);
        assertEquals(weather.getCity(), city);
        assertEquals(weather.getDate(), LocalDate.now().plusDays(1));
    }

    @Test
    public void getForecastWeatherWithoutCity() {
        Weather weather = weatherService.getForecastWeather();
        assertEquals(weather.getCity(), "Moscow");
        assertEquals(weather.getDate(), LocalDate.now().plusDays(1));
    }
}