package com.sinothk.redheart.controller;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.base.utils.TokenUtil;
import com.sinothk.redheart.controller.comm.authorization.TokenCheck;
import com.sinothk.redheart.domain.AdvertiseEntity;
import com.sinothk.redheart.service.AdvertiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Api(tags = "广告管理")
@RestController
@RequestMapping("/advertise")
public class AdvertiseController {

    @Resource(name = "advertiseService")
    private AdvertiseService advertiseService;

    @ApiOperation(value = "广告：发布", notes = "广告：发布")
    @PostMapping("/add")
    @ResponseBody
    @TokenCheck
    public ResultData<Boolean> add(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "通知实体信息", required = true) @RequestBody AdvertiseEntity adEntity) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }
        if (StringUtil.isEmpty(adEntity.getAdTitle())) {
            return ResultData.error("广告标题不能为空");
        }
        if (StringUtil.isEmpty(adEntity.getAdImg())) {
            return ResultData.error("广告图片不能为空");
        }
        if (StringUtil.isEmpty(adEntity.getAdUrl())) {
            return ResultData.error("广告详情不能为空");
        }

        adEntity.setCreateAccount(Long.valueOf(account));
        adEntity.setCreateTime(new Date());
        adEntity.setStatus(0);

        return advertiseService.add(adEntity);
    }

    @ApiOperation(value = "广告：获取所有分页广告", notes = "通知：获取所有分页广告")
    @GetMapping("/getAdList")
    public ResultData<PageData<AdvertiseEntity>> getAdList(
            @ApiParam(value = "城市名称：XX市", required = true) @RequestParam("cityName") String cityName,
            @ApiParam(value = "位置编号", required = true) @RequestParam("adWhere") int adWhere,
            @ApiParam(value = "查询页号", required = true) @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页大小", required = true) @RequestParam("pageSize") int pageSize) {
        return advertiseService.getAdList(cityName, adWhere, pageNum, pageSize);
    }

    @ApiOperation(value = "广告：获取开屏广告，根据城市名称", notes = "获取开屏广告，根据城市名称")
    @GetMapping("/getWelcomeAdvList")
    public ResultData<AdvertiseEntity> getWelcomeAdvList(
            @ApiParam(value = "城市名称：XX市", required = true) @RequestParam("cityName") String cityName) {
        return advertiseService.getWelcomeAdvList(cityName);
    }
}
