package com.sinothk.redheart.controller;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.base.utils.TokenUtil;
import com.sinothk.redheart.comm.authorization.TokenCheck;
import com.sinothk.redheart.domain.AppVersionEntity;
import com.sinothk.redheart.service.AppVersionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Api(tags = "版本控制")
@RestController
@RequestMapping("/app/version")
public class AppVersionController {

    @Resource(name = "appVersionService")
    private AppVersionService appVersionService;

    @ApiOperation(value = "应用：发布", notes = "发布")
    @PostMapping("/add")
    @ResponseBody
    @TokenCheck
    public ResultData<AppVersionEntity> add(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "通知实体信息", required = true) @RequestBody AppVersionEntity appEntity) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        if (appEntity.getAppCode() == null) {
            return ResultData.error("新版信息不完整");
        }

        appEntity.setCreatorAccount(Long.valueOf(account));
        appEntity.setCreateTime(new Date());

        return appVersionService.add(appEntity);
    }

    @ApiOperation(value = "应用：更新状态", notes = "更新状态")
    @PostMapping("/changeStatus")
    @ResponseBody
    @TokenCheck
    public ResultData<AppVersionEntity> changeStatus(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "appCode", required = true) @RequestParam("appCode") Integer appCode,
            @ApiParam(value = "appStatus: -1:删除；0:提交；1：发布！", required = true) @RequestParam("appStatus") Integer appStatus) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        if (appCode == null) {
            return ResultData.error("appCode信息不完整");
        }

        if (appStatus == null) {
            return ResultData.error("appStatus信息不完整");
        }

        AppVersionEntity newAppVersion = new AppVersionEntity();
        newAppVersion.setAppCode(appCode);
        newAppVersion.setPublicTime(new Date());
        newAppVersion.setAppStatus(appStatus);
        return appVersionService.changeStatus(newAppVersion);
    }

    @ApiOperation(value = "应用：获取最新版本APP", notes = "获取最新版本APP")
    @GetMapping("/getLastApp")
    @TokenCheck
    public ResultData<AppVersionEntity> getLastApp(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token) {
        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }
        return appVersionService.getLastApp();
    }

    @ApiOperation(value = "应用：获取APP分页数据", notes = "获取APP分页数据")
    @GetMapping("/getAppPageList")
    @TokenCheck
    public ResultData<PageData<AppVersionEntity>> getAppPageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "appStatus: -1:删除；0:提交；1：发布！") @RequestParam("appStatus") Integer appStatus,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        return appVersionService.getAppPageList(appStatus, pageNum, pageSize);
    }
}
