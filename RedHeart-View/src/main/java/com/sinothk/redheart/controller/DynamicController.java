package com.sinothk.redheart.controller;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.base.utils.TokenUtil;
import com.sinothk.redheart.comm.authorization.TokenCheck;
import com.sinothk.redheart.domain.TopicAo;
import com.sinothk.redheart.service.DynamicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "动态相关")
@RestController
@RequestMapping("/dynamic")
public class DynamicController {

    @Resource(name = "dynamicService")
    private DynamicService dynamicService;

    @ApiOperation(value = "查询：动态查询用户动态", notes = "动态查询用户动态")
    @GetMapping("/findDynamicByAccount")
    @TokenCheck
    public ResultData<PageData<TopicAo>> findDynamicByAccount(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        return dynamicService.findDynamicByAccount(account, pageNum, pageSize);
    }

}
