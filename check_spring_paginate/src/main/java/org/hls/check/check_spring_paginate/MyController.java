package org.hls.check.check_spring_paginate;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("")
public class MyController {

    @GetMapping("check1")
    @ResponseBody
    String getCheck1(Pageable pageable, UriComponentsBuilder uriBuilder) {
        return "check1 " + pageable.toString();
    }
    @GetMapping("check2")
    @ResponseBody
    String getCheck2(Pageable pageable) {
        return "check2 " + pageable.toString();
    }
    @GetMapping("check3")
    @ResponseBody
    String getCheck3(Pageable pageable,
                     @RequestParam(value = "select", defaultValue = "") String select,
                     @RequestParam(value = "where", defaultValue = "") String where) {
        return String.format("check3 '%s' '%s' %s", select, where, pageable.toString());
    }
}
