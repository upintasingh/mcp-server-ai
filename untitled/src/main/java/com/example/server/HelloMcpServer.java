package com.example.server;

import com.example.prompts.GreetUserPrompt;
import com.example.resources.ProjectResourceTemplate;
import com.example.resources.ServerInfoResource;
import com.example.tools.CalculatorTool;
import com.example.tools.HelloTool;
import com.example.tools.MathTool;
import com.example.tools.WeatherTool;
import io.modelcontextprotocol.json.jackson3.JacksonMcpJsonMapper;
import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.server.McpSyncServer;
import io.modelcontextprotocol.server.transport.StdioServerTransportProvider;
import io.modelcontextprotocol.spec.McpSchema;
import tools.jackson.databind.json.JsonMapper;


public class HelloMcpServer {
    /*public static void main(String[] args) throws InterruptedException {
        McpSchema.JsonSchema inputSchema = new McpSchema.JsonSchema("object",
                Map.of("name", Map.of(
                        "type",        "string",
                        "description", "The person's name to greet"
                )), List.of("name"), false, null, null);

        McpSchema.Tool sayHelloTool = new McpSchema.Tool("say Hello", "hello tool", "Greet a person by name", inputSchema, null, null, null);


        // ── 3. Pair the tool with its handler lambda ─────────────────────────
        // The lambda runs every time Claude invokes say_hello
        McpServerFeatures.SyncToolSpecification sayHelloSpec =
                new McpServerFeatures.SyncToolSpecification(
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


        // ── 4. Build and start the server ───────────────────────────────────

// Wrap ObjectMapper in JacksonMcpJsonMapper — required in SDK 1.0.0
        var jsonMapper        = new JacksonMcpJsonMapper(JsonMapper.builder().build());
        var transportProvider = new StdioServerTransportProvider(jsonMapper);

        McpSyncServer server = McpServer.sync(transportProvider)
                .serverInfo("hello-server", "1.0.0")
                .capabilities(
                        McpSchema.ServerCapabilities.builder()
                                .tools(true)
                                .build()
                )
                .build();

// Register tool on the live server
        server.addTool(sayHelloSpec);

// ── 5. Keep the process alive ────────────────────────────────────────
        Thread.currentThread().join();


    }*/
    /*public static void main(String[] args) throws InterruptedException {
        var jsonMapper        = new JacksonMcpJsonMapper(JsonMapper.builder().build());
        var transportProvider = new StdioServerTransportProvider(jsonMapper);

        McpSyncServer server = McpServer.sync(transportProvider)
                .serverInfo("hello-server", "1.0.0")
                .capabilities(
                        McpSchema.ServerCapabilities.builder()
                                .tools(true)
                                .resources(true, true)
                                .prompts(true)
                                .build()
                )
                .build();
        server.addTool(HelloTool.create());
        server.addTool(CalculatorTool.create());
        server.addTool(MathTool.create());
        server.addTool(WeatherTool.create());
        server.addResource(ServerInfoResource.create());
        server.addResourceTemplate(ProjectResourceTemplate.create());
        server.addPrompt(GreetUserPrompt.create());
        Thread.currentThread().join();
    }*/
}
