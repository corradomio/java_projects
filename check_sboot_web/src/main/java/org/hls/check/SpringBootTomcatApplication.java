package org.hls.check;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class SpringBootTomcatApplication extends SpringBootServletInitializer {

    private String homeDirectory;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        homeDirectory = servletContext.getRealPath("/").replace("\\", "/");
        super.onStartup(servletContext);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        Properties applicationProperties = loadProperties();
        builder = builder.sources(SpringBootTomcatApplication.class);
        builder.application().setDefaultProperties(applicationProperties);
        return builder;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTomcatApplication.class, args);
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        File propertiesFile = new File(homeDirectory, "WEB-INF/application.properties");
        try(InputStream is = new FileInputStream(propertiesFile)) {
            properties.load(is);
        }
        catch(Throwable ex) {
            ex.printStackTrace();
        }
        properties.setProperty("spring.app.root", homeDirectory);
        return properties;
    }
}
