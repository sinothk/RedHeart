package com.sinothk.redheart.controller;

import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.AccountUtil;
import com.sinothk.redheart.config.InitLoader;
import com.sinothk.redheart.domain.UserEntity;
import com.sinothk.redheart.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.logging.Logger;

@Api(tags = "用户系统")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    @ApiOperation(value = "新增：用户实体", notes = "新增：用户实体")
    @GetMapping("/addUser")
    @ResponseBody
    public ResultData<String> add(@ApiParam(value = "用户信息", required = true) @RequestBody UserEntity user) {
        // http://localhost:8080/redheart/user/add
        return ResultData.error("失败");
    }

    @ApiOperation(value = "新增：用户", notes = "新增：用户")
    @GetMapping("/add")
    @ResponseBody
    public ResultData<String> add(@ApiParam(value = "用户邮箱", required = true) @RequestParam("email") String email,
                                  @ApiParam(value = "用户密码", required = true) @RequestParam("password") String password) {
        // http://localhost:8080/redheart/user/add

        System.out.println("email : " + email);
        System.out.println("password : " + password);
        System.out.println("account : " + AccountUtil.create(InitLoader.getInitAccountSet()));


        return ResultData.error("失败");
    }
}
