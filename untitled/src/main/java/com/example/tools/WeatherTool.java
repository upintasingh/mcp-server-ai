package com.example.tools;

import com.example.Service.WeatherService;
import com.example.models.WeatherResponse;
import io.modelcontextprotocol.client.McpAsyncClient;
import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.spec.McpSchema;

import java.util.List;
import java.util.Map;

public class WeatherTool {
    private static final WeatherService weatherService =
            new WeatherService();

    public static McpServerFeatures.SyncToolSpecification create() {

        McpSchema.JsonSchema inputSchema = new McpSchema.JsonSchema("object", Map.of(
                "latitude", Map.of("type", "number",
                        "description", "Latitude of the location"),
                "longitude", Map.of("type", "number",
                        "description", "Longitude of the location")

        ), List.of("latitude", "longitude"), false, null, null);

        McpSchema.Tool weatherTool = new McpSchema.Tool("weather", "weather tool", "get current weater information", inputSchema, null, null, null);

        return new McpServerFeatures.SyncToolSpecification(weatherTool,
                (exchange, request) -> {
            Number latitude = (Number) request.arguments().get("latitude");
            Number longitude = (Number) request.arguments().get("longitude");
                    try {
                        WeatherResponse weatherResponse = weatherService.getWeather(latitude.doubleValue(), longitude.doubleValue());

                        String response = String.format(
                                """
                                Current Weather
                                Temperature : %.1f °C
                                Wind Speed  : %.1f km/h
                                Wind Direction : %d°
                                Weather Code : %d
                                """,
                                weatherResponse.temperature(),
                                weatherResponse.windSpeed(),
                                weatherResponse.winddirection(),
                                weatherResponse.weathercode()
                        );

                        return McpSchema.CallToolResult.builder()
                                .content(List.of(new McpSchema.TextContent(response)))
                                .isError(false)
                                .build();
                    } catch (Exception e) {
                        return McpSchema.CallToolResult.builder()
                                .content(
                                        List.of(
                                                new McpSchema.TextContent(
                                                        "Unable to fetch weather: "
                                                                + e.getMessage()
                                                )
                                        )
                                )
                                .isError(true)
                                .build();
                    }
                }
        );

    }



}
