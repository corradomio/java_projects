package org.hls.check.sboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@SpringBootApplication()
public class Application {
    public static void main(String[] args) {
        // String appHome = new File(".").getAbsolutePath();
        // System.setProperty("spring.app.root", appHome);
        SpringApplication.run(Application.class, args);
    }
}

