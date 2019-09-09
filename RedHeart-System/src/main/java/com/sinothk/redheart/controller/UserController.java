package com.sinothk.redheart.controller;

import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户系统")
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation(value = "新增：用户", notes = "新增：用户")
    @GetMapping("/add")
    @ResponseBody
    public ResultData<String> add(@ApiParam(value = "用户信息", required = true) @RequestBody UserEntity user) {
        // http://localhost:8080/redheart/user/add
        return ResultData.error("失败");
    }
}
