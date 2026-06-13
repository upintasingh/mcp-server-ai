package com.example.resources;

import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.spec.McpSchema;

import java.util.List;

public class ProjectResourceTemplate {

    public static McpServerFeatures.SyncResourceTemplateSpecification create() {
        McpSchema.ResourceTemplate template =
                new McpSchema.ResourceTemplate(
                        "project://{projectName}",
                        "Project Information",
                        "Returns information about a project",
                        "application/json",
                        null
                );

        return new McpServerFeatures.SyncResourceTemplateSpecification(
                template,
                (exchange, request) -> {

                    String uri = request.uri();

                    String projectName =
                            uri.replace("project://", "");

                    String content = """
                            {
                              "project": "%s",
                              "status": "active",
                              "owner": "upinta"
                            }
                            """.formatted(projectName);

                    return new McpSchema.ReadResourceResult(
                            List.of(
                                    new McpSchema.TextResourceContents(
                                            uri,
                                            "application/json",
                                            content
                                    )
                            )
                    );
                }
        );

    }
}
