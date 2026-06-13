package com.example.resources;

import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.spec.McpSchema;

import java.util.List;

public class ServerInfoResource {
    public static  McpServerFeatures.SyncResourceSpecification create() {
        McpSchema.Resource resource = new McpSchema.Resource(
                "server://info",
                "Server Information",
                "Information about this MCP server",
                "application/json",
                null,null,null,null);

        return new McpServerFeatures.SyncResourceSpecification(
                resource,
                (exchange, request) -> {

                    String content = """
                            {
                              "serverName": "hello-server",
                              "version": "1.0.0",
                              "tools": [
                                "hello",
                                "calculator",
                                "math"
                              ]
                            }
                            """;

                    return new McpSchema.ReadResourceResult(
                            List.of(
                                    new McpSchema.TextResourceContents(
                                            "server://info",
                                            "application/json",
                                            content
                                    )
                            )
                    );
                }
        );
    }

}
