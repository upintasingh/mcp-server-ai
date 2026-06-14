package com.example.Service;

import com.example.models.OpenMeteoResponse;
import com.example.models.WeatherResponse;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class WeatherService {
    private final HttpClient httpClient = HttpClient.newHttpClient();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public WeatherResponse getWeather(Double latitude, Double longitude) throws Exception {
        String url =
                """
                https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&current_weather=true
                """
                        .formatted(latitude, longitude)
                        .replace("\n", "");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        OpenMeteoResponse weatherResponse = objectMapper.readValue(
                response.body(),
                OpenMeteoResponse.class
        );

        return new WeatherResponse(
                weatherResponse.currentWeather().temperature(),
                weatherResponse.currentWeather().windspeed(),
                weatherResponse.currentWeather().winddirection(),
                weatherResponse.currentWeather().weathercode()
        );
    }

    public CompletableFuture<WeatherResponse> getWeatherAsync(
            Double latitude,
            Double longitude) {

        String url = """
            https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&current_weather=true
            """
                .formatted(latitude, longitude)
                .trim();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        return httpClient.sendAsync(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                )
                .thenApply(HttpResponse::body)
                .thenApply(body -> {
                    try {
                        OpenMeteoResponse weatherResponse =
                                objectMapper.readValue(
                                        body,
                                        OpenMeteoResponse.class
                                );

                        return new WeatherResponse(
                                weatherResponse.currentWeather().temperature(),
                                weatherResponse.currentWeather().windspeed(),
                                weatherResponse.currentWeather().winddirection(),
                                weatherResponse.currentWeather().weathercode()
                        );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
