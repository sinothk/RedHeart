package com.sinothk.redheart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class WebController {

    @GetMapping("/index")
    private String index() {
        // http://localhost:80/redHeart/index
        return "index";
    }

    @GetMapping("/web/login")
    private String login(Map<String, Object> paramMap) {
        // http://localhost:80/redHeart/main

        paramMap.put("name", "张三11");
        paramMap.put("age", 31);

        return "main";
    }

    @GetMapping("/main")
    private String main(Map<String, Object> paramMap) {
        // http://localhost:80/redHeart/main

        /** 默认Map的内容会放大请求域中，页面可以直接使用Thymeleaf取值*/
        paramMap.put("name", "张三");
        paramMap.put("age", 35);

        return "main";
    }


}