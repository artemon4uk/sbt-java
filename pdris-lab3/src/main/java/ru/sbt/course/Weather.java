package ru.sbt.course;

public class Weather {
    private final double maxTemperature;
    private final double minTemperature;
    private final double avgTemperature;

    public Weather(double minTemperature, double maxTemperature, double avgTemperature) {
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
}
