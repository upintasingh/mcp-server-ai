package com.example.server;

import com.example.tools.AsyncHelloTool;
import com.example.tools.AsyncWeatherTool;
import io.modelcontextprotocol.json.jackson3.JacksonMcpJsonMapper;
import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.server.transport.StdioServerTransportProvider;
import io.modelcontextprotocol.spec.McpSchema;
import tools.jackson.databind.json.JsonMapper;

public class McpAsyncServer {

    public static void main(String[] args) throws Exception {
        var jsonMapper = new JacksonMcpJsonMapper(
                        JsonMapper.builder().build());

        var transportProvider =
                new StdioServerTransportProvider(jsonMapper);

        io.modelcontextprotocol.server.McpAsyncServer server =
                McpServer.async(transportProvider)
                        .serverInfo(
                                "hello-server",
                                "1.0.0"
                        )
                        .capabilities(
                                McpSchema.ServerCapabilities.builder()
                                        .tools(true)
                                        .resources(true, true)
                                        .prompts(true)
                                        .build()
                        )
                        .build();

        System.out.println("Creating async server...");

        var tool = AsyncWeatherTool.create();

        System.out.println("Tool = " + tool);

        server.addTool(tool);

        System.out.println("Tool registered successfully");
        var toll2 = AsyncHelloTool.create();
        System.out.println("Tool = " + tool);
        server.addTool(toll2);
        System.out.println("Async weather tool registered.");
        System.out.println(server.listTools());
        Thread.currentThread().join();
    }

}
