package com.sinothk.redheart.controller;

import com.alibaba.fastjson.JSON;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.WxApiEntity;
import com.sinothk.redheart.domain.WxUserEntity;
import com.sinothk.redheart.domain.WxUserVo;
import com.sinothk.redheart.service.WxUserService;
import com.sinothk.redheart.utils.wx.WxUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "微信用户管理")
@RestController
@RequestMapping("/wxBiz")
public class WxBizController {

    @Resource(name = "wxUserService")
    private WxUserService wxUserService;

    @ApiOperation(value = "获得微信OpenId", notes = "获得微信OpenId")
    @GetMapping("/getOpenId")
    public ResultData<WxApiEntity> getOpenId(@ApiParam("code") @RequestParam("code") String code) {
        // http://192.168.124.10:8086/redheart/wxBiz/getOpenId?code=043u9z8C1bQu360v3J9C1niD8C1u9z8s
        try {
            String wxDataStr = WxUtil.getOpenID(code);
            WxApiEntity wxApiEntity = JSON.parseObject(wxDataStr, WxApiEntity.class);
            return ResultData.success(wxApiEntity);
        } catch (Exception ex) {
            return ResultData.error("获取WxOpenId数据异常");
        }
    }

    @ApiOperation(value = "新增：通过微信用户登录或注册", notes = "新增：通过微信用户登录或注册")
    @PostMapping("/loginByWx")
    public ResultData<WxUserEntity> loginByWx(@ApiParam("wxUser") @RequestBody WxUserVo wxUser) {
        // http://192.168.124.12:11000/wxBiz/loginByWx
        try {
            WxUserEntity wxUserEntity = wxUserService.saveOrFindUser(wxUser);
            if (wxUserEntity == null) {
                return ResultData.error("数据解析为空");
            } else {
                return ResultData.success(wxUserEntity);
            }
        } catch (Exception ex) {
            return ResultData.error("数据访问或解析异常");
        }
    }
}
