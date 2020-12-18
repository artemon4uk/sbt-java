package ru.sbt.course.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sbt.course.entities.Weather;
import ru.sbt.course.repositories.WeatherRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final WeatherRepository weatherRepository;
    private static final String API_KEY = "6b18f848502f4a6588293139201512";

    @Autowired
    public WeatherService(RestTemplateBuilder restTemplateBuilder,
                          ObjectMapper mapper,
                          WeatherRepository weatherRepository) {
        restTemplate = restTemplateBuilder.build();
        objectMapper = mapper;
        this.weatherRepository = weatherRepository;
    }

    public List<Weather> getHistoryWeather(int n, String city) {
        return getHistoryWeatherData(n, city);
    }

    public List<Weather> getHistoryWeather(int n) {
        return getHistoryWeatherData(n, "Moscow");
    }

    public Weather getForecastWeather(String city) {
        return getForecastWeatherData(city);
    }

    public Weather getForecastWeather() {
        return getForecastWeatherData("Moscow");
    }

    private List<Weather> getHistoryWeatherData(int n, String city) {
        LocalDate today = LocalDate.now();
        List<Weather> weatherList = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            LocalDate day = today.minusDays(i);
            Optional<Weather> weatherFromRepo = weatherRepository.findByCityAndDate(city, day);
            if (weatherFromRepo.isPresent()) {
                System.out.println("Weather is taken from BD");
                weatherList.add(weatherFromRepo.get());
            }
            else {
                System.out.println("Weather is taken from response");
                ResponseEntity<String> response = restTemplate.getForEntity(getUrlForHistory(city, day), String.class);
                Weather weather = getWeather(response, city, day);
                weatherList.add(weather);
                weatherRepository.save(weather);
            }
        }
        return weatherList;
    }

    private Weather getForecastWeatherData(String city) {
        ResponseEntity<String> response = restTemplate.getForEntity(getUrlForForecast(city), String.class);
        return getWeather(response, city, LocalDate.now().plusDays(1));
    }

    private Weather getWeather(ResponseEntity<String> response, String city, LocalDate date) {
        JsonNode json = null;
        try {
            json = objectMapper.readTree(response.getBody());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        json = json.get("forecast").get("forecastday").get(0).get("day");
        double minTemperature = json.get("mintemp_c").asDouble();
        double maxTemperature = json.get("maxtemp_c").asDouble();
        double avgTemperature = json.get("avgtemp_c").asDouble();
        return new Weather(city, date, minTemperature, maxTemperature, avgTemperature);
    }

    private String getUrlForHistory(String city, LocalDate date) {
        return "http://api.weatherapi.com/v1/history.json?key=" + API_KEY + "&q=" + city + "&dt=" +
                date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private String getUrlForForecast(String city) {
        return "http://api.weatherapi.com/v1/forecast.json?key=" + API_KEY + "&q=" + city + "&days=1";
    }
}
