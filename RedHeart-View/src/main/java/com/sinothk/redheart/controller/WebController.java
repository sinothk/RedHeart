package com.sinothk.redheart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/hello")
    private String helloWorld() {
        // http://localhost:80/redHeart/hello
        return "hello";
    }
}