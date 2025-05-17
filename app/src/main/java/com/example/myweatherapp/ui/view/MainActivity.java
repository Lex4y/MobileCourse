// MainActivity.java
package com.example.myweatherapp.ui.view;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myweatherapp.R;
import com.example.myweatherapp.data.api.*;
import com.example.myweatherapp.data.model.Weather;
import com.example.myweatherapp.ui.viewmodel.WeatherViewModel;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    private EditText inputCity;
    private TextView temperatureText;
    private WeatherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputCity = findViewById(R.id.inputcity);
        temperatureText = findViewById(R.id.temperature);
        Button fetchButton = findViewById(R.id.buttonfind);

        // Инициализация ViewModel
        OkHttpClient client = new OkHttpClient();
        viewModel = new WeatherViewModel(
                new CoordinatesApiImpl(client),
                new WeatherApiImpl(client)
        );

        fetchButton.setOnClickListener(v -> {
            String city = inputCity.getText().toString();
            if (city.isEmpty()) {
                Toast.makeText(this, "Please enter a city", Toast.LENGTH_SHORT).show();
            } else {
                fetchWeather(city);
            }
        });
    }

    private void fetchWeather(String city) {
        viewModel.fetchWeather(city, new WeatherViewModel.WeatherFetchCallback() {
            @Override
            public void onSuccess(Weather weather) {
                runOnUiThread(() -> {
                    temperatureText.setText(String.valueOf(weather.getTemperature()));
                });
            }

            @Override
            public void onError(String error) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                });
            }
        });
    }
}