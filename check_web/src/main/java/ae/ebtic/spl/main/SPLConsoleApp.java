package ae.ebtic.spl.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@SpringBootApplication
@RestController
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
}
