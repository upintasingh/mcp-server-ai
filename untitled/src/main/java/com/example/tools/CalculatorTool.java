package com.example.tools;

import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.spec.McpSchema;

import java.util.List;
import java.util.Map;

public class CalculatorTool {
    public static McpServerFeatures.SyncToolSpecification create() {
        //make scehma
        McpSchema.JsonSchema inputSchema = new McpSchema.JsonSchema(
                "object",
                Map.of(
                        "a", Map.of(
                                "type", "number",
                                "description", "First number"
                        ),
                        "b", Map.of(
                                "type", "number",
                                "description", "Second number"
                        )
                ),
                List.of("a", "b"),
                false,
                null,
                null
        );

        McpSchema.Tool calculatorTool = new McpSchema.Tool(
                "calculator",
                "calculator tool",
                "Adds two numbers",
                inputSchema,
                null,
                null,
                null
        );

        return new McpServerFeatures.SyncToolSpecification(
                calculatorTool,
                (exchange, request) -> {
                    // request.arguments() is a Map<String, Object>
                    double a = ((Number) request.arguments().get("a")).doubleValue();
                    double b = ((Number) request.arguments().get("b")).doubleValue();
                    double sum = a + b;

                    // Use the builder — not a direct constructor
                    return McpSchema.CallToolResult.builder()
                            .content(List.of(new McpSchema.TextContent("The sum of " + a + " and " + b + " is " + sum)))
                            .isError(false)
                            .build();
                }
        );

    }
}
