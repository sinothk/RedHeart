package com.sinothk.redheart.view.controller;

import com.sinothk.base.entity.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/add")
    @ResponseBody
    public ResultData<String> add(){
        // http://localhost:8086/redheart/users/add
        return ResultData.error("失败");
    }
}
