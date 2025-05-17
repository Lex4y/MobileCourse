// WeatherViewModel.java
package com.example.myweatherapp.ui.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.myweatherapp.data.api.*;
import com.example.myweatherapp.data.model.Coordinates;
import com.example.myweatherapp.data.model.Weather;

public class WeatherViewModel extends ViewModel {
    private final CoordinatesApi coordinatesApi;
    private final WeatherApi weatherApi;

    public WeatherViewModel(CoordinatesApi coordinatesApi, WeatherApi weatherApi) {
        this.coordinatesApi = coordinatesApi;
        this.weatherApi = weatherApi;
    }

    public void fetchWeather(String city, WeatherFetchCallback callback) {
        coordinatesApi.fetchCoordinates(city, new CoordinatesApi.CoordinatesCallback() {
            @Override
            public void onSuccess(Coordinates coordinates) {
                weatherApi.fetchWeather(
                        coordinates.getLatitude(),
                        coordinates.getLongitude(),
                        new WeatherApi.WeatherCallback() {
                            @Override
                            public void onSuccess(Weather weather) {
                                callback.onSuccess(weather);
                            }

                            @Override
                            public void onError(String error) {
                                callback.onError(error);
                            }
                        }
                );
            }

            @Override
            public void onError(String error) {
                callback.onError(error);
            }
        });
    }

    public interface WeatherFetchCallback {
        void onSuccess(Weather weather);
        void onError(String error);
    }
}