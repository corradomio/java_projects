package org.hls.check.check_spring_paginate;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/check")
public class MyController {

    @GetMapping("")
    @ResponseBody
    String getCheck(Pageable pageable, UriComponentsBuilder uriBuilder) {
        return "check " + pageable.toString();
    }
}
