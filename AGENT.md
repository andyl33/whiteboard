# Whiteboard - Agent Guidelines

## Build/Test Commands
- **Build**: `mvn compile` or `mvn package` 
- **Test all**: `mvn test`
- **Test single class**: `mvn test -Dtest=XmlRpcTemplateTest`
- **Clean build**: `mvn clean compile`
- **Run Spring Boot app**: `mvn spring-boot:run` or run `WhiteboardApplication.main()`
- **Run dev launcher**: Use `src/test/java/.../jetty/Launcher.java` (now Spring Boot based)

## Project Structure
- **Modern Stack**: Spring Boot 3.5.0 with Java 17 and Maven 3.9.9
- **Package Structure**: `io.andylee.whiteboard.*` (updated from com.andylee.whiteboard)
- Domain models in `io.andylee.whiteboard.domain`
- Controllers in `io.andylee.whiteboard.controller` 
- Data access in `io.andylee.whiteboard.data`
- XML-RPC webservice integration in `io.andylee.whiteboard.webservice.xmlrpc`
- **Templates**: FreeMarker templates in `src/main/resources/templates/`
- **Config**: Spring Boot properties in `src/main/resources/application.properties`

## Code Style
- **Packages**: Follow `io.andylee.whiteboard.*` structure
- **Imports**: Group standard Java, then third-party, then project imports
- **Annotations**: Use Spring Boot annotations (`@SpringBootApplication`, `@Controller`, `@RequestMapping`, `@Autowired`)
- **Fields**: Private with public getters/setters, use validation (Apache Commons Validate)
- **Comments**: Use Javadoc for classes, minimal inline comments, TODO markers for pending work
- **Error handling**: Throw domain-specific exceptions (`InvalidStateException`, `UnknownColumnException`)
- **Dependencies**: Spring Boot starters, Apache Commons, Jackson for JSON, JUnit 5 + Mockito for testing