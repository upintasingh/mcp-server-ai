# Java MCP Hello Server

A simple MCP (Model Context Protocol) server built using the official Java MCP SDK.

This project demonstrates how to:

* Create an MCP server using Java 21
* Register custom tools
* Expose tools over STDIO transport
* Test tools using the MCP Inspector
* Return responses from tool handlers

## Prerequisites

* Java 21+
* Maven 3.9+
* Node.js (for MCP Inspector)

## Dependencies

* MCP Java SDK 1.0.0
* Jackson
* SLF4J Simple Logger

## Project Structure

```text
src/
└── main/
    └── java/
        └── com/example/
            └── HelloMcpServer.java
```

## Build

```bash
mvn clean package
```

The build generates:

```text
target/mcp-hello.jar
```

## Run

```bash
java -jar target/mcp-hello.jar
```

When the server starts, it publishes MCP notifications and waits for client requests over STDIO.

## Available Tool

### say Hello

Greets a user by name.

#### Input

```json
{
  "name": "Upinta"
}
```

#### Output

```text
Hello, Upinta! Greetings from your Java MCP server!
```

## Testing with MCP Inspector

Start MCP Inspector:

```bash
npx @modelcontextprotocol/inspector
```

Configure:

### Transport

```text
STDIO
```

### Command

```text
java
```

### Arguments

```text
-jar E:/mcp/untitled/target/mcp-hello.jar
```

Connect to the server.

Navigate to:

```text
Tools → say Hello
```

Provide input:

```json
{
  "name": "Upinta"
}
```

Expected response:

```text
Hello, Upinta! Greetings from your Java MCP server!
```

## Technologies Used

* Java 21
* Maven
* MCP Java SDK 1.0.0
* Jackson
* SLF4J
* MCP Inspector

## Learning Goals

This project serves as a starting point for learning:

* MCP Tools
* MCP Client/Server communication
* JSON Schema-based tool definitions
* STDIO transport
* MCP Inspector

## Future Enhancements

* Calculator Tool
* Weather Tool
* Spring Boot Integration
* MCP Resources
* MCP Prompts
* External API Integration

## References

* Model Context Protocol (MCP)
* MCP Java SDK
* MCP Inspector

## License

MIT License
