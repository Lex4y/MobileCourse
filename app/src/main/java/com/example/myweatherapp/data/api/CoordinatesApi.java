// CoordinatesApi.java
package com.example.myweatherapp.data.api;

import com.example.myweatherapp.data.model.Coordinates;

public interface CoordinatesApi {
    void fetchCoordinates(String cityName, CoordinatesCallback callback);

    interface CoordinatesCallback {
        void onSuccess(Coordinates coordinates);
        void onError(String error);
    }
}