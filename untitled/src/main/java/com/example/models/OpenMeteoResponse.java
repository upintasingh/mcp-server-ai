package com.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OpenMeteoResponse(
        @JsonProperty("current_weather")
        CurrentWeather currentWeather) {
}
