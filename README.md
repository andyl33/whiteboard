# Whiteboard - Kanban Board for [Trac](https://trac.edgewall.org/) Integration

This project started life as a misguided attempt to build a visual Kanban interface for managing [Trac](https://trac.edgewall.org/) tickets and workflows.

## Project Overview

This project serves as a **test case for [AmpCode](https://ampcode.com/) and Agent-driven software development**, demonstrating how AI agents can modernize legacy codebases. The application started as a 13-year-old incomplete Spring MVC and FreeMarker web application and is being systematically upgraded exclusively with [AmpCode](https://ampcode.com/).

### What is Whiteboard?

Whiteboards original aim was to transform the traditional [Trac](https://trac.edgewall.org/) ticket management experience into a modern, visual Kanban board interface so that Users could:

- **Visual Workflow Management**: View tickets as cards in a drag-and-drop Kanban board
- **Real-time [Trac](https://trac.edgewall.org/) Integration**: Sync ticket status changes directly with [Trac](https://trac.edgewall.org/) via XML-RPC
- **Milestone-based Views**: Filter and organize tickets by project milestones
- **Workflow Validation**: Enforce proper state transitions between development stages

### Workflow Stages

The application implements a complete software development workflow:

1. **Backlog** → New tickets awaiting assignment
2. **In Progress** → Active development work
3. **Ready for Review** → Completed work awaiting code review
4. **Reviewing** → Under active review
5. **Testing** → Quality assurance and testing
6. **Ready to Release** → Approved for deployment

## Technology Stack

### Current (Modernized)
- **Spring Boot 3.5.0** with Java 17
- **Maven 3.9.9** build system
- **FreeMarker** templating engine
- **XML-RPC** for [Trac](https://trac.edgewall.org/) integration
- **JUnit 5** for testing

### Original (Legacy)
- Spring MVC 3.1.1
- Java 8
- Maven 3.x
- Manual dependency management

## Modernization Roadmap

This project follows a systematic modernization approach using the [AmpCode](https://ampcode.com/) AI agent to transform the application from a legacy Spring MVC application into a Spring Boot back-end and Svelte front-end. The Trac ticket management elements will be replaced by a self-contained ticket management system:

### ✅ Phase 1: Framework Modernization (Completed)
- [x] Updated to Spring Boot 3.5.0 with auto-configuration
- [x] Migrated from Java 8 to Java 17
- [x] Replaced individual Spring dependencies with Boot starters
- [x] Updated package structure to `io.andylee.whiteboard.*`
- [x] Modernized Maven configuration and dependencies

### 🚧 Phase 2: Data Persistence Layer (Planned)
- [ ] Add PostgreSQL database integration
- [ ] Implement JPA/Hibernate entities
- [ ] Create database migration scripts
- [ ] Add connection pooling and transaction management
- [ ] Implement caching layer for performance

### 🚧 Phase 3: Containerization (Planned)
- [ ] Create Dockerfile for application
- [ ] Add Docker Compose for local development
- [ ] Configure PostgreSQL container
- [ ] Set up development and production profiles
- [ ] Implement health checks and monitoring

### 🚧 Phase 4: Frontend Modernization (Planned)
- [ ] Replace FreeMarker with modern Svelte UI
- [ ] Implement REST API endpoints
- [ ] Add real-time WebSocket updates
- [ ] Create responsive, mobile-friendly interface
- [ ] Add modern drag-and-drop functionality

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.9.9 or higher
- Access to a [Trac](https://trac.edgewall.org/) server with XML-RPC enabled

### Configuration
1. Update [Trac](https://trac.edgewall.org/) credentials in `src/main/resources/application.properties`:
   ```properties
   whiteboard.trac.url=https://your-trac-server.com/login/xmlrpc
   whiteboard.trac.username=your-username
   whiteboard.trac.password=your-password
   ```

### Running the Application

#### Option 1: Spring Boot Maven Plugin
```bash
mvn spring-boot:run
```

#### Option 2: Development Launcher
```bash
# Runs on port 9081 with dev profile
mvn compile exec:java -Dexec.mainClass="io.andylee.whiteboard.jetty.Launcher"
```

#### Option 3: IDE
Run the main method in `io.andylee.whiteboard.WhiteboardApplication`

### Access
- Application: http://localhost:8080/whiteboard
- Development server: http://localhost:9081/whiteboard

## Testing

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=XmlRpcTemplateTest
```

## Project Structure

```
src/
├── main/java/io/andylee/whiteboard/
│   ├── WhiteboardApplication.java          # Spring Boot main class
│   ├── config/ApplicationConfig.java       # Application configuration
│   ├── controller/BoardController.java     # Web controllers
│   ├── domain/                             # Domain models and logic
│   ├── data/                              # Data access layer
│   └── webservice/xmlrpc/                 # [Trac](https://trac.edgewall.org/) XML-RPC integration
├── main/resources/
│   ├── application.properties             # Spring Boot configuration
│   └── templates/                         # FreeMarker templates
└── test/java/                            # Test classes
```

## Agent-Driven Development

This project demonstrates the power of AI agents in software modernization:

- **Systematic Refactoring**: Agents methodically updated 45+ Java files
- **Dependency Management**: Automated migration from legacy to modern dependencies
- **Code Quality**: Maintained existing functionality while modernizing structure
- **Best Practices**: Applied current Spring Boot conventions and patterns

## Contributing

This project is primarily an AI agent development experiment. Contributions are welcome, especially those that:

- Advance the modernization roadmap
- Demonstrate agent-driven development techniques
- Improve integration with modern development tools

## License

This project is available under the MIT License.

---

*This README was generated as part of an AI agent-driven modernization process.*