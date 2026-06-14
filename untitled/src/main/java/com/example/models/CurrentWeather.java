package com.example.models;

public record CurrentWeather(double temperature,
                             double windspeed,
                             int winddirection,
                             int weathercode) {
}
