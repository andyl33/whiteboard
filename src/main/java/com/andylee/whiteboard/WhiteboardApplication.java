package io.andylee.whiteboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Spring Boot Application entry point for Whiteboard.
 */
@SpringBootApplication
public class WhiteboardApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WhiteboardApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WhiteboardApplication.class, args);
    }
}