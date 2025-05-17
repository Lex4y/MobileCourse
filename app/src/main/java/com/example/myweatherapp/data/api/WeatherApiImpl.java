package com.example.myweatherapp.data.api;

import com.example.myweatherapp.data.model.Weather;

import okhttp3.*;
import org.json.*;
import java.io.IOException;

public class WeatherApiImpl implements WeatherApi {
    private final OkHttpClient client;

    public WeatherApiImpl(OkHttpClient client) {
        this.client = client;
    }

    @Override
    public void fetchWeather(double latitude, double longitude, WeatherCallback callback) {
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude
                + "&longitude=" + longitude
                + "&hourly=temperature_2m&forecast_hours=1";

        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onError("Request failed: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String responseData = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONObject hourly = jsonObject.getJSONObject("hourly");
                    JSONArray temperatures = hourly.getJSONArray("temperature_2m");

                    if (temperatures.length() > 0) {
                        double temperature = temperatures.getDouble(0);
                        callback.onSuccess(new Weather(temperature, null, null));
                    } else {
                        callback.onError("Temperature data not found");
                    }
                } catch (JSONException e) {
                    callback.onError("JSON parsing error: " + e.getMessage());
                }
            }
        });
    }
}