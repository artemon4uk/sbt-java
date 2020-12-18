package ru.sbt.course.services;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbt.course.entities.Weather;

import java.util.List;

@Service
public class PredictService {
    private final static int N_DAYS = 7;

    private final WeatherService weatherService;
    private final CurrencyService currencyService;
    private final SimpleRegression simpleRegression;

    @Autowired
    public PredictService(WeatherService weatherService, CurrencyService currencyService) {
        this.weatherService = weatherService;
        this.currencyService = currencyService;
        this.simpleRegression = new SimpleRegression();
    }

    public void fit() {
        List<Weather> weatherList = weatherService.getHistoryWeather(N_DAYS);
        List<Double> currencies = currencyService.getNLatestCurrency(N_DAYS);
        for (int i = 0; i < Math.min(weatherList.size(), currencies.size()); ++i) {
            simpleRegression.addData(weatherList.get(i).getAvgTemperature(), currencies.get(i));
        }
    }

    public double predict() {
        Weather weather = weatherService.getForecastWeather();
        return predictCurrency(weather);
    }

    public double predict(String city) {
        Weather weather = weatherService.getForecastWeather(city);
        return predictCurrency(weather);
    }

    private double predictCurrency(Weather weather) {
        return simpleRegression.predict(weather.getAvgTemperature());
    }
}
