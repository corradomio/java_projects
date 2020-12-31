package org.hls.check;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class TomcatController {

    private Logger logger = LogManager.getLogger(TomcatController.class);

    @Value("${hello.world}")
    private String helloWorld;

    public TomcatController() {
        logger.info("Created");
    }

    @GetMapping("/")
    public String index() {
        return helloWorld;
    }


    @GetMapping("/hello")
    public Collection<String> sayHello() {
        return IntStream.range(0, 10)
            .mapToObj(i -> helloWorld + " " + i)
            .collect(Collectors.toList());
    }
}