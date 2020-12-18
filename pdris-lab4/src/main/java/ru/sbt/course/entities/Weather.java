package ru.sbt.course.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "weather")
public class Weather {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private LocalDate date;
    private String city;
    private double maxTemperature;
    private double minTemperature;
    private double avgTemperature;

    public Weather() {

    }

    public Weather(String city, LocalDate date, double minTemperature, double maxTemperature, double avgTemperature) {
        this.date = date;
        this.city = city;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.avgTemperature = avgTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getAvgTemperature() {
        return avgTemperature;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }
}
