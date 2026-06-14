package com.example.tools;

import com.example.Service.WeatherService;
import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.spec.McpSchema;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public class AsyncWeatherTool {
    private static final WeatherService weatherService =
            new WeatherService();

    public static McpServerFeatures.AsyncToolSpecification create() {

        McpSchema.JsonSchema inputSchema =
                new McpSchema.JsonSchema(
                        "object",
                        Map.of(
                                "latitude", Map.of(
                                        "type", "number",
                                        "description", "Latitude"
                                ),
                                "longitude", Map.of(
                                        "type", "number",
                                        "description", "Longitude"
                                )
                        ),
                        List.of("latitude", "longitude"),
                        false,
                        null,
                        null
                );

        McpSchema.Tool weatherTool =
                new McpSchema.Tool(
                        "weather-async",
                        "async weather tool",
                        "Get weather asynchronously",
                        inputSchema,
                        null,
                        null,
                        null
                );

        return new McpServerFeatures.AsyncToolSpecification(
                weatherTool,
                (exchange, request) -> {

                    Number latitude =
                            (Number) request.arguments().get("latitude");

                    Number longitude =
                            (Number) request.arguments().get("longitude");

                    return Mono.fromFuture(
                                    weatherService.getWeatherAsync(
                                            latitude.doubleValue(),
                                            longitude.doubleValue()
                                    )
                            )
                            .map(weather -> {

                                String response = String.format(
                                        """
                                        Current Weather (Async)

                                        Temperature : %.1f °C
                                        Wind Speed  : %.1f km/h
                                        Wind Direction : %d°
                                        Weather Code : %d
                                        """,
                                        weather.temperature(),
                                        weather.windSpeed(),
                                        weather.winddirection(),
                                        weather.weathercode()
                                );

                                return McpSchema.CallToolResult.builder()
                                        .content(List.of(
                                                new McpSchema.TextContent(response)
                                        ))
                                        .isError(false)
                                        .build();
                            })
                            .onErrorResume(ex ->
                                    Mono.just(
                                            McpSchema.CallToolResult.builder()
                                                    .content(List.of(
                                                            new McpSchema.TextContent(
                                                                    "Unable to fetch weather: "
                                                                            + ex.getMessage()
                                                            )
                                                    ))
                                                    .isError(true)
                                                    .build()
                                    )
                            );
                }
        );
    }
}
