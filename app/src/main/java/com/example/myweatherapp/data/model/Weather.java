// Weather.java
package com.example.myweatherapp.data.model;

public class Weather {
    private final double temperature;
    private final Double humidity;
    private final Double windSpeed;

    public Weather(double temperature, Double humidity, Double windSpeed) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    // Getters
    public double getTemperature() { return temperature; }
    public Double getHumidity() { return humidity; }
    public Double getWindSpeed() { return windSpeed; }
}