package com.example.tools;

import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.spec.McpSchema;
import reactor.core.publisher.Mono;

import java.util.List;

public class AsyncHelloTool {
    public static McpServerFeatures.AsyncToolSpecification create() {

        McpSchema.Tool tool =
                new McpSchema.Tool(
                        "async-hello",
                        "async hello",
                        "Test async tool",
                        null,
                        null,
                        null,
                        null
                );

        return new McpServerFeatures.AsyncToolSpecification(
                tool,
                (exchange, request) ->
                        Mono.just(
                                McpSchema.CallToolResult.builder()
                                        .content(List.of(
                                                new McpSchema.TextContent("Hello Async")
                                        ))
                                        .isError(false)
                                        .build()
                        )
        );
    }
}
