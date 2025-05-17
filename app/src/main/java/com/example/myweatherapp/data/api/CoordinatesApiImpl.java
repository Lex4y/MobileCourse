// CoordinatesApiImpl.java
package com.example.myweatherapp.data.api;

import com.example.myweatherapp.data.model.Coordinates;

import okhttp3.*;
import org.json.*;
import java.io.IOException;

public class CoordinatesApiImpl implements CoordinatesApi {
    private static final String API_KEY = "ziZmgl75E7ZATXiSlduERw==4CppXGM88FviqpQK";
    private final OkHttpClient client;

    public CoordinatesApiImpl(OkHttpClient client) {
        this.client = client;
    }


    @Override
    public void fetchCoordinates(String cityName, CoordinatesCallback callback) {
        Request request = new Request.Builder()
                .url("https://api.api-ninjas.com/v1/city?name=" + cityName)
                .addHeader("X-Api-Key", API_KEY)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    JSONArray jsonArray = new JSONArray(jsonData);
                    if (jsonArray.length() > 0) {
                        JSONObject city = jsonArray.getJSONObject(0);
                        Coordinates coordinates = new Coordinates(
                                city.getDouble("latitude"),
                                city.getDouble("longitude")
                        );
                        callback.onSuccess(coordinates);
                    } else {
                        callback.onError("City not found");
                    }
                } catch (JSONException e) {
                    callback.onError("JSON parsing error CoordinatesAPI");
                }
            }
        });
    }
}