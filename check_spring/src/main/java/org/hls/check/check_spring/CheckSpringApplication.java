package org.hls.check.check_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class CheckSpringApplication implements CommandLineRunner/*, ApplicationRunner*/ {

    public static void main(String[] args) {
        // SpringApplication.run(CheckSpringApplication.class, args);

        // SpringApplication app = new SpringApplication(CheckSpringApplication.class);
        // app.setBannerMode(Banner.Mode.OFF);
        // app.run(args);

        new SpringApplicationBuilder()
                .bannerMode(Banner.Mode.OFF)
                .sources(CheckSpringApplication.class)
                .run(args);

        // ApplicationContext ctx = new AnnotationConfigApplicationContext(CheckSpringApplication.class);

        // Database db = ctx.getBean("database", Database.class);
        // System.out.println(db.getName());
        //
        // db = ctx.getBean("database", Database.class);
        // System.out.println(db.getName());
        //
        // System.out.println("Done");
    }

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private Database db;

    @Override
    public void run(String ... args) {
        // Database db = ctx.getBean("database", Database.class);
        System.out.println(db.getName());

        db = ctx.getBean("database", Database.class);
        System.out.println(db.getName());

        System.out.println("Done");
    }

    // @Override
    // public void run(ApplicationArguments args) throws Exception {
    //     Database db = ctx.getBean("database", Database.class);
    //     System.out.println(db.getName());
    //
    //     db = ctx.getBean("database", Database.class);
    //     System.out.println(db.getName());
    //
    //     System.out.println("Done");
    // }
}
