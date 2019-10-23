package com.sinothk.redheart.controller;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.comm.authorization.TokenCheck;
import com.sinothk.redheart.domain.DataCenterEntity;
import com.sinothk.redheart.domain.UserLoginAOEntity;
import com.sinothk.redheart.service.DataCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "数据统计中心")
@RestController
@RequestMapping("/dataCenter")
public class DataCenterController {

    @Resource(name = "dataCenterService")
    private DataCenterService dataCenterService;

    @ApiOperation(value = "登录统计：今日登录用户列表", notes = "登录统计：今日登录用户列表")
    @GetMapping("/getTodayLoginUserPageList")
    @TokenCheck
    public ResultData<PageData<List<UserLoginAOEntity>>> getTodayLoginUserPageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {
        return dataCenterService.getTodayLoginUserPageList(pageNum, pageSize);
    }

    @ApiOperation(value = "登录统计：最近一周登录用户列表", notes = "登录统计：最近一周登录用户列表")
    @GetMapping("/getWeekLoginUserPageList")
    @TokenCheck
    public ResultData<PageData<List<UserLoginAOEntity>>> getWeekLoginUserPageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {
        return dataCenterService.getWeekLoginUserPageList(pageNum, pageSize);
    }

    @ApiOperation(value = "登录统计：本月登录用户列表", notes = "登录统计：本月登录用户列表")
    @GetMapping("/getThisMonthLoginUserPageList")
    @TokenCheck
    public ResultData<PageData<List<UserLoginAOEntity>>> getThisMonthLoginUserPageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {
        return dataCenterService.getThisMonthLoginUserPageList(pageNum, pageSize);
    }

    @ApiOperation(value = "登录统计：某年每个月的用户登录量统计", notes = "登录统计：某年每个月的用户登录量统计")
    @GetMapping("/getYearLoginUserPageList")
    @TokenCheck
    public ResultData<List<DataCenterEntity>> getYearLoginUserPageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "年号：形如\"2019\"") @RequestParam("yearStr") String yearStr) {
        return dataCenterService.getYearLoginUserPageList(yearStr);
    }
}
