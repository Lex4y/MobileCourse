// Coordinates.java
package com.example.myweatherapp.data.model;

public class Coordinates {
    private final double latitude;
    private final double longitude;

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}