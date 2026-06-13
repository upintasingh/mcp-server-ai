package com.example.tools;

import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.spec.McpSchema;

import java.util.List;
import java.util.Map;

public class MathTool {
    public static McpServerFeatures.SyncToolSpecification create() {
        McpSchema.JsonSchema inputSchema = new McpSchema.JsonSchema("object", Map.of(
                "a", Map.of("type", "number",
                        "description", "First number"),
                "b", Map.of("type", "number",
                        "description", "second number"),
                "operation", Map.of("type", "String", "description", "Operation: add, subtract, multiply, divide")
        ), List.of(), false, null, null);

        McpSchema.Tool mathTool = new McpSchema.Tool("math", "math tool", "Perform mathematical operations",
                inputSchema,
                null,
                null,
                null);

        return new McpServerFeatures.SyncToolSpecification(mathTool, (exchange, request) -> {
            Number a = (Number) request.arguments().get("a");
            Number b = (Number) request.arguments().get("b");
            String operation =
                    request.arguments().get("operation").toString();
            double first = a.doubleValue();
            double second = b.doubleValue();
            double result;

            switch (operation.toLowerCase()) {

                case "add":
                    result = first + second;
                    break;

                case "subtract":
                    result = first - second;
                    break;

                case "multiply":
                    result = first * second;
                    break;

                case "divide":

                    if (second == 0) {
                        return McpSchema.CallToolResult.builder()
                                .content(List.of(
                                        new McpSchema.TextContent(
                                                "Division by zero is not allowed"
                                        )
                                ))
                                .isError(true)
                                .build();
                    }

                    result = first / second;
                    break;

                default:
                    return McpSchema.CallToolResult.builder()
                            .content(List.of(
                                    new McpSchema.TextContent(
                                            "Unsupported operation: " + operation
                                    )
                            ))
                            .isError(true)
                            .build();
            }
            String response = String.format(
                    "Result of %s %.2f and %.2f = %.2f",
                    operation,
                    first,
                    second,
                    result
            );
            return McpSchema.CallToolResult.builder()
                    .content(
                            List.of(
                                    new McpSchema.TextContent(response)
                            )
                    )
                    .isError(false)
                    .build();
        }

        );
    }
}
