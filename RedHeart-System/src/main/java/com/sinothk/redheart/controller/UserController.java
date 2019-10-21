package com.sinothk.redheart.controller;

import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.AccountUtil;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.redheart.config.AccountInitLoader;
import com.sinothk.redheart.domain.UserEntity;
import com.sinothk.redheart.repository.UserMapper;
import com.sinothk.redheart.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "用户系统")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    @ApiOperation(value = "新增：用户", notes = "新增：用户")
    @GetMapping("/add")
    @ResponseBody
    public ResultData<UserEntity> add(@ApiParam(value = "用户邮箱", required = true) @RequestParam("email") String email,
                                      @ApiParam(value = "用户密码", required = true) @RequestParam("password") String password) {
        if (StringUtil.isEmpty(email)) {
            // 验证邮箱
            return ResultData.error("");
        }
        if (StringUtil.isEmpty(password)) {
            // 验证密码
            return ResultData.error("");
        }

        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setUserPwd(password);
        return userService.addUser(user);
    }

    @ApiOperation(value = "新增：用户实体", notes = "新增：用户实体")
    @GetMapping("/update")
    @ResponseBody
    public ResultData<String> update(@ApiParam(value = "用户信息", required = true) @RequestBody UserEntity user) {
        // http://localhost:8080/redheart/user/add
        return ResultData.error("失败");
    }
}
