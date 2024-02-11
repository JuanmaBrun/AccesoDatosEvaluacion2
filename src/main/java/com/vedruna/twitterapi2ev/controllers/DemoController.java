package com.vedruna.twitterapi2ev.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {

    @PostMapping(value = "demo")
    public String welcome() {
        return "Welcome from secure endpoint";
    }

    @PostMapping(value = "demo2")
    public String welcome2() {
        return "Welcome2 from2 secur2 endpoin2t";
    }
}
