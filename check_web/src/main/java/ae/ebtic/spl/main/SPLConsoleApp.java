package ae.ebtic.spl.main;

import ae.ebtic.spl.data.feature.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@SpringBootApplication
// @EnableNeo4jRepositories(basePackageClasses = SPLData.class)
// @EnableNeo4jRepositories(repositoryBaseClass = ExtendedNeo4jRepository.class, basePackageClasses = SPLData.class)
// @EntityScan("ae.ebtic.spl.data")
@RestController
@ImportResource({"file:${spring.app.root:./web}/WEB-INF/applicationContext.xml"})
// @ComponentScan(value = "jext.springframework")
// @ComponentScan("ae.ebtic.spl.data")
@ComponentScan("ae")
public class SPLConsoleApp {

    public static void main(String[] args) {
        System.out.println(new File(".").getAbsolutePath());

        SpringApplication.run(SPLConsoleApp.class, args);
    }

    @GetMapping("/app")
    @ResponseBody
    public String test() {
        return "/app: " + (new File(".").getAbsolutePath());
    }

    // @Autowired
    FeatureRepository repository;

    @Autowired
    void setFeatureRepository(FeatureRepository repository) {
        this.repository = repository;
    }

    @Bean
    CommandLineRunner demo1() {
        return args -> {
            repository.findAll(PageRequest.of(1,10))
                    .forEach(project -> {
                        System.out.printf("Project %d: %s\n", project.getId(), project.getName());
                    });
        };
    }

}
