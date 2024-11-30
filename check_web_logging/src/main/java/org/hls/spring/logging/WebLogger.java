package org.hls.spring.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jext.util.logging.LogManager;

import java.io.File;

@SpringBootApplication
public class WebLogger {
    public static void main(String[] args) {
        // System.out.println("Hello, World!");
        // LogManager.configure(new File("logging.properties"));
        // LogManager.getLogger(WebLogger.class).info("Logging initialized");;

        SpringApplication.run(WebLogger.class, args);
    }
}
