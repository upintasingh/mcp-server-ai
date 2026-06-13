# MCP Server AI (Java)

A hands-on Java implementation of a Model Context Protocol (MCP) Server built using the MCP Java SDK.

This project was created to learn MCP concepts from the ground up, starting with simple tools and gradually adding resources, resource templates, and prompts.

## Features

### Tools

#### Hello Tool

Greets a user by name.

Example Input:

```json
{
  "name": "Upinta"
}
```

Example Output:

```text
Hello, Upinta! Greetings from your Java MCP server!
```

---

#### Math Tool

Supports the following operations:

* add
* subtract
* multiply
* divide

Example Input:

```json
{
  "a": 10,
  "b": 5,
  "operation": "multiply"
}
```

Example Output:

```text
Result of multiply 10.00 and 5.00 = 50.00
```

Includes MCP-compliant error handling using `isError=true`.

---

### Resources

#### server://info

Returns information about the MCP server.

Example Response:

```json
{
  "serverName": "hello-server",
  "version": "1.0.0",
  "tools": [
    "hello",
    "math"
  ]
}
```

---

### Resource Templates

#### project://{projectName}

Dynamic resource template that returns project information.

Example:

```text
project://order-service
```

Response:

```json
{
  "project": "order-service",
  "status": "active",
  "owner": "upinta"
}
```

---

### Prompts

#### greet-user

Generates a reusable prompt template.

Example Input:

```json
{
  "name": "Upinta"
}
```

Example Prompt:

```text
You are a friendly AI assistant.

Greet Upinta warmly and ask how their MCP learning journey is going.

Keep the response short and encouraging.
```

## Project Structure

```text
src/main/java
├── server
│   └── HelloMcpServer.java
│
├── tools
│   ├── HelloTool.java
│   ├── CalculatorTool.java
│   └── MathTool.java
│
├── resources
│   ├── ServerInfoResource.java
│   └── ProjectResourceTemplate.java
│
└── prompts
    └── GreetUserPrompt.java
```

## Technologies

* Java 21
* Maven
* MCP Java SDK 1.0.0
* Jackson
* SLF4J

## Running the Server

Build:

```bash
mvn clean package
```

Run:

```bash
java -jar target/mcp-hello.jar
```

## Testing with MCP Inspector

Launch Inspector:

```bash
npx @modelcontextprotocol/inspector -- java -jar target/mcp-hello.jar
```

Open:

```text
http://localhost:6274
```

Test:

* Tools
* Resources
* Resource Templates
* Prompts

## MCP Concepts Covered

* MCP Server Setup
* Tool Registration
* Tool Execution
* Input Schemas
* Error Handling
* Resources
* Resource Templates
* Prompts
* MCP Inspector

## Next Steps

* External API Integration (Weather Tool)
* GitHub Tool
* MCP Client Development
* Async Tools
* Sampling
* Production-grade MCP Server Architecture

## Author

Upinta Singh
