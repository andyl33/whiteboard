package io.andylee.whiteboard.jetty;

import io.andylee.whiteboard.WhiteboardApplication;
import org.springframework.boot.SpringApplication;

/**
 * Starts a Spring Boot application for local development
 */
public class Launcher {
    public static void main(String[] args) throws Exception {
        // Set development profile and port
        System.setProperty("spring.profiles.active", "dev");
        System.setProperty("server.port", "9081");
        
        // Start Spring Boot application
        SpringApplication.run(WhiteboardApplication.class, args);
    }
}
