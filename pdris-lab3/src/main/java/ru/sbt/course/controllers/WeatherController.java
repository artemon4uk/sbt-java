package ru.sbt.course.controllers;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.course.Weather;
import ru.sbt.course.services.WeatherService;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public List<Weather> getWeatherHistory(@RequestParam @Min(1) int n, @RequestParam @Nullable String city) {
        if (city == null) {
            return weatherService.getHistoryWeather(n);
        }
        return weatherService.getHistoryWeather(n, city);
    }
}
