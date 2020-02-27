package com.sinothk.redheart.controller;

import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.WebUserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class WebController {

    @GetMapping("/index")
    private String index() {
        // http://localhost:80/redHeart/index
        return "index";
    }

    @GetMapping("/web/login")
    @ResponseBody
    private ResultData<WebUserEntity> login(WebUserEntity userVo) {
//
        WebUserEntity webUserEntity = new WebUserEntity();
        webUserEntity.setName("梁宇涛");
        webUserEntity.setAge(26);
        return ResultData.success(webUserEntity);
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