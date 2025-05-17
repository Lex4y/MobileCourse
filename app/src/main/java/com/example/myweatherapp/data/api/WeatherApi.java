// WeatherApi.java
package com.example.myweatherapp.data.api;

import com.example.myweatherapp.data.model.Weather;

public interface WeatherApi {
    void fetchWeather(double latitude, double longitude, WeatherCallback callback);

    interface WeatherCallback {
        void onSuccess(Weather weather);
        void onError(String error);
    }
}