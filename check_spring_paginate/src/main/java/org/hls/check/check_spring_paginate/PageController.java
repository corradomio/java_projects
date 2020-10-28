package org.hls.check.check_spring_paginate;

import org.hls.check.check_spring_paginate.util.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class PageController {

    @GetMapping("")
    public Map<String, Object> getPaged(Pageable pageable) {
        Map<String, Object> result = new HashMap<>();
        result.put("version", "1.0");
        result.put("pageable", pageable.toString());
        return result;
    }

    @GetMapping("{name1}/{name2}")
    public  Map<String, Object> getPaged2(
        @PathVariable String name1,
        @PathVariable String name2,
        Pageable pageable) {
        Map<String, Object> result = new HashMap<>();
        result.put("version", "1.0");
        result.put("pageable", pageable.toString());
        result.put("name1", name1);
        result.put("name2", name2);
        return result;
    }
}
