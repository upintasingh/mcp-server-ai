package com.example.prompts;

import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.spec.McpSchema;

import java.util.List;

public class GreetUserPrompt {
    public static McpServerFeatures.SyncPromptSpecification create() {
        McpSchema.Prompt prompt = new McpSchema.Prompt(
                "greet-user",
                "Generate a greeting prompt",
                List.of(
                        new McpSchema.PromptArgument(
                                "name",
                                "Name of the user",
                                true
                        )
                )
        );

        return new McpServerFeatures.SyncPromptSpecification(
                prompt,
                (exchange, request) -> {

                    String name =
                            request.arguments().get("name").toString();

                    String promptText = """
                            You are a friendly AI assistant.
                            Greet %s warmly and ask how their MCP learning journey is going.
                            Keep the response short and encouraging.
                            """.formatted(name);

                    return new McpSchema.GetPromptResult(
                            "Greeting Prompt",
                            List.of(
                                    new McpSchema.PromptMessage(
                                            McpSchema.Role.USER,
                                            new McpSchema.TextContent(promptText)
                                    )
                            )
                    );
                }
        );
    }
}
