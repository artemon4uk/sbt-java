package ru.sbt.course.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WeatherTest {
    private static Weather weather;
    private final static String city = "London";
    private final static LocalDate date = LocalDate.of(2020, 10, 15);
    private final static double minTemperature = 0.0;
    private final static double maxTemperature = 20.0;
    private final static double avgTemperature = 10.0;

    @BeforeAll
    public static void setUp() {
        weather = new Weather(city, date, minTemperature, maxTemperature, avgTemperature);
    }

    @Test
    public void getMaxTemperature() {
        assertEquals(weather.getMaxTemperature(), maxTemperature);
    }

    @Test
    public void getMinTemperature() {
        assertEquals(weather.getMinTemperature(), minTemperature);
    }

    @Test
    public void getAvgTemperature() {
        assertEquals(weather.getAvgTemperature(), avgTemperature);
    }

    @Test
    public void getDate() {
        assertEquals(weather.getDate(), date);
    }

    @Test
    public void getCity() {
        assertEquals(weather.getCity(), city);
    }
}