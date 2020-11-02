package org.hls.check.check_spring_neo4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spl/v2")
public class TestController {

    @GetMapping("")
    public String root() {
        return "root";
    }

    @GetMapping("test")
    public String test() {
        return "test";
    }

}
