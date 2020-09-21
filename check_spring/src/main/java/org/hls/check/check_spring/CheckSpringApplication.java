package org.hls.check.check_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class CheckSpringApplication {

    public static void main(String[] args) {
        // SpringApplication.run(CheckSpringApplication.class, args);

        ApplicationContext ctx = new AnnotationConfigApplicationContext(CheckSpringApplication.class);

        Database db = ctx.getBean("database", Database.class);
        System.out.println(db.getName());

        db = ctx.getBean("database", Database.class);
        System.out.println(db.getName());

        System.out.println("Done");
    }

}
