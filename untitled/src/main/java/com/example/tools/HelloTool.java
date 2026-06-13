package com.example.tools;

import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.spec.McpSchema;

import java.util.List;
import java.util.Map;

public class HelloTool {

    public static McpServerFeatures.SyncToolSpecification create() {
        McpSchema.JsonSchema inputSchema = new McpSchema.JsonSchema("object",
                Map.of("name", Map.of(
                        "type",        "string",
                        "description", "The person's name to greet"
                )), List.of("name"), false, null, null);

        McpSchema.Tool sayHelloTool = new McpSchema.Tool("say Hello", "hello tool", "Greet a person by name", inputSchema, null, null, null);

        return new McpServerFeatures.SyncToolSpecification(
                sayHelloTool,
                (exchange, request) -> {
                    // request.arguments() is a Map<String, Object>
                    String personName = (String) request.arguments().get("name");
                    String greeting = "Hello, " + personName
                            + "! Greetings from your Java MCP server!";

                    // Use the builder — not a direct constructor
                    return McpSchema.CallToolResult.builder()
                            .content(List.of(new McpSchema.TextContent(greeting)))
                            .isError(false)
                            .build();
                }
        );


    }
}
