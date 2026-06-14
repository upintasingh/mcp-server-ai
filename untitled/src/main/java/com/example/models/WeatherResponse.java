package com.example.models;

public record WeatherResponse(double temperature,
                              double windSpeed,
                              int winddirection,
                              int weathercode) {
}
