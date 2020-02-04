package com.sinothk.redheart.controller;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.base.utils.TokenUtil;
import com.sinothk.jpush.pushbyjpush.JPushHelper;
import com.sinothk.redheart.comm.authorization.TokenCheck;
import com.sinothk.redheart.domain.*;
import com.sinothk.redheart.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "用户系统")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    @ApiOperation(value = "新增：用户邮箱密码注册", notes = "用户邮箱密码注册")
    @PostMapping("/add")
    @ResponseBody
    public ResultData<UserEntity> add(@ApiParam(value = "用户邮箱", required = true) @RequestParam("email") String email,
                                      @ApiParam(value = "用户密码", required = true) @RequestParam("password") String password) {
        if (StringUtil.isEmpty(email)) {
            // 验证邮箱
            return ResultData.error("邮箱不能为空");
        }
        if (StringUtil.isEmpty(password)) {
            // 验证密码
            return ResultData.error("密码不能为空");
        }

        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setUserPwd(password);

        return userService.addUser(user);
    }

    @ApiOperation(value = "新增：用户实体注册", notes = "用户实体注册")
    @PostMapping("/addEntity")
    @ResponseBody
    public ResultData<UserEntity> add(@ApiParam(value = "用户注册信息", required = true) @RequestBody UserRegisterVo userVo) {

        if (StringUtil.isEmpty(userVo.getEmail())) {
            // 验证邮箱
            return ResultData.error("邮箱不能为空");
        }
        if (StringUtil.isEmpty(userVo.getUserPwd())) {
            // 验证密码
            return ResultData.error("密码不能为空");
        }

        if (userVo.getSex() == null) {
            // 用户性别
            return ResultData.error("性别不能为空");
        }

        UserEntity user = new UserEntity();
        user.setEmail(userVo.getEmail());
        user.setUserPwd(userVo.getUserPwd());
        user.setSex(userVo.getSex());

        Integer userType;
        if (userVo.getUserType() == null) {
            userType = 0;
        } else {
            userType = userVo.getUserType();
        }
        user.setUserType(userType);

        if (userVo.getSex() == 1) {
            user.setAvatar("living/9999/default/user_default_avatar_m.png");
        }else{
            user.setAvatar("living/9999/default/user_default_avatar_f.png");
        }

        return userService.addUser(user);
    }

    @ApiOperation(value = "更新：更新用户实体", notes = "更新：更新用户实体")
    @PostMapping("/update")
    @ResponseBody
    @TokenCheck
    public ResultData<UserEntity> update(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "用户信息", required = true) @RequestBody UserEntity user) {

        if (user == null) {
            return ResultData.error("用户信息不能为空");
        }

        if (user.getAccount() == null) {
            return ResultData.error("账号不能为空");
        }
        return userService.updateUser(user);
    }

    @ApiOperation(value = "登录：邮箱及密码", notes = "登录：邮箱及密码")
    @PostMapping("/login")
    @ResponseBody
    public ResultData<UserEntity> login(
            @ApiParam(value = "用户邮箱", required = true) @RequestParam("email") String email,
            @ApiParam(value = "用户密码", required = true) @RequestParam("password") String password) {
        if (StringUtil.isEmpty(email)) {
            return ResultData.error("邮箱不能为空");
        }
        if (StringUtil.isEmpty(password)) {
            return ResultData.error("密码不能为空");
        }

        UserVo userVo = new UserVo();
        userVo.setEmail(email);
        userVo.setUserPwd(password);

        return userService.login(userVo);
    }

    @ApiOperation(value = "更新：用户信息(位置信息,IMEI等)", notes = "位置信息,IMEI等")
    @PostMapping("/loginRecord")
    @ResponseBody
    public ResultData<LoginRecordEntity> loginRecord(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "登录信息", required = true) @RequestBody LoginRecordEntity loginRecordVo) {

        if (loginRecordVo == null) {
            return ResultData.error("登录信息不能为空");
        }

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        loginRecordVo.setAccount(Long.valueOf(account));

        return userService.loginRecord(loginRecordVo);
    }

    @ApiOperation(value = "更新：修改密码", notes = "更新：修改密码")
    @PostMapping("/changePwd")
    @ResponseBody
    @TokenCheck
    public ResultData<Boolean> changePwd(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "原密码", required = true) @RequestParam("oldPwd") String oldPwd,
            @ApiParam(value = "新密码", required = true) @RequestParam("newPwd") String newPwd) {

        if (StringUtil.isEmpty(oldPwd)) {
            return ResultData.error("旧密码不能为空");
        }
        if (StringUtil.isEmpty(newPwd)) {
            return ResultData.error("新密码不能为空");
        }
        if (newPwd.equals(oldPwd)) {
            return ResultData.error("新密码不能和旧密码相同");
        }
        if (newPwd.length() < 6 || newPwd.length() > 18) {
            return ResultData.error("密码应在6至18个数之间");
        }

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        return userService.changePwd(account, oldPwd, newPwd);
    }

    @ApiOperation(value = "查询：获取最近登录的用户", notes = "查询：获取最近登录的用户")
    @GetMapping("/getLastLoginUserPageList")
    @TokenCheck
    public ResultData<PageData<UserEntity>> getLastLoginUserPageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String currAccount = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(currAccount)) {
            return ResultData.error("Token解析失败");
        }
        return userService.getLastLoginUserPageList(currAccount, pageNum, pageSize);
    }

    @ApiOperation(value = "查询：可能喜欢的用户", notes = "可能喜欢的用户")
    @GetMapping("/getMaybeLikeUserList")
    @TokenCheck
    public ResultData<PageData<UserEntity>> getMaybeLikeUserList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String likingAccount = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(likingAccount)) {
            return ResultData.error("Token解析失败");
        }

        return userService.getMaybeLikePageList(likingAccount, pageNum, pageSize);
    }

    @ApiOperation(value = "查询：模糊搜索用户(账号、昵称或名称)", notes = "搜索用户(账号、昵称或名称)")
    @GetMapping("/findUserByAccountOrUsername")
    @TokenCheck
    public ResultData<List<UserEntity>> findUserByAccountOrUsername(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "关键字") @RequestParam("keyword") String keyword) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        return userService.findUserByAccountOrUsername(account, keyword);
    }

    @ApiOperation(value = "查询：获取附近用户分页数据", notes = "获取附近用户分页数据")
    @GetMapping("/getNearbyUserPageList")
    @TokenCheck
    public ResultData<PageData<UserAO>> getNearbyUserPageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "男1/女0, -1全部") @RequestParam("sex") int sex,
            @ApiParam(value = "中心纬度") @RequestParam("centerLat") Double centerLat,
            @ApiParam(value = "中心经度") @RequestParam("centerLng") Double centerLng,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String likingAccount = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(likingAccount)) {
            return ResultData.error("Token解析失败");
        }
        return userService.getNearbyUserPageList(sex, centerLat, centerLng, pageNum, pageSize);
    }

    @ApiOperation(value = "查询：获取最新注册用户分页数据", notes = "获取最新注册用户分页数据")
    @GetMapping("/getNewUserPageList")
    @TokenCheck
    public ResultData<PageData<UserEntity>> getNewUserPageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String currAccount = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(currAccount)) {
            return ResultData.error("Token解析失败");
        }
        return userService.getNewUserPageList(currAccount, pageNum, pageSize);
    }

    @ApiOperation(value = "查询：获取动态最多的用户分页数据", notes = "获取动态最多的用户分页数据")
    @GetMapping("/getUsersByTopsTopicPageList")
    @TokenCheck
    public ResultData<PageData<UserEntity>> getUsersByTopsTopicPageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String currAccount = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(currAccount)) {
            return ResultData.error("Token解析失败");
        }

        String sex = TokenUtil.getTokenValue(token, "sex");
        if (sex == null) {
            sex = "-1";
        }

        return userService.getUsersByTopsTopicPageList(sex, pageNum, pageSize);
    }
}
